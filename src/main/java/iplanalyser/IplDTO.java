package iplanalyser;

import com.opencsv.bean.CsvBindByName;

import java.util.Iterator;

public class IplDTO {

    public String player;
    public int runs;
    public int four;
    public int six;
    public int five;
    public double strikeRate;
    public double avg;
 //   public U field;


    public IplDTO(IplRunsCSV IplRunsCSV) {
        player=IplRunsCSV.player;
        runs=IplRunsCSV.runs;
        four=IplRunsCSV.four;
        six=IplRunsCSV.six;
        strikeRate=IplRunsCSV.strikeRate;
        avg=IplRunsCSV.Avg;
    }

    public IplDTO (IplWKTsCSV IplWKTsCSV){
        player=IplWKTsCSV.player;
        runs=IplWKTsCSV.runs;
        four=IplWKTsCSV.four;
        five =IplWKTsCSV.five;
        strikeRate=IplWKTsCSV.strikeRate;
        avg=IplWKTsCSV.Avg;
    }
}
