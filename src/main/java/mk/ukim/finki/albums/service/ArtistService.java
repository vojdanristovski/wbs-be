package mk.ukim.finki.albums.service;

import mk.ukim.finki.albums.model.Artist;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistService extends FetchService<Artist> {
    @Override
    public List<Artist> getDataList(String url) {
        return null;
    }

    @Override
    public Artist getDataDetails(String url) {
        JSONArray arr = this.getData(url);
        return this.getArtistFromJson(arr);
    }

    private Artist getArtistFromJson(JSONArray jsonArray){
        JSONObject jsonObj = (JSONObject) jsonArray.get(0);
        String desc = this.getFromJson(jsonObj,"desc");
        String name = this.getFromJson(jsonObj,"name");
        String birthPlace = this.getFromJson(jsonObj,"birthPlace");
        String birthDate = this.getFromJson(jsonObj,"birthDate");
        String thumbnail = this.getFromJson(jsonObj,"thumbnail");
        List<String> songs = jsonArray.stream().map(a->this.getFromJson((JSONObject) a,"songs").split("/")[4]).collect(Collectors.toList());

        return new Artist(desc,name,birthPlace,birthDate,thumbnail,songs);

    }
}
