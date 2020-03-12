package iplanalyser;

import com.opencsv.bean.CsvBindByName;

public class IplWKTsCSV {


    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "4w", required = true)
    public int four;

    @CsvBindByName(column = "5w", required = true)
    public int five;

    @CsvBindByName(column = "SR", required = true)
    public double strikeRate;

    @CsvBindByName(column = "Avg", required = true)
    public double avgBolling;

    @CsvBindByName(column = "Econ", required = true)
    public double Econ;

  @CsvBindByName(column = "Wkts" , required = true)
    public int Wkts;


}
