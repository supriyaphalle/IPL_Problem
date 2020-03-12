package iplanalyser;

import CSVReader.CSVBuilderFactory;
import CSVReader.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class LoadData {

    public  <E> Map loadIplWktData(Map<String, IplDTO> IPLMap, sortField type, String csvFilePath)  {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IplWKTsCSV> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, IplWKTsCSV.class);
            Iterable<IplWKTsCSV> csvIterable = () -> censusCSVIterator;

            if( type.equals(sortField.BATTINGBOLLING_AVERAGE)){
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .filter(csvName -> IPLMap.get(csvName.player) != null)
                        .forEach(csvName -> IPLMap.get(csvName.player).avgBolling = csvName.avgBolling);
            }
            return  IPLMap;
        } catch (IOException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

}
