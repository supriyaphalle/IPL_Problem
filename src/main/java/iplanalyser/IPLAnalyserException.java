package iplanalyser;

public class IPLAnalyserException extends Throwable {


    enum ExceptionType {
        UNABLE_TO_PARSE, NO_CENSUS_DATA, IPLDATA_FILE_PROBLEM,INVALID_DATA ;
    }

    ExceptionType type;

    public IPLAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public IPLAnalyserException(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }
}
