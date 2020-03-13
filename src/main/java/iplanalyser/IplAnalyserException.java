package iplanalyser;

public class IplAnalyserException extends RuntimeException {


    ExceptionType type;

    public IplAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    enum ExceptionType {
        NO_CENSUS_DATA, IPL_DATA_FILE_PROBLEM, INVALID_DATA, CENSUS_FILE_PROBLEM;
    }
}
