package mk.ukim.finki.albums.service;

import mk.ukim.finki.albums.model.Album;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumService extends FetchService<Album> {

    @Override
    public List<Album> getDataList(String url) {
        JSONArray jsonArray = this.getData(url);

        return this.getAlbumsFromJSON(jsonArray);
    }

    @Override
    public Album getDataDetails(String url) {
        return null;
    }


    private List<Album> getAlbumsFromJSON(JSONArray jsonArray) {
        List<Album> albums = jsonArray.stream().map((jsonAlbum) -> {
            String name = this.getFromJson((JSONObject) jsonAlbum, "name");
            String artist = this.getFromJson((JSONObject) jsonAlbum, "artist");
            String descripton = this.getFromJson((JSONObject) jsonAlbum, "description");
            Integer released = Integer.parseInt(this.getFromJson((JSONObject) jsonAlbum, "released"));
            Integer id = Integer.parseInt(this.getFromJson((JSONObject) jsonAlbum, "id"));
            String thumbnail = this.getFromJson((JSONObject) jsonAlbum, "thumbnail");


            Album album = new Album(released,name,artist,descripton,id,thumbnail);
            return album;
        }).collect(Collectors.toList());
        return albums;
    }

}
