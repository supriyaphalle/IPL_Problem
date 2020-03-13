package iplanalyser;

import java.util.Map;

public class IplAdaptorFactory {
    public Map<String, IplDTO> getIplData(IplAnalyser.CSVType type, String... filePath) {
        if (type.equals(IplAnalyser.CSVType.RUNS)) {
            return new IplRunsAdaptor().loadIplData(filePath);
        }
        if (type.equals(IplAnalyser.CSVType.WICKETS)) {
            return new IplWKTAdaptor().loadIplData(filePath);
        }
        throw new IplAnalyserException("INVALID CSV TYPE", IplAnalyserException.ExceptionType.INVALID_DATA);


    }
}


