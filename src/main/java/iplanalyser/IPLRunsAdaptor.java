package iplanalyser;

import java.util.Map;

public class IPLRunsAdaptor extends IplAdaptor {
    @Override
    public Map<String, IplDTO> loadIplData(String csvFilePath) {
        Map<String, IplDTO> iplMap = super.loadIplData(IplRunsCSV.class, csvFilePath);
        return iplMap;
    }
}
