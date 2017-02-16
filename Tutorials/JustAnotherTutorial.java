package Tutorials;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class JustAnotherTutorial {

	public static void main(String[] args) throws IOException {
		
		// Open the bloggers RDF graph from the filesystem
		InputStream in = new FileInputStream(new File("mycontacts.rdf"));
		
		// Create an empty in-memory model and populate it from the graph
		Model model = ModelFactory.createMemModelMaker().createDefaultModel();
		model.read(in,null); // null base URI, since model URIs are absolute
		in.close();
		
		// Create a new query
		String queryString =
		"PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
		"SELECT ?name ?url " +
			"WHERE {" +
				" ?x foaf:name ?name . OPTIONAL{?x foaf:weblog ?url} . FILTER regex(?name, \"a\", \"i\")}";
		Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		// Output query results
		ResultSetFormatter.out(System.out, results, query);
		// Important - free up resources used running the query
		qe.close();

	}

}
