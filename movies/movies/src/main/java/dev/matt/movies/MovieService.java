package dev.matt.movies;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    // need a reference of the repository
    @Autowired // to instantiate a class of MovieRepository
    private MovieRepository movieRepository;

    public List<Movie> allMovies() {
        return movieRepository.findAll();
    }

    // Optional means it may return null, possible because no such movie id exists
    public Optional<Movie> singleMovie(ObjectId id) {
        return movieRepository.findById(id);
    }

}
