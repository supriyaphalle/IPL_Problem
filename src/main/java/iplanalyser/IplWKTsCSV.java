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
    public double Avg;

    @CsvBindByName(column = "Econ", required = true)
    public double Econ;

 /*   @Override
    public String toString() {
        return "IplWicketsCSV{" + '\'' +
                "Player = " + player + '\'' +
                "Runs=" + runs + '\'' +
                ", Avg = " + Avg + '\'' +
                ", Four =" + four + '\'' +
                ", Five Wickets = " + five + '\'' +
                "Strike Rate = " + strikeRate + '\'' +
                "Economy = + " + Econ + '\'' +
                '}';
    }
*/

}
