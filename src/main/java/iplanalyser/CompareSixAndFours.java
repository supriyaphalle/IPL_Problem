package iplanalyser;

import java.util.Comparator;

public class CompareSixAndFours implements Comparator<IplDTO> {

    @Override
    public int compare(IplDTO t1, IplDTO t2) {
        return (t1.six + t1.four) - (t2.six + t2.four);
    }
}
