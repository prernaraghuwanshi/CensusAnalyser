package censusanalyser;

public class CSVException extends Exception {
    enum ExceptionType {
        UNABLE_TO_PARSE, CSV_FILE_PROBLEM;
    }

    CSVException.ExceptionType type;

    public CSVException(String message, CSVException.ExceptionType type) {
        super(message);
        this.type = type;
    }

}
