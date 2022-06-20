package mk.ukim.finki.albums.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
    private String desc;
    private String name;
    private String birthPlace;
    private String birthDate;
    private String thumbnail;
    private List<String> songs;
}
