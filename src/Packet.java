package userApplication;

public class Packet {

    final String packetString;
    final long responseTime;
    String what;
    int fcs;
    int repeat;

    public Packet(String packetString, long responseTime, int repeat) {
        this.packetString = packetString;
        this.responseTime = responseTime;
        this.repeat = repeat;

        String[] s = packetString.split(" ");
        if (s.length >= 6) {
            this.what = s[4];
            this.fcs = Integer.parseInt(s[5]);
        }
    }

    public Packet(String packetString, long responseTime) {
        this.packetString = packetString;
        this.responseTime = responseTime;
    }

    public boolean isFCScorrect() {
        if (what == null) return false;

        int f = what.charAt(1);
        for (int i = 2; i < 17; i++) f ^= what.charAt(i);

        return f == fcs;
    }

    @Override
    public String toString() {
        if (what != null)
            return packetString + " | RTT=" + responseTime + "ms | Retries=" + repeat;
        return packetString + " | RTT=" + responseTime + "ms";
    }
}