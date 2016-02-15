package coursera.capstone.project;

import java.util.ArrayList;

/**
 * Created by greg on 2/15/2016.
 */
public class FourthRatings {
    public FourthRatings() {
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ratingArrayList = new ArrayList<>();
        ArrayList<String> movieIdList = MovieDatabase.filterBy(new TrueFilter());
        for (String movieId : movieIdList) {
            double averageRating = getAverageByID(movieId, minimalRaters);
            if (averageRating != 0.0) {
                ratingArrayList.add(new Rating(movieId, averageRating));
            }
        }
        return ratingArrayList;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter criteriaFilter) {
        ArrayList<Rating> ratingArrayList = new ArrayList<>();
        ArrayList<String> movieList = MovieDatabase.filterBy(criteriaFilter);
        for (String aMovieList : movieList) {
            double averageRating = getAverageByID(aMovieList, minimalRaters);
            if (averageRating != 0.0) {
                ratingArrayList.add(new Rating(aMovieList, averageRating));
            }
        }
        return ratingArrayList;
    }

    private double getAverageByID(String movieId, int minimalRaters) {
        double totalRatings = 0.0;
        int numberOfRatings = 0;
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        for (Rater rater : myRaters) {
            if (rater.hasRating(movieId)) {
                numberOfRatings++;
                totalRatings += rater.getRating(movieId);
            }
        }
        if (numberOfRatings < minimalRaters) {
            return 0.0;
        }
        return totalRatings / (double) numberOfRatings;
    }

    private double dotProduct(Rater me, Rater rater) {
        /*
        This method should first translate a rating from the scale 0 to 10 to the scale -5 to 5 and return
        the dot product of the ratings of movies that they both rated. This method will be called by getSimilarities.
         */

        return 0.0;
    }

    private ArrayList<Rating> getSimilarities(String id) {
        /*
        This method computes a similarity rating for each rater in the RaterDatabase (except the rater with
        the ID given by the parameter) to see how similar they are to the Rater whose ID is the parameter to
        getSimilarities. This method returns an ArrayList of type Rating sorted by ratings from highest to
        lowest rating with the highest rating first and only including those raters who have a positive
        similarity rating since those with negative values are not similar in any way. Note that in each
        Rating object the item field is a rater’s ID, and the value field is the dot product comparison
        between that rater and the rater whose ID is the parameter to getSimilarities. Be sure not to use
        the dotProduct method with parameter id and itself!
         */
        return null;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        /*
        This method should return an ArrayList of type Rating, of movies and their weighted average ratings using
         only the top numSimilarRaters with positive ratings and including only those movies that have at least
         minimalRaters ratings from those top raters. These Rating objects should be returned in sorted order by
         weighted average rating from largest to smallest ratings. This method is very much like the getAverageRatings
         method you have written previously. In particular this method should:

        For every rater, get their similarity rating to the given parameter rater id. Include only those raters with
         positive similarity ratings—those that are more similar to rater id. Which method could you call?

        For each movie, calculate a weighted average movie rating based on: Use only the top (largest) numSimilarRaters
         raters. For each of these raters, multiply their similarity rating by the rating they gave that movie. This
         will emphasize those raters who are closer to the rater id, since they have greater weights. The weighted
         average movie rating for a particular movie is the sum of these weighted average ratings (for each rater
         multiply their similarity rating by their rating for the movie), divided by the total number of such ratings.

        This method returns an ArrayList of Ratings for movies and their calculated weighted ratings, in sorted order.
         */

        return null;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters,
                                                       Filter filterCriteria) {
        /*
        Is similar to the getSimilarRatings method but has one additional Filter parameter named filterCriteria
        and uses that filter to access and rate only those movies that match the filter criteria.
         */
        return null;
    }
}