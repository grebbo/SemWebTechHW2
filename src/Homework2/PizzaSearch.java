package Homework2;

import java.util.ArrayList;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFactory;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.query.ResultSetRewindable;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;
import org.apache.jena.vocabulary.DC;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.RDF;

public class PizzaSearch {

	static public final String NL = System.getProperty("line.separator");

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		String prologOwl = "PREFIX owl: <" + OWL.getURI() + ">";
		String prologRdfs = "PREFIX rdfs: <" + RDFS.getURI() + ">";
		String prologRdf = "PREFIX rdf: <" + RDF.getURI() + ">";
		String prologDbo = "PREFIX dbo: <http://dbpedia.org/ontology/ingredient>";
		String pizzaOntology = "PREFIX pizzaOnt: <http://www.co-ode.org/ontologies/pizza/pizza.owl#>";

		/*
		 * Model myOntologyModel = ModelFactory.createOntologyModel();
		 * myOntologyModel.read("pizza.owl");
		 * System.out.println(myOntologyModel.toString());
		 */

		String queryStr = "";
		QueryExecution qexec = null;
		Query query = null;
		ResultSetRewindable rs = null;

		ArrayList<String> namedPizzas = new ArrayList<String>();

		try {
			queryStr = prologRdfs + NL + pizzaOntology + NL + " SELECT * " + " WHERE { "
					+ "		?pizza <http://www.w3.org/2000/01/rdf-schema#subClassOf> pizzaOnt:NamedPizza " + " }";

			query = QueryFactory.create(queryStr);
			Model model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);
			model.read("pizza.owl");

			qexec = QueryExecutionFactory.create(query, model);

			rs =  ResultSetFactory.makeRewindable(qexec.execSelect());
			// ResultSetFormatter.out(System.out, rs, query);
			rs.next();
			while (rs.hasNext()) {
				String t = rs.next().toString();
				namedPizzas.add(t.substring(t.indexOf("#") + 1, t.indexOf(">")));
			}

			qexec.close();
			//System.out.println(namedPizzas);

			String str = "{?pizza foaf:name\"zzz\"}";
			for (String pizza : namedPizzas) {
				pizza = stripCapital(pizza);
				str += "UNION" + "{?pizza foaf:name \"" + pizza + "\"@en }";
			}
			for (String pizza : namedPizzas) {
				pizza = stripCapital(pizza).toLowerCase();
				str += "UNION" + "{?pizza rdfs:label \"Pizza " + pizza + "\"@it }";
			}

			str += ".";
			//System.out.println(str);
			queryStr = prologRdfs + NL + prologRdf + NL + prologDbo + NL + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
					+ NL + " SELECT ?label ?ingredient" + " WHERE {"
					+ "	?pizza <http://dbpedia.org/property/mainIngredient> ?ingredient . "
					+ "   ?pizza rdfs:label ?label {" + "   SELECT ?pizza " + "   WHERE {"
					+ "     ?pizza rdf:type <http://dbpedia.org/ontology/Food> ." + str + "   }}" + " }";

			query = QueryFactory.create(queryStr);

			// Remote execution.
			qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
			// Set the DBpedia specific timeout.
			((QueryEngineHTTP) qexec).addParam("timeout", "100000");
			// Execute.

			rs =  ResultSetFactory.makeRewindable(qexec.execSelect());
			System.out.println("Result as CSV: ");
			ResultSetFormatter.outputAsCSV(rs);
			rs.reset();
			System.out.println("Result as JSON: ");
			ResultSetFormatter.outputAsJSON(rs);
			rs.reset();
			System.out.println("Result standard: ");
			ResultSetFormatter.out(System.out, rs, query);
			qexec.close();
			
			/*
			str = "{?pizza foaf:name\"zzz\"}";
			for (String pizza : namedPizzas) {
				pizza = stripCapital(pizza);
				str += "UNION" + "{?pizza foaf:name \"" + pizza + "\"@en }";
			}
			

			str += ".";
			*/
			System.out.println("QUERY SU DBPEDIA(IT)");
			
			queryStr = prologRdfs + NL + prologRdf + NL + prologDbo + NL + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
					+ NL 
					+" SELECT ?label ?region" + " WHERE {"
					//+ "	OPTIONAL (?pizza <http://dbpedia.org/property/mainIngredient> ?ingredient) . "
					+ "   ?pizza rdfs:label ?label ."
					+ "   ?pizza <http://it.dbpedia.org/property/regione> ?region {" 
					+ "   SELECT ?pizza " + "   WHERE {"
					+ "     ?pizza rdf:type <http://dbpedia.org/ontology/Food> "
					+ "." + str 
					+ "   }"
					+ "}"
					+ " }";

			query = QueryFactory.create(queryStr);

			// Remote execution.
			qexec = QueryExecutionFactory.sparqlService("http://it.dbpedia.org/sparql", query);
			// Set the DBpedia specific timeout.
			((QueryEngineHTTP) qexec).addParam("timeout", "100000");
			// Execute.

			rs =  ResultSetFactory.makeRewindable(qexec.execSelect());
			System.out.println("Result as CSV: ");
			ResultSetFormatter.outputAsCSV(rs);
			rs.reset();
			System.out.println("Result as JSON: ");
			ResultSetFormatter.outputAsJSON(rs);
			rs.reset();
			System.out.println("Result standard: ");
			ResultSetFormatter.out(System.out, rs, query);
			qexec.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// mostro il resultset come json o xml/rdf o html
	}

	public static String stripCapital(String str) {
		for (int i = 1; i < str.length(); i++) {
			if (!Character.isAlphabetic(str.charAt(i))) {
				i++;
				continue;
			}
			if (Character.isUpperCase(str.charAt(i))) {
				str = str.substring(0, i) + " " + str.substring(i);
				i++;
			}
		}
		return str;
	}

}