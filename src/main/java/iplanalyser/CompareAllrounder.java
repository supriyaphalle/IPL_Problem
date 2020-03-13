package iplanalyser;

import java.util.Comparator;

public class CompareAllrounder implements Comparator<IplDTO> {
    @Override
    public int compare(IplDTO p1, IplDTO t1) {
        int i=0;
        if (p1.wicket != 0 ) {
            i = (int) ((p1.runs*p1.wicket) - (t1.wicket*t1.runs));
        }
        return i;
    }
}
