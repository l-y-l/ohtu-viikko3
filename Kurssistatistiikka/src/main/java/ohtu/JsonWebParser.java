
package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonWebParser {
    private final Gson mapper;
    private final JsonParser parser;

    public JsonWebParser() {
        mapper = new Gson();
        parser = new JsonParser();
    }

    public Submission[] parseStudent(String studentNr) throws IOException {
        //Get url
        String url = "https://studies.cs.helsinki.fi/courses/students/"+studentNr+"/submissions";

        //Get data as Json string
        String jsonString = Request.Get(url).execute().returnContent().asString();

        //Map Json to array of Submissions
        return mapper.fromJson(jsonString, Submission[].class);
    }
    
    public Course[] parseCourses() throws IOException {
        //Get url
        String url = "https://studies.cs.helsinki.fi/courses/courseinfo";

        //Get data as Json string
        String jsonString = Request.Get(url).execute().returnContent().asString();
        
        //Course array
        return mapper.fromJson(jsonString, Course[].class);
    }
    
    public JsonObject parseCourseStats(String course) throws IOException {
        //Get url
        String url = "https://studies.cs.helsinki.fi/courses/"+course+"/stats";

        //Get data as Json string
        String statsResponse = Request.Get(url).execute().returnContent().asString();
        
        //Parse Json as JsonObject
        return parser.parse(statsResponse).getAsJsonObject();
    }
}
