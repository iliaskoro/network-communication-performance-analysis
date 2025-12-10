package userApplication;

import ithakimodem.Modem;

public class ARQHandler {

    public static Packet arq(Modem modem, String ACK, String NACK) {

        modem.setSpeed(80000);

        int retransmission = 0;
        long start = System.currentTimeMillis();

        ModemUtils.runCommand(modem, ACK);
        char[] chars = ModemUtils.readPacket(modem, 59);

        long elapsed = System.currentTimeMillis() - start;
        Packet packet = new Packet(new String(chars), elapsed, retransmission);

        while (!packet.isFCScorrect()) {

            retransmission++;
            start = System.currentTimeMillis();

            ModemUtils.runCommand(modem, NACK);
            chars = ModemUtils.readPacket(modem, 59);

            elapsed = System.currentTimeMillis() - start;   // FIXED
            packet = new Packet(new String(chars), elapsed, retransmission);
        }

        System.out.println("\nCorrect FCS: " + packet);
        return packet;
    }
}