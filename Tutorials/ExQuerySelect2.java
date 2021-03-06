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
import org.apache.jena.atlas.io.IndentedWriter ;

import org.apache.jena.query.Query ;
import org.apache.jena.query.QueryExecution ;
import org.apache.jena.query.QueryExecutionFactory ;
import org.apache.jena.query.QueryFactory ;
import org.apache.jena.query.ResultSetFactory ;
import org.apache.jena.query.ResultSetFormatter ;
import org.apache.jena.query.ResultSetRewindable ;
import org.apache.jena.rdf.model.Model ;
import org.apache.jena.rdf.model.ModelFactory ;
import org.apache.jena.rdf.model.Resource ;
import org.apache.jena.vocabulary.DC ;


public class ExQuerySelect2
{
    static public final String NL = System.getProperty("line.separator") ; 
    
    public static void main(String[] args)
    {
        // Create the data.
        // This wil be the background (unnamed) graph in the dataset.
        Model model = createModel() ;
        
        // First part or the query string 
        String prolog = "PREFIX dc: <"+DC.getURI()+">" ;
        
        // Query string.
        String queryString = prolog + NL +
            "SELECT ?title ?description WHERE {?x dc:title ?title . ?x dc:description ?description . FILTER regex(?title, \"oo\", \"i\")}" ; 
        
        Query query = QueryFactory.create(queryString) ;
        // Print with line numbers
        query.serialize(new IndentedWriter(System.out,true)) ;
        System.out.println() ;
        
        // Create a single execution of this query, apply to a model
        // which is wrapped up as a Dataset
        
        QueryExecution qexec = QueryExecutionFactory.create(query, model) ;
        // Or QueryExecutionFactory.create(queryString, model) ;

        try {
            // A ResultSet is an iterator - any query solutions returned by .next()
            // are not accessible again.
            // Create a ResultSetRewindable that can be reset to the beginning.
            // Do before first use.
            
            ResultSetRewindable rewindable = ResultSetFactory.makeRewindable(qexec.execSelect()) ;
            System.out.println(rewindable.size());
            ResultSetFormatter.out(rewindable) ;
            rewindable.reset() ;
            ResultSetFormatter.out(rewindable) ;
            
        }
        finally
        {
            // QueryExecution objects should be closed to free any system resources 
            qexec.close() ;
        }
    }
    
    public static Model createModel()
    {
    	  Model m = ModelFactory.createDefaultModel() ;
          
          Resource r1 = m.createResource("http://example.org/book#1") ;
          Resource r2 = m.createResource("http://example.org/book#2") ;
          Resource r3 = m.createResource("http://example.org/book#3") ;
          
          r1.addProperty(DC.title, "SPARQL - the book")
            .addProperty(DC.description, "A book about SPARQL") 
            .addProperty(DC.identifier, "thisISBN")
            .addProperty(DC.type, "teaching");
          
          r2.addProperty(DC.title, "Advanced techniques for SPARQL")
            .addProperty(DC.description, "A good book about SPARQL")
            .addProperty(DC.identifier, "thatISBN")
            .addProperty(DC.type, "teaching");
          
          r3.addProperty(DC.title, "Another Advanced techniques for SPARQL")
          	.addProperty(DC.description, "Another good book about SPARQL")
          	.addProperty(DC.identifier, "thatthisISBN")
          	.addProperty(DC.type, "teaching");

        return m ;
    }
}