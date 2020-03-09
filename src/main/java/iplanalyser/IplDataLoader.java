package iplanalyser;

import CSVReader.CSVBuilderFactory;
import CSVReader.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IplDataLoader {
    Map<String, IplDTO> iplMap = new HashMap<>();

    public  Map<String, IplDTO> loadIplData(IPLAnalyser.CSVType type, String FilePath) throws IPLAnalyserException {
            if(type.equals(IPLAnalyser.CSVType.RUNS))
                return this.loadIplData(IplRunsCSV.class,FilePath);
            else if (type.equals(IPLAnalyser.CSVType.WICKETS))
                return  this.loadIplData(IplWKTsCSV.class,FilePath);
            else
                 throw new IPLAnalyserException("Invalid data", IPLAnalyserException.ExceptionType.INVALID_DATA);
    }


    public <E> Map<String, IplDTO> loadIplData(Class<E> IplCSV, String FilePath) throws IPLAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(FilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> iterator = csvBuilder.getCSVFileIterator(reader, IplCSV);
            Iterable<E> csvIterable = () -> iterator;
            if (IplCSV.getName() == "iplanalyser.IplRunsCSV") {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map((IplRunsCSV.class::cast))
                        .forEach(csvName -> iplMap.put(csvName.player, new IplDTO(csvName)));
                // .forEach(csvName ->this.iplDTOList.add(new IplDTO(csvName)));
            } else if (IplCSV.getName() == "iplanalyser.IplWKTsCSV") {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map((IplWKTsCSV.class::cast))
                        .forEach(csvName -> iplMap.put(csvName.player, new IplDTO(csvName)));
            }
            return iplMap;
        } catch (IOException ex) {
            throw new IPLAnalyserException(ex.getMessage(), IPLAnalyserException.ExceptionType.IPLDATA_FILE_PROBLEM);
        }
    }

}
