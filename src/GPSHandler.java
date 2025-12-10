package userApplication;

import ithakimodem.Modem;
import java.util.ArrayList;

public class GPSHandler {

    private static void gpsRead(Modem modem, StringBuilder out) {
        int x;

        for (;;) {
            try {
                x = modem.read();
                if (x == -1) break;

                out.append((char) x);

                if (out.length() >= 26) {
                    if (out.substring(out.length() - 26).equals("ITHAKI GPS TRACKING...\r\n"))
                        break;
                }

            } catch (Exception e) {
                break;
            }
        }
    }

    public static void gps(Modem modem, String GPS, String FILES_PATH) throws Exception {
        StringBuilder out = new StringBuilder();

        modem.setSpeed(1000);

        ModemUtils.runCommand(modem, GPS.replace("\r", "R=1001101\r"));
        gpsRead(modem, out);

        ModemUtils.runCommand(modem, GPS.replace("\r", "R=1011001\r"));
        gpsRead(modem, out);

        ModemUtils.runCommand(modem, GPS.replace("\r", "R=1012001\r"));
        gpsRead(modem, out);

        ModemUtils.runCommand(modem, GPS.replace("\r", "R=1013001\r"));
        gpsRead(modem, out);

        ArrayList<GPGGA> list = new ArrayList<>();

        for (int i = 0; i < out.length(); i++) {
            if (out.charAt(i) == '$') {
                int start = i;

                while (out.charAt(i) != '\r') i++;
                int stop = i;

                list.add(new GPGGA(out.substring(start, stop)));
            }
        }

        getGpsImage(modem, list, GPS, FILES_PATH);
    }

    private static void getGpsImage(Modem modem,
                                    ArrayList<GPGGA> list,
                                    String GPS,
                                    String FILES_PATH) throws Exception {

        StringBuilder tPar = new StringBuilder();

        for (GPGGA g : list) tPar.append(g.getT());
        tPar.append("\r");

        String finalCommand = GPS.replace("\r", tPar.toString());
        ImageHandler.getImage(modem, finalCommand, FILES_PATH);
    }
}