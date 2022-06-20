package mk.ukim.finki.albums.service;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class FetchService<T> {


    public abstract List<T> getDataList(String url);

    public abstract T getDataDetails(String url);

    protected final JSONArray getData(String url) {
        JSONObject json = this.getJsonDataFromEndpoint(url);
        return this.getResultsRows(json);
    }

    protected String getFromJson(JSONObject json, String value) {
        JSONObject object = (JSONObject) json.get(value);
        if (object != null) return (String) object.get("value");
        return "";
    }

    private JSONObject getJsonDataFromEndpoint(String url) {
        JSONObject json = null;
        try {
            HttpClient client = HttpClients.custom().build();
            HttpUriRequest request = RequestBuilder.get()
                    .setUri(url)
                    .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    .build();
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            JSONParser parser = new JSONParser();
            json = (JSONObject) parser.parse(responseString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return json;
    }

    private JSONArray getResultsRows(JSONObject json) {
        JSONObject results = (JSONObject) json.get("results");
        return (JSONArray) results.get("bindings");
    }

}