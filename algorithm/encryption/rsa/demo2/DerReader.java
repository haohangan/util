package der;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
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

	public static void main(String[] args)
			throws UnsupportedEncodingException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		InputStream in = DerReader.class.getResourceAsStream("private_key.der");
		byte[] keyBytes = IOUtils.toByteArray(in);
		// byte[] keyBytes =
		// Files.readAllBytes(Paths.get("D://private_key.der"));

		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = kf.generatePrivate(spec);
		
		/** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
//		Decoder decoder = Base64.getDecoder();
		String str = "X5Yd9lB3kPwQP550qZlwbG1BkePtd+J95E5NzV+VuM2b9X8GPR3+NbBDlPKILOSpk4gM9ODTSG7RUAqezfDAvV6mbRo5+FgrWgLaNYOijcjcSRVQGt1BTp1mI1P18O7MDhj9IJfLe9LRG37JhkVjbKdG1v59XS3RvzAE/pAsm08zc1KZk1R0faQIilqSIyiM7YJaJz6FOLtwVI/S2TY+5sh9/7nDy4Zyba4F5QW73Pk0aFNOacAaT/J0aUHfEyQUX07arSqg49hhbDv4k7tf1g3QexhyaKH3oJvLjylfJFZBB/jWLnh2pfMT/Yg6MFVAwJ/PmJoiIE8CR8kxQQomNw==";
		byte[] b1 = Base64.decodeBase64(str.getBytes(StandardCharsets.UTF_8));
		/** 执行解密操作 */
		byte[] b = cipher.doFinal(b1);
		String mw =  new String(b);
		System.out.println(mw);
	}
}
