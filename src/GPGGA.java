package userApplication;

public class GPGGA {

    private final String raw;
    final String time;

    int aDeg, aMin, aSec;
    int bDeg, bMin, bSec;

    public GPGGA(String raw) {
        this.raw = raw;

        String[] info = raw.split(",");
        this.time = info[1];

        String a = info[2];
        String b = info[4];

        aDeg = Integer.parseInt(a.substring(0, 2));
        aMin = Integer.parseInt(a.substring(2, 4));
        aSec = (int)(60 * Double.parseDouble(a.substring(5, 9)) / 10000);

        bDeg = Integer.parseInt(b.substring(0, 3));
        bMin = Integer.parseInt(b.substring(3, 5));
        bSec = (int)(60 * Double.parseDouble(b.substring(6, 10)) / 10000);
    }

    public String getT() {
        return "T=" + bDeg + bMin + bSec + aDeg + aMin + aSec;
    }

    @Override
    public String toString() {
        return raw;
    }
}