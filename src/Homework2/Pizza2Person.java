package Homework2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSetFactory;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.query.ResultSetRewindable;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

public class Pizza2Person {

	static public final String NL = System.getProperty("line.separator");

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// relazione di corrispondenza tra le persone (scorso homework) e pizze
		// (definite prima)
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

			rs = ResultSetFactory.makeRewindable(qexec.execSelect());

			rs.next();
			while (rs.hasNext()) {
				String t = rs.next().toString();
				namedPizzas.add(t.substring(t.indexOf("<") + 1, t.indexOf(">")));

			}
			System.out.println(namedPizzas);
			qexec.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		InputStream in = new FileInputStream(new File("mycontacts.rdf"));

		// Create an empty in-memory model and populate it from the graph
		Model model = ModelFactory.createMemModelMaker().createDefaultModel();
		model.read(in, null); // null base URI, since model URIs are absolute
		in.close();
		
		ArrayList<String> personIDs = new ArrayList<String>();
		
		try {
			queryStr = prologRdf + NL + "PREFIX foaf: <http://xmlns.com/foaf/0.1/> " + NL
					+ " SELECT ?id "
					+ " WHERE { "
					+ " 	?id a foaf:Agent " 
					+ " }";

			query = QueryFactory.create(queryStr);
			qexec = QueryExecutionFactory.create(query, model);

			rs = ResultSetFactory.makeRewindable(qexec.execSelect());

			rs.next();
			while (rs.hasNext()) {
				String t = rs.next().toString();
				personIDs.add(t.substring(t.indexOf("=")+1, t.indexOf(")")).trim());
			}
			System.out.println(personIDs);
			rs.reset();
			//ResultSetFormatter.outputAsCSV(rs);
			qexec.close();

			System.out.println(model.createList(model.listResourcesWithProperty(model.getProperty("foaf:name"))));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//model.add(giuseppe, likes, sloppy)

	}
}
