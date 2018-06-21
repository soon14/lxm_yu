package com.lxm.ss.kuaisan.Utils;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * md5工具包
 */

public class MDFive {

    public static final int BUFF_SIZE = 1024;

    /**
     * get md5 of the input stream.
     *
     * @param in input stream. Could be FileInputStream, DataInputStream.
     * @return md5 if success, null if error occurs.
     */
    public static String getMD5(InputStream in) {
        if (in == null) {
            return null;
        }
        MessageDigest digest = null;
        byte buffer[] = new byte[BUFF_SIZE];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            while ((len = in.read(buffer, 0, BUFF_SIZE)) != -1) {
                digest.update(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }
}
