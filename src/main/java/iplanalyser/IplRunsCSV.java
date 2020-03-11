package iplanalyser;

import com.opencsv.bean.CsvBindByName;

public class IplRunsCSV {


    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "4s", required = true)
    public int four;

    @CsvBindByName(column = "Avg", required = true)
    public double Avg;

    @CsvBindByName(column = "6s", required = true)
    public int six;

    @CsvBindByName(column = "SR", required = true)
    public double strikeRate;


   /* @Override
    public String toString() {
        return "IplRunsCSV{" +
                "Runs=" + runs + '\'' +
                ", Avg = " + Avg + '\'' +
                ", Four =" + four + '\'' +
                ", Six = " + six + '\'' +
                "Strike Rate = " + strikeRate + '\'' +
                '}';
    }*/
}
