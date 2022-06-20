package mk.ukim.finki.albums.service;

import mk.ukim.finki.albums.model.AlbumDetail;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumDetailService extends FetchService<AlbumDetail>{

    @Override
    public List<AlbumDetail> getDataList(String url) {
        return null;
    }

    @Override
    public AlbumDetail getDataDetails(String url) {
        JSONArray arr = this.getData(url);
        return this.getAlbumFromJSON(arr);
    }

        private AlbumDetail getAlbumFromJSON(JSONArray jsonArray){
        JSONObject album = (JSONObject) jsonArray.get(0);
        Integer released = Integer.parseInt(this.getFromJson(album,"released"));
        String name = this.getFromJson(album,"name");
        String artist = this.getFromJson(album,"artist");
        String description = this.getFromJson(album,"description");
        String genre = this.getFromJson(album,"genre");
        List<String> songs = jsonArray.stream().map(a -> this.getFromJson((JSONObject) a,"songs")).collect(Collectors.toList());
        return new AlbumDetail(released,name,artist,description,genre,songs);
    }
}
