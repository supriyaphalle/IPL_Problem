package iplanalyser;

import java.util.Map;

public class IplAdaptorFactory {
    public Map<String, IplDTO> getIplData(IPLAnalyser.CSVType type, String filePath) {
        if (type.equals(IPLAnalyser.CSVType.RUNS)) {
            return new IPLRunsAdaptor().loadIplData(filePath);
        }
        if (type.equals(IPLAnalyser.CSVType.WICKETS)) {
            return new IPLWKTAdaptor().loadIplData(filePath);
        }
        throw new IPLAnalyserException("INVALID CSV TYPE", IPLAnalyserException.ExceptionType.INVALID_DATA);


    }
}


