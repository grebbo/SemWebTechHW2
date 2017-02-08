/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package Tutorials;

// The ARQ application API.
import org.apache.jena.atlas.io.IndentedWriter;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.DC;


public class ExQuerySelect1 {
	static public final String NL = System.getProperty("line.separator");

	public static void main(String[] args) {
		// Create the data.
		// This wil be the background (unnamed) graph in the dataset.
		Model model = createModel();

		// First part or the query string
		String prolog = "PREFIX dc: <" + DC.getURI() + ">";

		// Query string.
		String queryString = prolog + NL
				+ "SELECT ?title ?description ?type WHERE {?x dc:description ?description . ?x dc:title ?title . OPTIONAL { ?x dc:type ?type }}";

		Query query = QueryFactory.create(queryString);
		// Print with line numbers
		query.serialize(new IndentedWriter(System.out, true));
		System.out.println();

		// Create a single execution of this query, apply to a model
		// which is wrapped up as a Dataset

		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		// Or QueryExecutionFactory.create(queryString, model) ;

		System.out.println("Titles & descriptions: ");

		try {
			// Assumption: it's a SELECT query.
			ResultSet rs = qexec.execSelect();

			// The order of results is undefined.
			for (; rs.hasNext();) {
				QuerySolution rb = rs.nextSolution();

				// Get title - variable names do not include the '?' (or '$')
				RDFNode x = rb.get("title");

				// Check the type of the result value
				if (x.isLiteral()) {
					Literal titleStr = (Literal) x;
					System.out.print("    " + titleStr + " - ");
				} else
					System.out.println("Strange - not a literal: " + x);

				x = rb.get("description");

				// Check the type of the result value
				if (x.isLiteral()) {
					Literal descStr = (Literal) x;
					System.out.print(descStr);
				} else
					System.out.println("Strange - not a literal: " + x);

				x = rb.get("type");

				// Check the type of the result value
				if (x == null) {
					System.out.println();
				}
				else {
					if (x.isLiteral()) {
						Literal typeStr = (Literal) x;
						System.out.println(" - " + typeStr);
					} else
						System.out.println("Strange - not a literal: " + x);
				}
			}
		} finally {
			// QueryExecution objects should be closed to free any system
			// resources
			qexec.close();
		}
	}

	public static Model createModel() {
		Model m = ModelFactory.createDefaultModel();

		Resource r1 = m.createResource("http://example.org/book#1");
		Resource r2 = m.createResource("http://example.org/book#2");

		r1.addProperty(DC.title, "SPARQL - the book").addProperty(DC.description, "A book about SPARQL")
				.addProperty(DC.identifier, "thisISBN");

		r2.addProperty(DC.title, "Advanced techniques for SPARQL")
				.addProperty(DC.description, "A good book about SPARQL").addProperty(DC.identifier, "thatISBN")
				.addProperty(DC.type, "teaching");
		return m;
	}
}
