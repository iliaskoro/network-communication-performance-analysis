package userApplication;

import ithakimodem.Modem;

public class EchoHandler {

    public static Packet echoPacket(Modem modem, String ECHO) {
        modem.setSpeed(80000);
        long start = System.currentTimeMillis();

        ModemUtils.runCommand(modem, ECHO);
        char[] chars = ModemUtils.readPacket(modem, 36);

        long elapsed = System.currentTimeMillis() - start;
        Packet packet = new Packet(new String(chars), elapsed);

        System.out.println(packet);
        return packet;
    }
}