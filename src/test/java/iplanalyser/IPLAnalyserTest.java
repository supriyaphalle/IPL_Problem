package iplanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IPLAnalyserTest {
    private String loadIplData;
    private IplDTO[] iplCSV;
    private static final String IPL_RUNS_DATA = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_BOLLING_DATA = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
    IPLAnalyser iplAnalyser;

    @Before
    public void setup() {
        iplAnalyser = new IPLAnalyser();
    }

    @Test
    public void givenIPLRunsData_whenSortedOnBattingAvg_ShouldReturnTrue() {
        try {
            iplAnalyser.loadIplData(IPLAnalyser.CSVType.RUNS, IPL_RUNS_DATA);
            loadIplData = iplAnalyser.getSortedIPLData(sortField.AVERAGE);
            iplCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals("MS Dhoni", iplCSV[0].player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRunsData_whenSortedOnStrikingRate_ShouldReturnTrue() {
        try {
            iplAnalyser.loadIplData(IPLAnalyser.CSVType.RUNS, IPL_RUNS_DATA);
            loadIplData = iplAnalyser.getSortedIPLData(sortField.STRIKE_RATE);
            iplCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals("Ishant Sharma", iplCSV[0].player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRunsData_whenSortedOnMaxSixAndFour_ShouldReturnTrue() {
        try {
            iplAnalyser.loadIplData(IPLAnalyser.CSVType.RUNS, IPL_RUNS_DATA);
            loadIplData = iplAnalyser.getSortedIPLData(sortField.FourAndSix);
            iplCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals("Andre Russell", iplCSV[0].player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRunsData_whenSortedOnMaxSixAndFour_ShouldReturnMaxStrikeRate() {
        try {
            iplAnalyser.loadIplData(IPLAnalyser.CSVType.RUNS, IPL_RUNS_DATA);
            loadIplData = iplAnalyser.getSortedIPLData(sortField.SIX_FOUR);
            iplCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals(204.81, iplCSV[0].strikeRate, 00);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRunsData_whenGreatAverageWithBestStrikeRate_ShouldReturnTrue() {
        try {
            iplAnalyser.loadIplData(IPLAnalyser.CSVType.RUNS, IPL_RUNS_DATA);
            loadIplData = iplAnalyser.getSortedIPLData(sortField.AVERAGE_STRIKE);
            iplCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            System.out.println(iplCSV);
            Assert.assertEquals("MS Dhoni", iplCSV[0].player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRunsData_whenMaximumRunsWithBestAverage_ShouldReturnTrue() {
        try {
            iplAnalyser.loadIplData(IPLAnalyser.CSVType.RUNS, IPL_RUNS_DATA);
            loadIplData = iplAnalyser.getSortedIPLData(sortField.RUNS_AVERAGE);
            iplCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals("David Warner", iplCSV[0].player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBollingData_whenSortedOnAverage_ShouldReturnMaximumAverage() {
        try {
            iplAnalyser.loadIplData(IPLAnalyser.CSVType.WICKETS, IPL_BOLLING_DATA);
            loadIplData = iplAnalyser.getSortedIPLData(sortField.BOLLING_AVERAGE);
            iplCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplCSV[0].player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBollingData_whenSortedOnStrikeRate_ShouldReturnTrue() {
        try {
            iplAnalyser.loadIplData(IPLAnalyser.CSVType.WICKETS, IPL_BOLLING_DATA);
            loadIplData = iplAnalyser.getSortedIPLData(sortField.STRIKE_RATE);
            iplCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplCSV[0].player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBollingData_whenSortedOnEconomy_ShouldReturnTrue() {
        try {
            iplAnalyser.loadIplData(IPLAnalyser.CSVType.WICKETS, IPL_BOLLING_DATA);
            loadIplData = iplAnalyser.getSortedIPLData(sortField.ECONOMY);
            iplCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals("Ben Cutting", iplCSV[0].player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBollingData_whenSortedOnStrikeRateAnd5Wand4W_ShouldReturnTrue() {
        try {
            iplAnalyser.loadIplData(IPLAnalyser.CSVType.WICKETS, IPL_BOLLING_DATA);
            loadIplData = iplAnalyser.getSortedIPLData(sortField.FIVE_FOUR);
            iplCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals("Lasith Malinga", iplCSV[0].player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBollingData_whenSorteOnBollingAvgWithBestStrikeRate_ShouldReturnTrue() {
        try {
            iplAnalyser.loadIplData(IPLAnalyser.CSVType.WICKETS, IPL_BOLLING_DATA);
            loadIplData = iplAnalyser.getSortedIPLData(sortField.AVG_STRIKE);
            iplCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplCSV[0].player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBollingData_whenSortOnMaximumWicketsWithBestBollingAvg_ShouldReturnTrue() {
        try {
            iplAnalyser.loadIplData(IPLAnalyser.CSVType.WICKETS, IPL_BOLLING_DATA);
            loadIplData = iplAnalyser.getSortedIPLData(sortField.WICKET_AVERAGE);
            iplCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals("Imran Tahir", iplCSV[0].player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLData_whenSortOnBattingAndBollingAvg_ShouldReturnTrue() {
        try {
            iplAnalyser.loadIplData(IPLAnalyser.CSVType.RUNS, IPL_RUNS_DATA, IPL_BOLLING_DATA);
            loadIplData = iplAnalyser.getSortedIPLData(sortField.BATTINGBOLLING_AVERAGE);
            iplCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplCSV[0].player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLData_whenALLRounder_ShouldReturnTrue() {
        try {
            iplAnalyser.loadIplData(IPLAnalyser.CSVType.RUNS, IPL_RUNS_DATA, IPL_BOLLING_DATA);
            loadIplData = iplAnalyser.getSortedIPLData(sortField.WICKETS);
            iplCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals("Hardik Pandya", iplCSV[0].player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }
}
