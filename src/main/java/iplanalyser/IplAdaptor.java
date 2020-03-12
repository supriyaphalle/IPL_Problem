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

public abstract class IplAdaptor {

    public abstract Map<String, IplDTO> loadIplData(String... csvFilePath) ;
    Map<String, IplDTO> iplMap = new HashMap<>();

    public <E> Map<String, IplDTO> loadIplData(Class<E> IplCSV, String FilePath) throws IPLAnalyserException {
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
            return iplMap;
        } catch (IOException ex) {
            throw new IPLAnalyserException(ex.getMessage(), IPLAnalyserException.ExceptionType.IPL_DATA_FILE_PROBLEM);
        }
    }
}
