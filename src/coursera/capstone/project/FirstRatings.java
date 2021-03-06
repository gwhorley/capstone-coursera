package coursera.capstone.project;

/*
 * FirstRatings.java
 * @author Greg Whorley
 * @version 1.0
 */

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;

public class FirstRatings {

    public FirstRatings() {
    }

    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movieArrayList = new ArrayList<Movie>();
        CSVParser csvParser = getParserFromFile(filename);
        for (CSVRecord csvRecord: csvParser) {
            movieArrayList.add(new Movie(csvRecord.get("id"),csvRecord.get("title"),csvRecord.get("year"),
                    csvRecord.get("genre"),csvRecord.get("director"),csvRecord.get("country"),
                    csvRecord.get("poster"),Integer.parseInt(csvRecord.get("minutes"))));
        }
        return movieArrayList;
    }

    public ArrayList<EfficientRater> loadRaters(String filename) {
        ArrayList<EfficientRater> raterArrayList = new ArrayList<EfficientRater>();
        CSVParser csvParser = getParserFromFile(filename);

        for (CSVRecord csvRecord : csvParser) {
            EfficientRater raterFromCsvRecord = new EfficientRater(csvRecord.get("rater_id"));
            int indexOfRater = findIndexOfRaterInArray(raterArrayList, raterFromCsvRecord);
            if (indexOfRater == -1) {
                raterFromCsvRecord.addRating(csvRecord.get("movie_id"), Double.parseDouble(csvRecord.get("rating")));
                raterArrayList.add(raterFromCsvRecord);
            }
            else {
                EfficientRater existingRaterInArray = raterArrayList.get(indexOfRater);
                existingRaterInArray.addRating(csvRecord.get("movie_id"), Double.parseDouble(csvRecord.get("rating")));
            }
        }
        return raterArrayList;
    }

    private CSVParser getParserFromFile(String filename) {
        FileResource fileResource = new FileResource(filename);
        return fileResource.getCSVParser();
    }

    private int findIndexOfRaterInArray(ArrayList<EfficientRater> raterList, EfficientRater rater) {
        for (int index = 0; index < raterList.size(); index++) {
            EfficientRater raterFromList = raterList.get(index);
            if (raterFromList.getID().contains(rater.getID())) {
                return index;
            }
        }
        return -1;
    }

}