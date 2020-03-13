package iplanalyser;

import java.util.Comparator;

public class CompareAverages implements Comparator<IplDTO> {
    @Override
    public int compare(IplDTO p1, IplDTO p2) {
        int avg = (int) ((p1.avg + p1.avgBolling) - (p2.avg + p2.avgBolling));
        return avg;
    }
}
