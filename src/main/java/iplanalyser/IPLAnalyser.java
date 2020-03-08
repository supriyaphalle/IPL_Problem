package iplanalyser;

import CSVReader.CSVBuilderFactory;
import CSVReader.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class IPLAnalyser {
    Map<String, IplDTO> iplMap =new HashMap<>();
    private List<IplDTO> iplDTOList;
    private IplDTO census;

    public static void main(String[] args) {
        System.out.println("Welcome to the Ipl- 2K19 Analyser");
    }

    public void loadIplData(String FilePath) throws IPLAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(FilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator <IplRunsCSV> iterator = csvBuilder.getCSVFileIterator(reader, IplRunsCSV.class);
            Iterable< IplRunsCSV > csvIterable = () -> iterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .forEach(csvName -> iplMap.put(csvName.player,new IplDTO(csvName)));
            System.out.println(iplMap);
           // return iplMap.size();
        } catch (IOException ex) {
            throw new IPLAnalyserException(ex.getMessage(), IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    public String getSortedIPLData(sortField field) throws IPLAnalyserException {
        if (iplMap == null || iplMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data",IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        iplDTOList = iplMap.values().stream().collect(Collectors.toList());

       Comparator<IplDTO> iplCSVComparator = Comparator.comparing(census -> census.avg);
         this.sort(iplCSVComparator);       ;
    //    this.sort(iplDTOList, this.iplMap.get(field));
       // this.sort(iplDTOList, (Comparator<IplDTO>) this.iplMap.get(field));

//        this.sort(censusCSVComparator);
        String sortedStateCensusJson = new Gson().toJson(iplDTOList);
        return sortedStateCensusJson;
    }

    private void sort( Comparator<IplDTO> iplCSVComparator) {
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





   /* private void sort(Comparator<IplDTO> CSVComparator) {

        for (int i = 0; i < iplDTOList.size() - 1; i++) {
            for (int j = 0; j < iplDTOList.size() - i - 1; j++) {
                IplDTO census1 = iplDTOList.get(j);
                IplDTO census2 = iplDTOList.get(j + 1);
                if (CSVComparator.compare(census1, census2) > 0) {
                    iplDTOList.set(j, census2);
                    iplDTOList.set(j + 1, census1);
                }
            }
        }
    }*/


}
/*
   public String getSortedCensusData(sortedField sortField) {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        censusDTOList = censusMap.values().stream().collect(Collectors.toList());
        this.sort(censusDTOList, this.sortedMap.get(sortField));
        String sortedStateCensusJson = new Gson().toJson(censusDTOList);
        return sortedStateCensusJson;
    }



    private <E> Map loadIndianStateCodeData( String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaStateCodeCSV> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, IndiaStateCodeCSV.class);
            Iterable<IndiaStateCodeCSV> csvIterable = () -> censusCSVIterator;
            // StreamSupport.stream(csvIterable.spliterator(), false).count();
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .filter(csvState -> censusMap.get(csvState.state) != null)
                    .forEach(csvState -> censusMap.get(csvState.state).stateCode = csvState.stateCode);
            return censusMap;

        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }
*/




