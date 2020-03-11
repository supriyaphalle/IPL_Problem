package iplanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class IPLAnalyserTest {
    public static final String IPL_RUNS_DATA = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    public static final String IPL_BOLLING_DATA = "./src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenIPLRunsData_whenSortedOnBattingAvg_ShouldReturnTrue() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIplData(IPLAnalyser.CSVType.RUNS, IPL_RUNS_DATA);
            String loadIplData = iplAnalyser.getSortedIPLData(sortField.AVERAGE);
            IplDTO[] censusCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals("MS Dhoni", censusCSV[0].player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRunsData_whenSortedOnStrikkingRate_ShouldReturnTrue() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIplData(IPLAnalyser.CSVType.RUNS, IPL_RUNS_DATA);
            String loadIplData = iplAnalyser.getSortedIPLData(sortField.STRIKE_RATE);
            IplDTO[] censusCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals("Ishant Sharma", censusCSV[0].player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRunsData_whenSortedOnMaxSixAndFour_ShouldReturnTrue() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIplData(IPLAnalyser.CSVType.RUNS, IPL_RUNS_DATA);
            String loadIplData = iplAnalyser.getSortedIPLData(sortField.FourAndSix);
            IplDTO[] censusCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals("Andre Russell", censusCSV[0].player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRunsData_whenSortedOnMaxSixAndFour_ShouldReturnMaxStrikeRate() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIplData(IPLAnalyser.CSVType.RUNS, IPL_RUNS_DATA);
            String loadIplData = iplAnalyser.getSortedIPLData(sortField.SIX_FOUR);
            IplDTO[] censusCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals(204.81, censusCSV[0].strikeRate, 00);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRunsData_whenGreatAverageWithBestStrikeRate_ShouldReturnTrue() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIplData(IPLAnalyser.CSVType.RUNS, IPL_RUNS_DATA);
            String loadIplData = iplAnalyser.getSortedIPLData(sortField.AVERAGE_STRIKE);
            IplDTO[] censusCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            System.out.println(censusCSV);
            Assert.assertEquals("MS Dhoni", censusCSV[0].player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRunsData_whenMaximumRunsWithBestAverage_ShouldReturnTrue() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIplData(IPLAnalyser.CSVType.RUNS, IPL_RUNS_DATA);
            String loadIplData = iplAnalyser.getSortedIPLData(sortField.RUNS_AVERAGE);
            IplDTO[] censusCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals("MS Dhoni", censusCSV[0].player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBollingData_whenSortedOnAverage_ShouldReturnMaximumAverage() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIplData(IPLAnalyser.CSVType.WICKETS, IPL_BOLLING_DATA);
            String loadIplData = iplAnalyser.getSortedIPLData(sortField.AVERAGE);
            IplDTO[] censusCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals("Krishnappa Gowtham", censusCSV[0].player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBollingData_whenSortedOnStrikeRate_ShouldReturnTrue() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIplData(IPLAnalyser.CSVType.WICKETS, IPL_BOLLING_DATA);
            String loadIplData = iplAnalyser.getSortedIPLData(sortField.STRIKE_RATE);
            IplDTO[] censusCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals("Krishnappa Gowtham", censusCSV[0].player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBollingData_whenSortedOnEconomy_ShouldReturnTrue() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIplData(IPLAnalyser.CSVType.WICKETS, IPL_BOLLING_DATA);
            String loadIplData = iplAnalyser.getSortedIPLData(sortField.ECONOMY);
            IplDTO[] censusCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals("Ben Cutting", censusCSV[0].player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBollingData_whenSortedOnStrikeRateAnd5Wand4W_ShouldReturnTrue() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIplData(IPLAnalyser.CSVType.WICKETS, IPL_BOLLING_DATA);
            String loadIplData = iplAnalyser.getSortedIPLData(sortField.FIVE_FOUR);
            IplDTO[] censusCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals("Lasith Malinga", censusCSV[0].player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBollingData_whenSorteOnBollingAvgWithBestStrikeRate_ShouldReturnTrue() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIplData(IPLAnalyser.CSVType.WICKETS, IPL_BOLLING_DATA);
            String loadIplData = iplAnalyser.getSortedIPLData(sortField.AVERAGE_STRIKE);
            IplDTO[] censusCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals("Krishnappa Gowtham", censusCSV[0].player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void givenIPLBollingData_whenSorteOnMaximumWicketsWithBestBollingAvg_ShouldReturnTrue() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIplData(IPLAnalyser.CSVType.WICKETS, IPL_BOLLING_DATA);
            String loadIplData = iplAnalyser.getSortedIPLData(sortField.WICKET_AVERAGE);
            IplDTO[] censusCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals("Imran Tahir", censusCSV[0].player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }


}
