package dev.matt.movies;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "movies") // represents each movie in the collection
@Data // Add getter, setter & toString method to each private property
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id //this property should be treated as unique identifier for each movie in the database
    private ObjectId id;

    private String imdbId;

    private String title;

    private String releaseDate;

    private String trailerLink;

    private String poster;

    private List<String> genres;

    private List<String> backdrops;

    @DocumentReference
    // Database will only store the Id of the reviews. The reviews will be in a separate collection
    // this is called a manual reference relationship
    private List<Review> reviewIds;
    // this will be an embedded relationship

}
