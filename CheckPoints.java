class CheckPoints {

    public final int[] x = new int[140];
    public final int[] z = new int[140];
    public final int[] y = new int[140];
    public final int[] typ = new int[140];
    public int pcs = 0;
    public int nsp = 0;
    public int n = 0;
    public final int[] fx = new int[5];
    public final int[] fz = new int[5];
    public final int[] fy = new int[5];
    public final boolean[] roted = new boolean[5];
    public final boolean[] special = new boolean[5];
    public int fn = 0;
    public int stage = 1;
    public int nlaps = 0;
    /**
     * stage name<br>
     * <b>hogan rewish</b> if none is found
     */
    public String name = "hogan rewish";
    public final int[] pos = {
           50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50,
           50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50,
           50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50
    };
    /**cum
     * connected to number of cars
     */
    public final int[] clear = new int[51];
    /**
     * connected to number of cars
     */
    public final int[] dested = new int[51];
    public int wasted = 0;
    public boolean haltall = false;
    public int pcleared = 0;
    public final int[] opx = new int[51];
    public final int[] opz = new int[51];
    public final int[] onscreen = new int[51];
    public final int[] omxz = new int[51];
    public int catchfin = 0;
    private int postwo = 0;

    public void checkstat(Madness amadness[], ContO aconto[], Record record, int ncars) {
        if (!haltall) {
            pcleared = amadness[0].pcleared;
            int i = 0;
            do {
                pos[i] = 0;
                onscreen[i] = aconto[i].dist;
                opx[i] = aconto[i].x;
                opz[i] = aconto[i].z;
                omxz[i] = amadness[i].mxz;
                if (dested[i] == 0) {
                    clear[i] = amadness[i].clear;
                } else {
                    clear[i] = -1;
                }
            } while (++i < ncars);
            i = 0;
            do {
                for (int l = i + 1; l < ncars; l++) {
                    if (clear[i] != clear[l]) {
                        if (clear[i] < clear[l]) {
                            pos[i]++;
                        } else {
                            pos[l]++;
                        }
                    } else {
                        int j1;
                        for (j1 = amadness[i].pcleared + 1; typ[j1] <= 0; ) {
                            if (++j1 == n) {
                                j1 = 0;
                            }
                        }

                        if (Utility.py(aconto[i].x / 100, x[j1] / 100, aconto[i].z / 100, z[j1] / 100) > Utility.py(aconto[l].x / 100,
                                x[j1] / 100, aconto[l].z / 100, z[j1] / 100)) {
                            pos[i]++;
                        } else {
                            pos[l]++;
                        }
                    }
                }

            } while (++i < ncars);
            if (stage > 2) {
                int j = 0;
                do {
                    if (clear[j] == nlaps * nsp && pos[j] == 0) {
                        if (j == 0) {
                            int i1 = 0;
                            do {
                                if (pos[i1] == 1) {
                                    postwo = i1;
                                }
                            } while (++i1 < ncars);
                            if (Utility.py(opx[0] / 100, opx[postwo] / 100, opz[0] / 100, opz[postwo] / 100) < 14000
                                    && clear[0] - clear[postwo] == 1) {
                                catchfin = 30;
                            }
                        } else if (pos[0] == 1 && Utility.py(opx[0] / 100, opx[j] / 100, opz[0] / 100, opz[j] / 100) < 14000
                                && clear[j] - clear[0] == 1) {
                            catchfin = 30;
                            postwo = j;
                        }
                    }
                } while (++j < ncars);
            }
        }
        wasted = 0;
        int k = 1;
        do {
            if (amadness[k].dest) {
                wasted++;
            }
        } while (++k < ncars);
        if (catchfin != 0) {
            catchfin--;
            if (catchfin == 0) {
                record.cotchinow(postwo);
                record.closefinish = pos[0] + 1;
            }
        }
    }
}
