package userApplication;

import ithakimodem.Modem;
import java.io.*;
import java.util.ArrayList;

public class ImageHandler {

    public static void getImage(Modem modem, String requestCode, String FILES_PATH) throws IOException {

        modem.setSpeed(80000);
        ArrayList<Byte> image = new ArrayList<>();

        ModemUtils.runCommand(modem, requestCode);

        int x;
        boolean ff = false;

        for (;;) {
            x = modem.read();
            if (x == -1) break;

            image.add((byte) x);

            if (ff && Integer.toHexString(x).equals("d9")) break;
            ff = Integer.toHexString(x).equals("ff");
        }

        byte[] img = new byte[image.size()];
        for (int i = 0; i < image.size(); i++) img[i] = image.get(i);

        String output = FILES_PATH + "image_" + requestCode.replace('\r', '.') + "jpg";
        FileOutputStream os = new FileOutputStream(output);
        os.write(img);
        os.close();

        System.out.println("Image saved: " + output);
    }
}