package iplanalyser;

import java.util.Map;

public class IPLWKTAdaptor extends IplAdaptor {
    @Override
    public Map<String, IplDTO> loadIplData(String csvFilePath) {
        Map<String, IplDTO> iplMap = super.loadIplData(IplWKTsCSV.class, csvFilePath);
        return iplMap;
    }
}
