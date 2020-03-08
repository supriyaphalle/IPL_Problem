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
    Map<sortField, Comparator<IplDTO>> sortedMap=null;
    Map<String, IplDTO> iplMap =new HashMap<>();
    private List<IplDTO> iplDTOList;
    private IplDTO census;


    public IPLAnalyser() {
        sortedMap = new HashMap<>();
        this.sortedMap.put(sortField.AVERAGE,Comparator.comparing(census -> census.avg));
        this.sortedMap.put(sortField.STRIKE_RATE,Comparator.comparing(census -> census.strikeRate));
        this.sortedMap.put(sortField.FourAndSix,Comparator.comparing(census -> census.four + census.six));


    }

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
        this.sort(iplDTOList, this.sortedMap.get(field));
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




