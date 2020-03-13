package iplanalyser;

import java.util.Comparator;

public class CompareFiveAndFours implements Comparator<IplDTO> {

    @Override
    public int compare(IplDTO t1, IplDTO t2) {
        return (t1.four + t1.five) - (t2.four + t2.five);
    }
}
