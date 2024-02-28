package dev.matt.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MongoTemplate mongoTemplate;
    // template and repositories are ways to talk to the database
    // can use template to form up a new dynamic query & do the job inside database without using repository

    public Review createReview(String imdbId, String reviewBody) {
        Review review = reviewRepository.insert(new Review(reviewBody));

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();
        // each movie in the collection contains an empty array of review ids
        // use the template to perform an update call on movie class
        // update the array and push a new review id into it
        // ".first()" makes sure that we are getting a single movie & updating it

        return review;
    }
}
