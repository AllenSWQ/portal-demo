package com.portal.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class SecurityUtil {

	public static String sha256(String strSrc) {
		MessageDigest md = null;
		String strDes = null;

		byte[] bt = strSrc.getBytes();
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(bt);
			strDes = bytes2Hex(md.digest());
		} catch (NoSuchAlgorithmException e) {
			return null;
		}

		return strDes;
	}

	public static String sha256File(String filePath) {

		File file = new File(filePath);
		if (!file.exists()) {
			return null;
		}

		FileInputStream in = null;
		MessageDigest messagedigest;

		try {
			messagedigest = MessageDigest.getInstance("SHA-256");

			byte[] buffer = new byte[1024 * 100];
			int len = 0;

			in = new FileInputStream(file);
			while ((len = in.read(buffer)) > 0) {
				// 该对象通过使用 update（）方法处理数据
				messagedigest.update(buffer, 0, len);
			}

			return new String(byteArrayToHexString(messagedigest.digest()));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	// 将字节转换为十六进制字符串
	private static String byteToHexString(byte ib) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] ob = new char[2];
		ob[0] = Digit[(ib >>> 4) & 0X0F];
		ob[1] = Digit[ib & 0X0F];
		String s = new String(ob);
		return s;
	}

	// 将字节数组转换为十六进制字符串
	public static String byteArrayToHexString(byte[] bytearray) {
		String strDigest = "";
		for (int i = 0; i < bytearray.length; i++) {
			strDigest += byteToHexString(bytearray[i]);
		}
		return strDigest;
	}

	public static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}

	private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String toHexString(byte[] bytes) {
		if (bytes == null)
			return "";
		StringBuilder hex = new StringBuilder(bytes.length * 2);
		for (byte b : bytes) {
			hex.append(hexDigits[(b >> 4) & 0x0F]);
			hex.append(hexDigits[b & 0x0F]);
		}
		return hex.toString();
	}

	public static String md5(String string) {
		byte[] encodeBytes = null;

		try {
			encodeBytes = MessageDigest.getInstance("MD5").digest(
					string.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return toHexString(encodeBytes);
	}

	// DES加密处理
	private static Key key;
	private static String KEY_STR = "ABDEDVFLJNJVFFGRGGDFSV";

	static {
		try {
			KeyGenerator generator = KeyGenerator.getInstance("DES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(KEY_STR.getBytes());
			generator.init(secureRandom);
			key = generator.generateKey();
			generator = null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 对字符串进行加密，返回BASE64的加密字符串 <功能详细描述>
	 * 
	 * @param str
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String getEncryptString(String str) {
		BASE64Encoder base64Encoder = new BASE64Encoder();
		System.out.println(key);
		try {
			byte[] strBytes = str.getBytes("UTF-8");
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encryptStrBytes = cipher.doFinal(strBytes);
			return base64Encoder.encode(encryptStrBytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 对BASE64加密字符串进行解密 <功能详细描述>
	 * 
	 * @param str
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String getDecryptString(String str) {
		BASE64Decoder base64Decoder = new BASE64Decoder();
		try {
			byte[] strBytes = base64Decoder.decodeBuffer(str);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] encryptStrBytes = cipher.doFinal(strBytes);
			return new String(encryptStrBytes, "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static String baseNumLetter = "0123456789ABCDEFGHJKLMNOPQRSTUVWXYZ";

	/**
	 * 获取验证码/安全码
	 * 
	 * @param num
	 *            - 长度
	 * @return
	 */
	public static String getSecurityCode(int num) {
		StringBuffer sb = new StringBuffer();
		String ch = "";
		for (int i = 0; i < num; i++) {
			ch = baseNumLetter.charAt(new Random().nextInt(baseNumLetter
					.length())) + "";
			sb.append(ch);
		}
		return sb.toString();
	}
	
	public static String mailContent1 = "Please use the following initial password activate the portal-demo account : ";
	public static String mailContent2 = "Password : ";
	public static String mailContent3 = "请使用如下初始化密码激活您的管理平台账户：";
	public static String mailContent4 = "密码：";
	public static String mailContent5 = "Thanks,</br>Account Team";
	public static String mailContent6 = "Please visit : ";
	public static String mailContent7 = "请访问：";
	public static String mailSubject = "portal-demo Acount Initialization";
	
	/**
	 * 获取邮件初始化密码正文
	 * @param account
	 * @param securityCode
	 * @return
	 */
	public static String getInitPassContent(String account, String password) {
		StringBuffer content = new StringBuffer();
		content.append(mailContent1).append(account).append("</br>")
				.append(mailContent2).append(password).append("</br>")
				.append(mailContent6).append(PropsUtil.getValue("host_url")).append("</br></br>")
				.append(mailContent3).append(account).append("</br>")
				.append(mailContent4).append(password).append("</br>")
				.append(mailContent7).append(PropsUtil.getValue("host_url")).append("</br></br>")
				.append(mailContent5);
		return content.toString();
	}
	
	public static String securityContent1 = "Please use the following security code for the portal-demo account : ";
	public static String securityContent2 = "Security Code : ";
	public static String securityContent3 = "请使用如下验证码验证您的管理平台账户：";
	public static String securityContent4 = "验证码：";
	public static String securityContent5 = "Thanks,</br>Account Team";
	public static String securitySubject = "portal-demo Acount Security Code";
	public static String securityContent6 = "Security code will be invalid after 1 hours.</br></br>";
	public static String securityContent7 = "验证码会在邮件发送1小时后失效。</br></br>";
	
	/**
	 * 获取邮件初始化密码正文
	 * @param account
	 * @param securityCode
	 * @return
	 */
	public static String getSecurityCodeContent(String account, String password) {
		StringBuffer content = new StringBuffer();
		content.append(securityContent1).append(account).append("</br>")
				.append(securityContent2).append(password).append("</br>")
				.append(securityContent6)
				.append(securityContent3).append(account).append("</br>")
				.append(securityContent4).append(password).append("</br>")
				.append(securityContent7)
				.append(securityContent5);
		return content.toString();
	}

	public static void main(String[] args) {
		System.out.println(md5("sensetime20181016"));
	}

}
