package me.chanjar.weixin.common.util;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class DigestSelfUtils {


  public static String md5Hex(String res){
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    md.update(res.getBytes());
    byte[] digest = md.digest();
    String myHash = DatatypeConverter
      .printHexBinary(digest).toUpperCase();
    return myHash;
  }

  public static String shaHex(String res){
    try {
      MessageDigest crypt = MessageDigest.getInstance("SHA-1");
      crypt.reset();
      crypt.update(res.getBytes(StandardCharsets.UTF_8));
      return  byteToHex(crypt.digest());
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return null;
  }

  static String byteToHex(final byte[] hash)
  {
    Formatter formatter = new Formatter();
    for (byte b : hash)
    {
      formatter.format("%02x", b);
    }
    String result = formatter.toString();
    formatter.close();
    return result;
  }

  static String getHexString(byte[] b) throws Exception {
    String result = "";
    for (int i=0; i < b.length; i++) {
      result +=
        Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
    }
    return result;
  }


}
