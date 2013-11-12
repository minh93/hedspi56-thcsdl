/*
 * Class chứa các phương thức dùng chung 
 * 
 */
package Ultilities;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Checksum;

/**
 *
 * @author PhamDucMinh
 */
public class Ultilities {

    public static String checksumGen(String filePath, String mode) {
        try {
            MessageDigest md = MessageDigest.getInstance(mode);

            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(filePath)));
            byte[] buff = new byte[1024];
            int length;
            while ((length = bis.read(buff)) != -1) {
                md.update(buff, 0, length);
            }
            byte[] mdByteResult = md.digest();

            bis.close();
            return bytArrayToHex(mdByteResult);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Ultilities.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ultilities.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ultilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String checksumGen(String source, String mode, boolean flag) {
        try {
            MessageDigest md = MessageDigest.getInstance(mode);
            byte[] buff = source.getBytes();
            md.update(buff);

            byte[] mdByteResult = md.digest();

            return bytArrayToHex(mdByteResult);

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Checksum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String bytArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder();
        for (byte b : a) {
            //sb.append(String.format("%02x", b & 0xff));
            sb.append(Integer.toHexString(b & 0xff));
        }
        return sb.toString();
    }
}
