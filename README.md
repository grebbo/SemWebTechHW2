# SemWebTechHW2

##Risultati

###ExampleDBPedia1

----------------------------------------------------------------------
| Concept                                                            |
======================================================================
| <http://www.openlinksw.com/schemas/virtcxml#FacetCategoryPattern>  |
| <http://www.w3.org/2000/01/rdf-schema#Datatype>                    |
| <http://www.openlinksw.com/schemas/virtrdf#QuadMapFormat>          |
| <http://www.openlinksw.com/schemas/virtrdf#QuadStorage>            |
| <http://www.openlinksw.com/schemas/virtrdf#array-of-QuadMap>       |
| <http://www.openlinksw.com/schemas/virtrdf#QuadMap>                |
| <http://www.openlinksw.com/schemas/virtrdf#array-of-QuadMapFormat> |
| <http://www.openlinksw.com/schemas/virtrdf#QuadMapValue>           |
| <http://www.openlinksw.com/schemas/virtrdf#array-of-QuadMapATable> |
| <http://www.openlinksw.com/schemas/virtrdf#array-of-QuadMapColumn> |
| <http://www.openlinksw.com/schemas/virtrdf#QuadMapColumn>          |
| <http://www.openlinksw.com/schemas/virtrdf#QuadMapFText>           |
| <http://www.openlinksw.com/schemas/virtrdf#QuadMapATable>          |
| <http://www.openlinksw.com/schemas/virtrdf#array-of-string>        |
| <http://www.w3.org/1999/02/22-rdf-syntax-ns#Property>              |
| <http://www.w3.org/2000/01/rdf-schema#Class>                       |
| <http://www.w3.org/ns/sparql-service-description#Service>          |
| <http://www.w3.org/2002/07/owl#AnnotationProperty>                 |
| <http://www.w3.org/2002/07/owl#Class>                              |
| <http://www.w3.org/2002/07/owl#Ontology>                           |
----------------------------------------------------------------------


###ExQuerySelect1

  1 PREFIX  dc:   <http://purl.org/dc/elements/1.1/>
  2 
  3 SELECT  ?title ?description ?type
  4 WHERE
  5   { ?x  dc:description  ?description ;
  6         dc:title        ?title
  7     OPTIONAL
  8       { ?x  dc:type  ?type }
  9   }

Titles & descriptions: 
    Advanced techniques for SPARQL - A good book about SPARQL - teaching
    SPARQL - the book - A book about SPARQL

	
###ExQuerySelect2

  1 PREFIX  dc:   <http://purl.org/dc/elements/1.1/>
  2 
  3 SELECT  ?title ?description
  4 WHERE
  5   { ?x  dc:title        ?title ;
  6         dc:description  ?description
  7     FILTER regex(?title, "oo", "i")
  8   }

1
-----------------------------------------------
| title               | description           |
===============================================
| "SPARQL - the book" | "A book about SPARQL" |
-----------------------------------------------
-----------------------------------------------
| title               | description           |
===============================================
| "SPARQL - the book" | "A book about SPARQL" |
-----------------------------------------------


###JustAnotherTutorial

-----------------------------------------------------------------------------
| name                        | url                                         |
=============================================================================
| "Ciccio Pasticcio"          | <http://cicciopasticcio.blogspot.com/>      |
| "Giulia Cagnes"             |                                             |
| "Edoardo Ferrante"          | <http://fleanend.github.io/>                |
| "Federico D'Ambrosio"       | <http://fedexist.github.io/>                |
| "Reddit - DataIssBeautiful" | <https://www.reddit.com/r/dataisbeautiful/> |
-----------------------------------------------------------------------------

###Homework part 1 (PizzaSearch)

Result as CSV: 
label,ingredient
Sloppy giuseppe,http://dbpedia.org/resource/Bell_pepper
Sloppy giuseppe,http://dbpedia.org/resource/Beef
Pizza napolitana,"Pizza dough , tomatoes , mozzarella"
Pizza napoletana,"Pizza dough , tomatoes , mozzarella"
ãƒŠãƒ?ãƒªãƒ”ãƒƒãƒ„ã‚¡,"Pizza dough , tomatoes , mozzarella"
Pizza napolitaine,"Pizza dough , tomatoes , mozzarella"
Neapolitan pizza,"Pizza dough , tomatoes , mozzarella"
Result as JSON: 
{
  "head": {
    "vars": [ "label" , "ingredient" ]
  } ,
  "results": {
    "bindings": [
      {
        "label": { "type": "literal" , "xml:lang": "en" , "value": "Sloppy giuseppe" } ,
        "ingredient": { "type": "uri" , "value": "http://dbpedia.org/resource/Bell_pepper" }
      } ,
      {
        "label": { "type": "literal" , "xml:lang": "en" , "value": "Sloppy giuseppe" } ,
        "ingredient": { "type": "uri" , "value": "http://dbpedia.org/resource/Beef" }
      } ,
      {
        "label": { "type": "literal" , "xml:lang": "es" , "value": "Pizza napolitana" } ,
        "ingredient": { "type": "literal" , "xml:lang": "en" , "value": "Pizza dough , tomatoes , mozzarella" }
      } ,
      {
        "label": { "type": "literal" , "xml:lang": "it" , "value": "Pizza napoletana" } ,
        "ingredient": { "type": "literal" , "xml:lang": "en" , "value": "Pizza dough , tomatoes , mozzarella" }
      } ,
      {
        "label": { "type": "literal" , "xml:lang": "ja" , "value": "ãƒŠãƒ?ãƒªãƒ”ãƒƒãƒ„ã‚¡" } ,
        "ingredient": { "type": "literal" , "xml:lang": "en" , "value": "Pizza dough , tomatoes , mozzarella" }
      } ,
      {
        "label": { "type": "literal" , "xml:lang": "fr" , "value": "Pizza napolitaine" } ,
        "ingredient": { "type": "literal" , "xml:lang": "en" , "value": "Pizza dough , tomatoes , mozzarella" }
      } ,
      {
        "label": { "type": "literal" , "xml:lang": "en" , "value": "Neapolitan pizza" } ,
        "ingredient": { "type": "literal" , "xml:lang": "en" , "value": "Pizza dough , tomatoes , mozzarella" }
      }
    ]
  }
}
Result standard: 
----------------------------------------------------------------------
| label                  | ingredient                                |
======================================================================
| "Sloppy giuseppe"@en   | <http://dbpedia.org/resource/Bell_pepper> |
| "Sloppy giuseppe"@en   | <http://dbpedia.org/resource/Beef>        |
| "Pizza napolitana"@es  | "Pizza dough , tomatoes , mozzarella"@en  |
| "Pizza napoletana"@it  | "Pizza dough , tomatoes , mozzarella"@en  |
| "ãƒŠãƒ?ãƒªãƒ”ãƒƒãƒ„ã‚¡"@ja           | "Pizza dough , tomatoes , mozzarella"@en  |
| "Pizza napolitaine"@fr | "Pizza dough , tomatoes , mozzarella"@en  |
| "Neapolitan pizza"@en  | "Pizza dough , tomatoes , mozzarella"@en  |
----------------------------------------------------------------------
QUERY SU DBPEDIA(IT)
Result as CSV: 
label,region
Pizza napoletana,Campania
Result as JSON: 
{
  "head": {
    "vars": [ "label" , "region" ]
  } ,
  "results": {
    "bindings": [
      {
        "label": { "type": "literal" , "xml:lang": "it" , "value": "Pizza napoletana" } ,
        "region": { "type": "literal" , "xml:lang": "it" , "value": "Campania" }
      }
    ]
  }
}
Result standard: 
-----------------------------------------
| label                 | region        |
=========================================
| "Pizza napoletana"@it | "Campania"@it |
-----------------------------------------


###Homework part 2 (Pizza2Person)

<rdf:RDF
    xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
    xmlns:dc="http://purl.org/dc/elements/1.1/"
    xmlns:html="http://www.w3.org/1999/xhtml"
    xmlns:j.0="http://viardo.pizza/"
    xmlns:vcard="http://www.w3.org/2006/vcard/ns#"
    xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#"
    xmlns:rss="http://purl.org/rss/1.0/"
    xmlns:foaf="http://xmlns.com/foaf/0.1/">
  <foaf:Agent>
    <vcard:Family>Cagnes</vcard:Family>
    <vcard:Given>Giulia</vcard:Given>
    <rdf:type rdf:resource="http://xmlns.com/foaf/0.1/Person"/>
    <vcard:FN>Giulia Cagnes</vcard:FN>
    <foaf:name>Giulia Cagnes</foaf:name>
    <foaf:interest rdf:resource="http://www.w3.org/2001/sw/"/>
    <vcard:TEL>0000004</vcard:TEL>
    <foaf:holdsAccount>
      <foaf:OnlineAccount>
        <foaf:accountName>Giuli92</foaf:accountName>
        <foaf:accountServiceHomepage rdf:resource="https://www.facebook.com/"/>
      </foaf:OnlineAccount>
    </foaf:holdsAccount>
    <j.0:likes>http://www.co-ode.org/ontologies/pizza/pizza.owl#Soho</j.0:likes>
  </foaf:Agent>
  <foaf:Document rdf:about="http://fleanend.github.io/">
    <rdfs:seeAlso>
      <rss:channel rdf:about="http://fleanend.github.io/rss">
        <foaf:topic rdf:resource="http://www.w3.org/2001/sw/"/>
        <foaf:maker>
          <foaf:Agent>
            <foaf:holdsAccount>
              <foaf:OnlineAccount>
                <foaf:accountName>fleanend</foaf:accountName>
                <foaf:accountServiceHomepage rdf:resource="https://github.com/"/>
              </foaf:OnlineAccount>
            </foaf:holdsAccount>
            <j.0:likes>http://www.co-ode.org/ontologies/pizza/pizza.owl#FruttiDiMare</j.0:likes>
            <foaf:name>Edoardo Ferrante</foaf:name>
            <vcard:FN>Edoardo Ferrante</vcard:FN>
            <vcard:TEL>0000003</vcard:TEL>
            <foaf:interest rdf:resource="http://www.w3.org/2001/sw/"/>
            <vcard:Given>Edoardo</vcard:Given>
            <foaf:weblog rdf:resource="http://fleanend.github.io/"/>
            <vcard:Family>Ferrante</vcard:Family>
            <foaf:holdsAccount>
              <foaf:OnlineAccount>
                <foaf:accountName>fleanend</foaf:accountName>
                <foaf:accountServiceHomepage rdf:resource="https://twitter.com/"/>
              </foaf:OnlineAccount>
            </foaf:holdsAccount>
            <rdf:type rdf:resource="http://xmlns.com/foaf/0.1/Person"/>
          </foaf:Agent>
        </foaf:maker>
      </rss:channel>
    </rdfs:seeAlso>
    <dc:title>Not Really Fleanend's Blog</dc:title>
  </foaf:Document>
  <rss:channel rdf:about="http://fedexist.github.io/rss">
    <foaf:topic rdf:resource="http://www.w3.org/2001/sw/"/>
    <foaf:maker>
      <foaf:Person>
        <foaf:holdsAccount>
          <foaf:OnlineAccount>
            <foaf:accountName>fedexist</foaf:accountName>
            <foaf:accountServiceHomepage rdf:resource="https://twitter.com/"/>
          </foaf:OnlineAccount>
        </foaf:holdsAccount>
        <vcard:TEL>0000001</vcard:TEL>
        <j.0:likes>http://www.co-ode.org/ontologies/pizza/pizza.owl#LaReine</j.0:likes>
        <foaf:weblog>
          <foaf:Document rdf:about="http://fedexist.github.io/">
            <rdfs:seeAlso rdf:resource="http://fedexist.github.io/rss"/>
            <dc:title>Not Really Fedexist's Blog</dc:title>
          </foaf:Document>
        </foaf:weblog>
        <foaf:name>Federico D'Ambrosio</foaf:name>
        <foaf:holdsAccount>
          <foaf:OnlineAccount>
            <foaf:accountName>fedexist</foaf:accountName>
            <foaf:accountServiceHomepage rdf:resource="https://github.com/"/>
          </foaf:OnlineAccount>
        </foaf:holdsAccount>
        <foaf:interest rdf:resource="http://www.w3.org/2001/sw/"/>
        <vcard:Given>Federico</vcard:Given>
        <vcard:FN>Federico D'Ambrosio</vcard:FN>
        <vcard:Family>D'Ambrosio</vcard:Family>
        <rdf:type rdf:resource="http://xmlns.com/foaf/0.1/Agent"/>
      </foaf:Person>
    </foaf:maker>
  </rss:channel>
  <rss:channel rdf:about="https://www.reddit.com/r/dataisbeautiful.rss">
    <foaf:topic rdf:resource="http://www.w3.org/community/datavis"/>
    <foaf:topic rdf:resource="http://www.w3.org/2001/sw/"/>
    <foaf:maker>
      <foaf:Agent>
        <j.0:likes>http://www.co-ode.org/ontologies/pizza/pizza.owl#Napoletana</j.0:likes>
        <foaf:interest rdf:resource="http://www.w3.org/community/datavis"/>
        <foaf:interest rdf:resource="http://www.w3.org/2001/sw/"/>
        <foaf:weblog>
          <foaf:Document rdf:about="https://www.reddit.com/r/dataisbeautiful/">
            <rdfs:seeAlso rdf:resource="https://www.reddit.com/r/dataisbeautiful.rss"/>
            <dc:title>DataIsBeautiful</dc:title>
          </foaf:Document>
        </foaf:weblog>
        <foaf:name>Reddit - DataIssBeautiful</foaf:name>
      </foaf:Agent>
    </foaf:maker>
  </rss:channel>
  <foaf:Document rdf:about="http://cicciopasticcio.blogspot.com/">
    <rdfs:seeAlso>
      <rss:channel rdf:about="http://cicciopasticcio.blogspot.com/rss">
        <foaf:topic rdf:resource="http://www.w3.org/2001/sw/"/>
        <foaf:maker>
          <foaf:Agent>
            <foaf:name>Ciccio Pasticcio</foaf:name>
            <foaf:interest rdf:resource="http://www.w3.org/2001/sw/"/>
            <j.0:likes>http://www.co-ode.org/ontologies/pizza/pizza.owl#Veneziana</j.0:likes>
            <rdf:type rdf:resource="http://xmlns.com/foaf/0.1/Person"/>
            <foaf:holdsAccount>
              <foaf:OnlineAccount>
                <foaf:accountName>realDonaldTrump</foaf:accountName>
                <foaf:accountServiceHomepage rdf:resource="https://twitter.com/"/>
              </foaf:OnlineAccount>
            </foaf:holdsAccount>
            <vcard:Family>Pasticcio</vcard:Family>
            <vcard:Given>Ciccio</vcard:Given>
            <foaf:weblog rdf:resource="http://cicciopasticcio.blogspot.com/"/>
            <vcard:TEL>1234567</vcard:TEL>
            <vcard:FN>Ciccio Pasticcio</vcard:FN>
          </foaf:Agent>
        </foaf:maker>
      </rss:channel>
    </rdfs:seeAlso>
    <dc:title>Not Really Ciccio's Blog</dc:title>
  </foaf:Document>
  <rss:channel rdf:about="http://grebbo.github.io/rss">
    <foaf:topic rdf:resource="http://www.w3.org/2001/sw/"/>
    <foaf:maker>
      <foaf:Agent>
        <vcard:FN>Enrico Ferro</vcard:FN>
        <foaf:holdsAccount>
          <foaf:OnlineAccount>
            <foaf:accountName>grebbo</foaf:accountName>
            <foaf:accountServiceHomepage rdf:resource="https://github.com/"/>
          </foaf:OnlineAccount>
        </foaf:holdsAccount>
        <foaf:weblog>
          <foaf:Document rdf:about="http://grebbo.github.io/">
            <rdfs:seeAlso rdf:resource="http://grebbo.github.io/rss"/>
            <dc:title>Not Really Enrico's Blog</dc:title>
          </foaf:Document>
        </foaf:weblog>
        <vcard:Given>Enrico</vcard:Given>
        <vcard:Family>Ferro</vcard:Family>
        <j.0:likes>http://www.co-ode.org/ontologies/pizza/pizza.owl#SloppyGiuseppe</j.0:likes>
        <rdf:type rdf:resource="http://xmlns.com/foaf/0.1/Person"/>
        <vcard:TEL>0000002</vcard:TEL>
        <foaf:name>Enrico Ferro</foaf:name>
        <foaf:interest rdf:resource="http://www.w3.org/2001/sw/"/>
      </foaf:Agent>
    </foaf:maker>
  </rss:channel>
</rdf:RDF>