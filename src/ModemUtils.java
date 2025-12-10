package userApplication;

import ithakimodem.Modem;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ModemUtils {

    public static char[] readPacket(Modem modem, int numOfChars) {
        char[] chars = new char[numOfChars];
        int x;

        for (int j = 0; j < numOfChars - 1; j++) {
            try {
                x = modem.read();
                if (x == -1) break;
                chars[j] = (char) x;
            } catch (Exception e) {
                break;
            }
        }
        return chars;
    }

    public static void runCommand(Modem modem, String command) {
        try {
            boolean ok = modem.write(command.getBytes("US-ASCII"));
            if (!ok) System.out.println("Error sending command: " + command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readUntilSequence(Modem modem) {
        int x;
        for (int j = 0; j != 4;) {
            try {
                x = modem.read();
                if (x == -1) break;
                char c = (char) x;
                System.out.print(c);

                if (j >= 1) {
                    if (c == '\n') j++;
                    else j = 0;
                }
                if (c == '\r') j = 1;

            } catch (Exception e) {
                break;
            }
        }
    }

    public static void exportDataToFile(ArrayList<Packet> packets,
                                        String filename,
                                        String FILES_PATH) throws IOException {

        File file = new File(FILES_PATH + filename + ".csv");
        FileWriter w = new FileWriter(file);

        for (Packet p : packets) {
            if (p.what != null)
                w.write(p.packetString + ";" + p.repeat + ";" + p.responseTime + "\n");
            else
                w.write(p.packetString + ";" + p.responseTime + "\n");
        }
        w.close();
    }
}