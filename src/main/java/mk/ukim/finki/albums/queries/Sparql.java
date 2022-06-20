package mk.ukim.finki.albums.queries;

import org.springframework.context.annotation.Configuration;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Configuration
public class Sparql {

    public static final String DBPEDIA_URL = "https://dbpedia.org/sparql";
    public static  final String DBPEDIA_GRAPH = "?default-graph-uri=http%3A%2F%2Fdbpedia.org&query=";
    public static final String DBPEDIA_END = "&format=application%2Fsparql-results%2Bjson&timeout=30000&signal_void=on&signal_unconnected=on";


   public static String getAlbums(){
        return DBPEDIA_URL + DBPEDIA_GRAPH +
                encodeValue("select distinct ?album ?released ?name ?artist ?description ?id ?thumbnail {" +
                        "?album a dbo:Album ;" +
                        "dbp:relyear ?released;" +
                        "dbp:name ?name;" +
                        "dbo:abstract ?description;" +
                        "dbo:thumbnail ?thumbnail;"+
                        "dbo:wikiPageID ?id;"+
                        "dbp:artist ?artist." +
                        "FILTER(isLiteral(?artist))"+
                        "FILTER(?released >= 2021 && ?released < 2023)" +
                        "FILTER(datatype(?released) = xsd:integer)"+
                        "FILTER(langMatches(lang(?description),\"en\"))"+
                        "}" +
                        "LIMIT 2000000")
                + DBPEDIA_END;
   }

   public static String getAlbumDetails(Integer id){
       return "https://dbpedia.org/sparql?default-graph-uri=http%3A%2F%2Fdbpedia.org&query=select+distinct+%3Falbum+%3Freleased+%3Fname+%3Fartist+%3Fdescription+%3Fsongs+%3Fgenre+%7B%0D%0A+%3Falbum+a+dbo%3AAlbum%3B%0D%0Adbp%3Arelyear+%3Freleased%3B%0D%0Adbp%3Aname+%3Fname%3B%0D%0Adbp%3Agenre+%3Fgenre%3B%0D%0Adbo%3Aabstract+%3Fdescription%3B%0D%0Adbp%3Aartist+%3Fartist%3B%0D%0Adbp%3Atitle+%3Fsongs%3B%0D%0Adbo%3AwikiPageID+"+id+".%0D%0AFILTER%28isLiteral%28%3Fartist%29%29%0D%0AFILTER%28isLiteral%28%3Fsongs%29%29%0D%0AFILTER%28langMatches%28lang%28%3Fdescription%29%2C%22en%22%29%29%0D%0A%7D&format=application%2Fsparql-results%2Bjson&timeout=30000&signal_void=on&signal_unconnected=on";

   }


   public static String getArtistDetails(String name){
       String sparqlName = name.replaceAll("\\s+","_");
       return "https://dbpedia.org/sparql?default-graph-uri=http%3A%2F%2Fdbpedia.org&query=select+distinct+%3Fdesc+%3Fname+%3FbirthPlace+%3FbirthDate+%3Fthumbnail+%3Fsongs%0D%0Awhere%7B%0D%0Adbr%3A"+sparqlName+"+dbo%3Aabstract+%3Fdesc%3B%0D%0A++++++++++++++++++++++foaf%3Aname+%3Fname%3B%0D%0A+++++++++++++++++++++dbp%3AbirthPlace+%3FbirthPlace%3B%0D%0A++++++++++++++++++++dbp%3AbirthDate+%3FbirthDate%3B%0D%0A++++++++++++++++++++dbo%3Athumbnail+%3Fthumbnail.%0D%0A%3Fsongs+dbo%3Aartist+dbr%3A"+sparqlName+".%0D%0Afilter%28langMatches%28lang%28%3Fdesc%29%2C%22en%22%29%29%0D%0A%7D&format=application%2Fsparql-results%2Bjson&timeout=30000&signal_void=on&signal_unconnected=on";
   }

    private static String encodeValue(String value) {
        String encodedValue = "";
        try {
            encodedValue = URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (Exception ex) {
            ex.getMessage();
        }
        return encodedValue;
    }
}

//
//    SELECT DISTINCT ?album ?released ?name  ?artist
//        {
//        ?album a dbo:Album ;
//        dbp:relyear ?released;
//        dbp:name ?name;
//        dbp:artist ?artist.
//        FILTER(isLiteral(?artist))
//        FILTER(?released >= 2021)
//        }
//        LIMIT 2000000

