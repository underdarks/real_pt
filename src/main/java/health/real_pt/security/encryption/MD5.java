package health.real_pt.security.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String getMD5HashCode(String text){
        String MD5="";

        try {
            MessageDigest md=MessageDigest.getInstance("MD5");
            md.update(text.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb=new StringBuffer();

            for (int i = 0; i < byteData.length; i++)
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

            MD5=sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return MD5;
    }
}
