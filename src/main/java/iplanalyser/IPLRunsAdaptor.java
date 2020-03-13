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

public class IPLRunsAdaptor extends IplAdaptor {

    @Override
    public Map<String, IplDTO> loadIplData(String... csvFilePath) {
        Map<String, IplDTO> iplMap = super.loadIplData(IplRunsCSV.class, csvFilePath[0]);
        if (csvFilePath.length > 1)
            this.loadIplWktData(iplMap, csvFilePath[1]);
        return iplMap;
    }

    public <E> void loadIplWktData(Map<String, IplDTO> IPLMap, String csvFilePath) {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IplWKTsCSV> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, IplWKTsCSV.class);
            Iterable<IplWKTsCSV> csvIterable = () -> censusCSVIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .filter(csvName -> IPLMap.get(csvName.player) != null)
                    .forEach(csvName -> {
                        IPLMap.get(csvName.player).wicket = csvName.wickets;
                        IPLMap.get(csvName.player).avgBolling = csvName.avgBolling;
                    });
        } catch (IOException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }
}
