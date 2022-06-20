package mk.ukim.finki.albums.rest;


import mk.ukim.finki.albums.model.Album;
import mk.ukim.finki.albums.model.AlbumDetail;
import mk.ukim.finki.albums.model.Artist;
import mk.ukim.finki.albums.queries.Sparql;
import mk.ukim.finki.albums.service.AlbumDetailService;
import mk.ukim.finki.albums.service.AlbumService;
import mk.ukim.finki.albums.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
@CrossOrigin("*")
public class AlbumController {
    @Autowired
   public AlbumService albumService;
    @Autowired
    public AlbumDetailService detailService;
    @Autowired
    public ArtistService artistService;
@GetMapping("/")
public List<Album> getAll(){
    return this.albumService.getDataList(Sparql.getAlbums());
}

@GetMapping("/{id}")
    public AlbumDetail findById(@PathVariable Integer id){
    return this.detailService.getDataDetails(Sparql.getAlbumDetails(id));
}
@GetMapping("/artist/{name}")
    public Artist findByName(@PathVariable String name){
    return this.artistService.getDataDetails(Sparql.getArtistDetails(name));
}
}
