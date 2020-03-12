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


        this.sortedMap.put(sortField.BOLLING_AVERAGE, Comparator.comparing(census -> census.avgBolling));

        Comparator<IplDTO> strike = Comparator.comparing((census -> census.avgBolling));
        this.sortedMap.put(sortField.AVG_STRIKE, strike.thenComparing(census -> census.strikeRate));

        Comparator<IplDTO> runAverage = Comparator.comparing((census -> census.runs));
        this.sortedMap.put(sortField.RUNS_AVERAGE, runAverage.thenComparing(census -> census.avg));

        Comparator<IplDTO> wicketAverage = Comparator.comparing((census -> census.Wicket));
        this.sortedMap.put(sortField.WICKET_AVERAGE, wicketAverage.thenComparing(census ->census.avgBolling));

        this.sortedMap.put(sortField.BATTINGBOLLING_AVERAGE, new CompareAverages());
        this.sortedMap.put(sortField.WICKETS, new CompareAverages());

//        this.sortedMap.put(sortField.BATTINGBOLLING_AVERAGE, Comparator.comparing(census -> (census.avgBolling + census.avg)/2));
    /*    Comparator<IplDTO> Average = Comparator.comparing((census -> census.avg));
        this.sortedMap.put(sortField.BATTINGBOLLING_AVERAGE, Average.thenComparing(census -> census.avgBolling));
*/
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Ipl- 2K19 Analyser");
    }

    public void loadIplData(CSVType type, String... FilePath) throws IPLAnalyserException {
        iplMap = new IplAdaptorFactory().getIplData(type, FilePath);
  //      new LoadData().loadIplWktData(iplMap,FilePath[1]);

       // System.out.println(iplMap.size());
    }

    public void loadData(sortField type , String... FilePath){
        this.loadIplData(CSVType.RUNS,FilePath[0]);
        iplMap =  new LoadData().loadIplWktData(iplMap, type,  FilePath[1]);
    }

    public String getSortedIPLData(sortField field) throws IPLAnalyserException {
        if (iplMap == null || iplMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
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
                IplDTO census1 = this.iplDTOList.get(j);
                IplDTO census2 = this.iplDTOList.get(j + 1);
                if (iplCSVComparator.compare(census1, census2) > 0) {
                    this.iplDTOList.set(j, census2);
                    this.iplDTOList.set(j + 1, census1);
                }
            }
        }
        System.out.println(iplDTOList);
    }


}


/*

    public String getSortedCricketData(SortedField sortedField) {
        cricketDTOCSVList = cricketCsvDtoMap.values().stream().collect(Collectors.toList());
        if(cricketDTOCSVList == null || cricketDTOCSVList.size() == 0){
            throw new CricketAnalyserException("No Data",CricketAnalyserException.ExceptionType.CRICKET_DATA_NOT_FOUND);
        }
        this.sort(this.sortedMap.get(sortedField));
        Collections.reverse(cricketDTOCSVList);
        String sortedStateCensus=new Gson().toJson(cricketDTOCSVList);
        return sortedStateCensus;
    }

    private void sort(Comparator<CricketCsvDto> cricketComparator) {
        for(int i = 0; i< this.cricketDTOCSVList.size()-1; i++){
            for(int j = 0; j< this.cricketDTOCSVList.size()-i-1; j++){
                CricketCsvDto run1 = this.cricketDTOCSVList.get(j);
                CricketCsvDto run2 = this.cricketDTOCSVList.get(j+1);
                if(cricketComparator.compare(run1,run2)>0){
                    cricketDTOCSVList.set(j,run2);
                    cricketDTOCSVList.set(j+1,run1);
                }
            }
        }
    }
*/

