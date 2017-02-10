package Homework2;

import java.util.ArrayList;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDFS;

public class PizzaSearch {
	
	static public final String NL = System.getProperty("line.separator");

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String prolog = "PREFIX owl: <" + OWL.getURI() + ">";
		String prolog2 = "PREFIX rdfs: <" + RDFS.getURI() + ">";
		
		OntModel myOntologyModel = ModelFactory.createOntologyModel();
		myOntologyModel.read("pizza.owl");
		
		//System.out.println(myOntologyModel.toString());
		//sembra funzionare a giudicare dal print
		
		ArrayList<String> namedPizzas = new ArrayList<String>();
		
		String queryString = prolog + NL + prolog2 + NL
				+ " SELECT ?name "
				+ "	WHERE "
				+ " {	 [] a owl:Class:NamedPizza "
				+ " } ";
		
		//faccio la query su dbpedia e un altro dataset
		
		//mostro il resultset come json o xml/rdf o html
	}

	private class Model {
		//model per i dati delle pizze
	}
}
