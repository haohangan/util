package der;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

public class DerReader {

	static PrivateKey privateKey;
	
	static{
		try {
			init();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void init() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		InputStream in = DerReader.class.getResourceAsStream("private_key.der");
		byte[] keyBytes = IOUtils.toByteArray(in);
		// byte[] keyBytes =
		// Files.readAllBytes(Paths.get("D://private_key.der"));

		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		privateKey = kf.generatePrivate(spec);
	}

	/**
	 * RSA解密，返回解密后的明文
	 * 
	 * @param str
	 * @return
	 */
	public static String decrypt(String str) {
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			// Decoder decoder = Base64.getDecoder();

			byte[] b1 = Base64.decodeBase64(str.getBytes(StandardCharsets.UTF_8));
			/** 执行解密操作 */
			byte[] b = cipher.doFinal(b1);
			String mw = new String(b);
			return mw;
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return "error";
	}

}
