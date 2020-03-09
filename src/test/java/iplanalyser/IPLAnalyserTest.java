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
            iplAnalyser.loadIplRunsData(IPL_RUNS_DATA);
            String loadIplData = iplAnalyser.getSortedIPLData(sortField.AVERAGE);
            IplDTO[] censusCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals("MS Dhoni", censusCSV[censusCSV.length - 1].player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRunsData_whenSortedOnStrikkingRate_ShouldReturnTrue() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIplRunsData(IPL_RUNS_DATA);
            String loadIplData = iplAnalyser.getSortedIPLData(sortField.STRIKE_RATE);
            IplDTO[] censusCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals("Ishant Sharma", censusCSV[censusCSV.length - 1].player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRunsData_whenSortedOnMaxSixAndFour_ShouldReturnTrue() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIplRunsData(IPL_RUNS_DATA);
            String loadIplData = iplAnalyser.getSortedIPLData(sortField.FourAndSix);
            IplDTO[] censusCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals("Andre Russell", censusCSV[censusCSV.length - 1].player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRunsData_whenSortedOnMaxSixAndFour_ShouldReturnMaxStrikeRate() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIplRunsData(IPL_RUNS_DATA);
            String loadIplData = iplAnalyser.getSortedIPLData(sortField.FourAndSix);
            IplDTO[] censusCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals(204.81, censusCSV[censusCSV.length - 1].strikeRate, 00);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRunsData_whenSortedOnStrikeRate_ShouldReturnMaximumAverage() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIplRunsData(IPL_RUNS_DATA);
            String loadIplData = iplAnalyser.getSortedIPLData(sortField.AVERAGE);
            IplDTO[] censusCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals(134.62, censusCSV[censusCSV.length - 1].strikeRate, 00);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRunsData_whenSortedOnRuns_ShouldReturnMaximumAverage() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIplRunsData(IPL_RUNS_DATA);
            String loadIplData = iplAnalyser.getSortedIPLData(sortField.RUNS);
            IplDTO[] censusCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals(69.2, censusCSV[censusCSV.length - 1].avg, 00);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBollingData_whenSortedOnAverage_ShouldReturnMaximumAverage() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIplWKTsData(IPL_BOLLING_DATA);
            String loadIplData = iplAnalyser.getSortedIPLData(sortField.AVERAGE);
            IplDTO[] censusCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals(166.0, censusCSV[censusCSV.length - 1].avg, 00);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBollingData_whenSortedOnStrikeRate_ShouldReturnTrue() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIplWKTsData(IPL_BOLLING_DATA);
            String loadIplData = iplAnalyser.getSortedIPLData(sortField.STRIKE_RATE);
            IplDTO[] censusCSV = new Gson().fromJson(loadIplData, IplDTO[].class);
            Assert.assertEquals(120.0, censusCSV[censusCSV.length - 1].strikeRate, 00);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }


}
