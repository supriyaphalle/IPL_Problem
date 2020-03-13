package iplanalyser;

public class IPLAnalyserException extends RuntimeException {


    ExceptionType type;

    public IPLAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    enum ExceptionType {
        NO_CENSUS_DATA, IPL_DATA_FILE_PROBLEM, INVALID_DATA, CENSUS_FILE_PROBLEM;
    }
}
