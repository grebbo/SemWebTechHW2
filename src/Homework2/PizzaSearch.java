package Homework2;

import java.util.ArrayList;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String prologOwl = "PREFIX owl: <" + OWL.getURI() + ">";
		String prologRdfs = "PREFIX rdfs: <" + RDFS.getURI() + ">";
		String prologRdf = "PREFIX rdf: <" + RDF.getURI() + ">";
		

		//OntModel myOntologyModel = ModelFactory.createOntologyModel();
		//myOntologyModel.read("pizza.owl");
		// System.out.println(myOntologyModel.toString());

		String queryStr = "";
		QueryExecution qexec = null;
		Query query = null;
		ResultSet rs = null;

		ArrayList<String> namedPizzas = new ArrayList<String>();
		ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
		
		/*
		 * queryStr =
		 * "SELECT * WHERE { ?pizza <rdfs:subClassOf> <http://www.co-ode.org/ontologies/pizza/pizza.owl#NamedPizza> }"
		 * ;
		 * 
		 * qexec = QueryExecutionFactory.create(queryStr, myOntologyModel);
		 * query = QueryFactory.create(queryStr); rs = qexec.execSelect();
		 * ResultSetFormatter.out(System.out, rs, query); qexec.close();
		 */

		/*
		try {
			queryStr = prologRdfs + NL + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>" + NL 
					+ " SELECT ?label ?wikiID " 
					+ " WHERE {" 
					+ " 	?label rdfs:label ?x ."
					+ "		?wikiID <http://dbpedia.org/ontology/wikiPageID> ?x {"
					+ "		SELECT ?x "
					+ "		WHERE { "
					+ "			?x a <http://dbpedia.org/ontology/Food> ."
					+ "			?x foaf:name \"Pizza\" ."
					+ "			?x <http://dbpedia.org/property/type> \"Greek pizza\" }"
					+ "		}" 
					+ " }";
			*/
		
		try {
			queryStr = prologRdfs + NL + prologRdf + NL + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>" + NL 
					+ " SELECT ?label "
					+ " WHERE { "
					+ " 	?pizza rdfs:label ?label {"
					+ " 	SELECT ?pizza " 
					+ " 	WHERE {" 
					+ " 		?pizza rdf:type <http://dbpedia.org/ontology/Food> ."
					+ "			?pizza foaf:name \"Greek pizza\"@en ."
					+ " 	}}"
					+ " }";	
			
			query = QueryFactory.create(queryStr);

			// Remote execution.
			qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
			// Set the DBpedia specific timeout.
			((QueryEngineHTTP) qexec).addParam("timeout", "100000");

			// Execute.
			rs = qexec.execSelect();
			ResultSetFormatter.out(System.out, rs, query);
			qexec.close();
		} catch (Exception e) {
			e.printStackTrace();
		}


		// mostro il resultset come json o xml/rdf o html
	}


	public static Model createModel(int resNumber, ArrayList<Pizza> pizzas) {
		Model m = ModelFactory.createDefaultModel();

		String temp = "http://example.org/pizza#";
		
		for (int i = 0 ; i < resNumber ; i++) {
			Resource rTemp = m.createResource(temp+i);
			addProperties(rTemp, pizzas.get(i));
		}
		return m;
	}
	
	public static void addProperties(Resource res, Pizza pizza){
		//fillare la resource con i campi di pizza ricavati dalla query
	}
	
	private class Pizza {
				
	}
}
