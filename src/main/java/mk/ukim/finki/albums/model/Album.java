package mk.ukim.finki.albums.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {
    private Integer released;
    private String name;
    private String artist;
    private String description;
    private Integer id;
    private String thumbnail;
}
