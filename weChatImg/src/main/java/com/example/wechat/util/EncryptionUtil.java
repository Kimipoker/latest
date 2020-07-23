package com.example.wechat.util;

public class EncryptionUtil {

    public static byte[] EnDeFile(byte[] filebuffer, int key) {
        byte[] newBuffer = new byte[filebuffer.length];
        for (int i = 0; i < filebuffer.length; i++)
        {
            newBuffer[i] = (byte)(filebuffer[i] ^ key);
        }

        return newBuffer;
    }
}
