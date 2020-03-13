package iplanalyser;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class IplAnalyser {
    Map<SortField, Comparator<IplDTO>> sortedMap = null;
    Map<String, IplDTO> iplMap = new HashMap<>();
    private List<IplDTO> iplDTOList;

    public enum CSVType {
        RUNS, WICKETS;
    }

    public IplAnalyser() {
        sortedMap = new HashMap<>();
        this.sortedMap.put(SortField.AVERAGE, Comparator.comparing(census -> census.avg));
        this.sortedMap.put(SortField.STRIKE_RATE, Comparator.comparing(census -> census.strikeRate));
        this.sortedMap.put(SortField.FourAndSix, Comparator.comparing(census -> census.four + census.six));
        this.sortedMap.put(SortField.SIX_FOUR, new CompareSixAndFours().thenComparing((census -> census.strikeRate)));
        this.sortedMap.put(SortField.RUNS, Comparator.comparing(census -> census.runs));
        this.sortedMap.put(SortField.ECONOMY, Comparator.comparing(census -> census.economy));
        this.sortedMap.put(SortField.FIVE_FOUR, new CompareFiveAndFours().thenComparing((census -> census.strikeRate)));
        this.sortedMap.put(SortField.BOLLING_AVERAGE, Comparator.comparing(census -> census.avgBolling));

        Comparator<IplDTO> avgStrike = Comparator.comparing((census -> census.avg));
        this.sortedMap.put(SortField.AVERAGE_STRIKE, avgStrike.thenComparing(census -> census.strikeRate));

        Comparator<IplDTO> strike = Comparator.comparing((census -> census.avgBolling));
        this.sortedMap.put(SortField.AVG_STRIKE, strike.thenComparing(census -> census.strikeRate));

        Comparator<IplDTO> runAverage = Comparator.comparing((census -> census.runs));
        this.sortedMap.put(SortField.RUNS_AVERAGE, runAverage.thenComparing(census -> census.avg));

        Comparator<IplDTO> wicketAverage = Comparator.comparing((census -> census.wicket));
        this.sortedMap.put(SortField.WICKET_AVERAGE, wicketAverage.thenComparing(census -> census.avgBolling));

        this.sortedMap.put(SortField.BATTINGBOLLING_AVERAGE, new CompareAverages());
        this.sortedMap.put(SortField.WICKETS, new CompareAllrounder());
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Ipl- 2K19 Analyser");
    }

    public void loadIplData(CSVType type, String... FilePath) throws IplAnalyserException {
        iplMap = new IplAdaptorFactory().getIplData(type, FilePath);
    }

    public String getSortedIPLData(SortField field) throws IplAnalyserException {
        if (iplMap == null || iplMap.size() == 0) {
            throw new IplAnalyserException("No Census Data", IplAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        iplDTOList = iplMap.values().stream().collect(Collectors.toList());
        this.sort(iplDTOList, this.sortedMap.get(field));
        Collections.reverse(iplDTOList);
        String sortedStateCensusJson = new Gson().toJson(iplDTOList);
        return sortedStateCensusJson;
    }

    private void sort(List<IplDTO> iplDTOList, Comparator<IplDTO> iplCSVComparator) {
        for (int i = 0; i < this.iplDTOList.size() - 1; i++) {
            for (int j = 0; j < this.iplDTOList.size() - i - 1; j++) {
                IplDTO ipl1 = this.iplDTOList.get(j);
                IplDTO ipl2 = this.iplDTOList.get(j + 1);
                if (iplCSVComparator.compare(ipl1, ipl2) > 0) {
                    this.iplDTOList.set(j, ipl2);
                    this.iplDTOList.set(j + 1, ipl1);
                }
            }
        }
    }


}

