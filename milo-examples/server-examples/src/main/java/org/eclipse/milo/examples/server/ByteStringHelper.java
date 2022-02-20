package org.eclipse.milo.examples.server;

import java.nio.charset.StandardCharsets;

public class ByteStringHelper {

    public static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(
        StandardCharsets.US_ASCII);

    public static String bytesToHex(final byte[] bytes) {
        if (bytes == null)
            return "";
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = (char) HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = (char) HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte toByte(char c) {
        switch (c) {
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            case 'A':
                return 10;
            case 'B':
                return 11;
            case 'C':
                return 12;
            case 'D':
                return 13;
            case 'E':
                return 14;
            case 'F':
                return 15;
        }
        return 0;
    }

    public static byte[] bytesFromHex(final String hex) {
        char[] hexChars = hex.toCharArray();
        byte[] bytes = new byte[hex.length() / 2];
        for (int j = 0; j < hex.length() / 2; j++) {
            bytes[j] = (byte) ((toByte(hexChars[j * 2]) << 4) | toByte(
                hexChars[j * 2 + 1]));
        }
        return bytes;
    }
}
