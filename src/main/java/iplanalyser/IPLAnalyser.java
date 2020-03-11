package iplanalyser;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class IPLAnalyser {
    Map<sortField, Comparator<IplDTO>> sortedMap = null;
    Map<String, IplDTO> iplMap = new HashMap<>();
    private List<IplDTO> iplDTOList;
    private IplDTO census;

    public enum CSVType {
        RUNS, WICKETS;
    }

    public IPLAnalyser() {
        sortedMap = new HashMap<>();
        Comparator<IplDTO> avgStrike = Comparator.comparing((census -> census.avg));
        this.sortedMap.put(sortField.AVERAGE_STRIKE, avgStrike.thenComparing(census -> census.strikeRate));
        this.sortedMap.put(sortField.AVERAGE, Comparator.comparing(census -> census.avg));
        this.sortedMap.put(sortField.STRIKE_RATE, Comparator.comparing(census -> census.strikeRate));
        this.sortedMap.put(sortField.FourAndSix, Comparator.comparing(census -> census.four + census.six));
        this.sortedMap.put(sortField.SIX_FOUR, new compareSixAndFours().thenComparing((census -> census.strikeRate)));

        this.sortedMap.put(sortField.RUNS, Comparator.comparing(census -> census.runs));
        this.sortedMap.put(sortField.ECONOMY, Comparator.comparing(census -> census.economy));
        this.sortedMap.put(sortField.FIVE_FOUR, new compareFiveAndFours().thenComparing((census -> census.strikeRate)));

        Comparator<IplDTO> runAverage = Comparator.comparing((census -> census.runs));
        this.sortedMap.put(sortField.RUNS_AVERAGE, avgStrike.thenComparing(census -> census.avg));

    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Ipl- 2K19 Analyser");
    }

    public void loadIplData(CSVType type, String FilePath) throws IPLAnalyserException {
        iplMap = new IplAdaptorFactory().getIplData(type, FilePath);
    }

    public String getSortedIPLData(sortField field) throws IPLAnalyserException {
        if (iplMap == null || iplMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        iplDTOList = iplMap.values().stream().collect(Collectors.toList());
        this.sort(iplDTOList, this.sortedMap.get(field).reversed());
        String sortedStateCensusJson = new Gson().toJson(iplDTOList);
        return sortedStateCensusJson;
    }


    private void sort(List<IplDTO> iplDTOList, Comparator<IplDTO> iplCSVComparator) {
        for (int i = 0; i < this.iplDTOList.size() - 1; i++) {
            for (int j = 0; j < this.iplDTOList.size() - i - 1; j++) {
                IplDTO census1 = this.iplDTOList.get(j);
                IplDTO census2 = this.iplDTOList.get(j + 1);
                if (iplCSVComparator.compare(census1, census2) > 0) {
                    this.iplDTOList.set(j, census2);
                    this.iplDTOList.set(j + 1, census1);
                }
            }
        }
    }


}




