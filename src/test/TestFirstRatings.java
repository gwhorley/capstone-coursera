package test;

import coursera.capstone.project.FirstRatings;
import coursera.capstone.project.Movie;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TestFirstRatings {
    protected FirstRatings firstRatings;
    protected String short_filename, long_filename;
    protected ArrayList<Movie> movieArrayList;

    protected void setUp() {
        short_filename = "/home/greg/IdeaProjects/capstone-coursera/data/ratedmovies_short.csv";
        long_filename = "/home/greg/IdeaProjects/capstone-coursera/data/ratedmoviesfull.csv";
        firstRatings = new FirstRatings();
    }

    @Test
    public void testLoadMoviesSmallArraySize() {
        movieArrayList = firstRatings.loadMovies(short_filename);
        assertEquals(5,movieArrayList.size());
    }

    @Test
    public void testLoadMoviesLargeArraySize() {
        movieArrayList = firstRatings.loadMovies(long_filename);
        assertEquals(3143, movieArrayList.size());
    }

    @Test
    public void testComedyGenreInArray() {
        movieArrayList = firstRatings.loadMovies(short_filename);
        int numberOfComedies = 0;
        for (Movie movie : movieArrayList) {
            if (movie.getGenres().contains("Comedy")) {
                numberOfComedies++;
            }
        }
        assertEquals(1, numberOfComedies);
    }

    @Test
    public void testMovieLengthInArray() {
        movieArrayList = firstRatings.loadMovies(short_filename);
        int moviesGreaterThan150Minutes = 0;
        for (Movie movie : movieArrayList) {
            if (movie.getMinutes() > 150) {
                moviesGreaterThan150Minutes++;
            }
        }
        assertEquals(2, moviesGreaterThan150Minutes);
    }

    @Test
    public void testMaxNumberOfDirectors() {
        movieArrayList = firstRatings.loadMovies(short_filename);
        HashMap<String, Integer> directorMap = getMapOfDirectorsToNumberOfFilms(movieArrayList);
        String biggestDirectorName = getDirectorWithMostFilms(directorMap);
        int numberOfFilms = directorMap.get(biggestDirectorName);
        assertEquals(1, numberOfFilms);
    }

    private HashMap<String, Integer> getMapOfDirectorsToNumberOfFilms(ArrayList<Movie> movieList) {
        HashMap<String, Integer> directorMap = new HashMap<String, Integer>();
        for (Movie movie : movieList) {
            if (!directorMap.containsKey(movie.getDirector())) {
                directorMap.put(movie.getDirector(), 1);
            } else if (directorMap.containsKey(movie.getDirector())) {
                int currentValue = directorMap.get(movie.getDirector());
                directorMap.replace(movie.getDirector(), currentValue, currentValue + 1);
            }
        }
        return directorMap;
    }

    private String getDirectorWithMostFilms(HashMap<String, Integer> directorMap) {
        int biggestDirectorTotal = 0;
        String biggestDirectorName = null;
        for (String director : directorMap.keySet()) {
            if (biggestDirectorName == null && biggestDirectorTotal == 0) {
                biggestDirectorName = director;
                biggestDirectorTotal = directorMap.get(director);
            } else if (directorMap.get(director) > biggestDirectorTotal) {
                biggestDirectorName = director;
                biggestDirectorTotal = directorMap.get(director);
            }
        }
        return biggestDirectorName;
    }

}