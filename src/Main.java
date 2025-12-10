package userApplication;

import ithakimodem.Modem;
import java.util.ArrayList;

public class Main {

    public static final String FILES_PATH = "D:\\ECLIPSE\\eclipse-workspace\\userApplication\\session2\\";
    public static final String ECHO = "E1653\r";
    public static final String IMAGE_ERROR_FREE = "M3942\r";
    public static final String IMAGE_WITH_ERROR = "G2675\r";
    public static final String GPS = "P7608\r";
    public static final String ACK = "Q9439\r";
    public static final String NACK = "R4708\r";

    public static void main(String[] args) throws Exception {

        Modem modem = new Modem();
        modem.setSpeed(1000);
        modem.setTimeout(2000);
        modem.open("ithaki");

        ModemUtils.readUntilSequence(modem);

        long startTime = System.currentTimeMillis();
        ArrayList<Packet> packets = new ArrayList<>();

        System.out.println("Getting packets without errors for 4 minutes:");
        while (System.currentTimeMillis() < startTime + (4 * 60 * 1000)) {
            packets.add(EchoHandler.echoPacket(modem, ECHO));
        }

        // FIXED: export now includes FILES_PATH
        ModemUtils.exportDataToFile(packets, "packets_without_errors", FILES_PATH);

        System.out.println("Getting image without errors:");
        ImageHandler.getImage(modem, IMAGE_ERROR_FREE, FILES_PATH);

        System.out.println("Getting image with errors:");
        ImageHandler.getImage(modem, IMAGE_WITH_ERROR, FILES_PATH);

        System.out.println("Getting GPS images:");
        GPSHandler.gps(modem, GPS, FILES_PATH);

        System.out.println("Getting packets with ARQ for 4 minutes:");
        startTime = System.currentTimeMillis();
        packets = new ArrayList<>();

        while (System.currentTimeMillis() < startTime + (4 * 60 * 1000)) {
            packets.add(ARQHandler.arq(modem, ACK, NACK));
        }

        ModemUtils.exportDataToFile(packets, "packets_with_errors", FILES_PATH);

        System.out.println("\nModem closed -> " + modem.close());
    }
}