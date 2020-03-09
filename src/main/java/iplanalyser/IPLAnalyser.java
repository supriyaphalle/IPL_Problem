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
    Map<sortField, Comparator<IplDTO>> sortedMap = null;
    Map<String, IplDTO> iplMap = new HashMap<>();
    private List<IplDTO> iplDTOList;
    private IplDTO census;


    public IPLAnalyser() {
        sortedMap = new HashMap<>();
        this.sortedMap.put(sortField.AVERAGE, Comparator.comparing(census -> census.avg));
        this.sortedMap.put(sortField.STRIKE_RATE, Comparator.comparing(census -> census.strikeRate));
        this.sortedMap.put(sortField.FourAndSix, Comparator.comparing(census -> census.four + census.six));
        this.sortedMap.put(sortField.RUNS, Comparator.comparing(census -> census.runs));
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Ipl- 2K19 Analyser");
    }

    public void loadIplRunsData(String FilePath) throws IPLAnalyserException {
        this.loadIplData(IplRunsCSV.class, FilePath);
    }

    public void loadIplWKTsData(String FilePath) throws IPLAnalyserException {
        this.loadIplData(IplWKTsCSV.class, FilePath);
    }

    public <E> void loadIplData(Class<E> IplCSV, String FilePath) throws IPLAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(FilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> iterator = csvBuilder.getCSVFileIterator(reader, IplCSV);
            Iterable<E> csvIterable = () -> iterator;
            if (IplCSV.getName() == "iplanalyser.IplRunsCSV") {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map((IplRunsCSV.class::cast))
                        .forEach(csvName -> iplMap.put(csvName.player, new IplDTO(csvName)));
            } else if (IplCSV.getName() == "iplanalyser.IplWKTsCSV") {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map((IplWKTsCSV.class::cast))
                        .forEach(csvName -> iplMap.put(csvName.player, new IplDTO(csvName)));
            }
        } catch (IOException ex) {
            throw new IPLAnalyserException(ex.getMessage(), IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }


    public String getSortedIPLData(sortField field) throws IPLAnalyserException {
        if (iplMap == null || iplMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
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




