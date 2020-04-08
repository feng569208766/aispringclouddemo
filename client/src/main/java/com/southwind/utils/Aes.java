package com.southwind.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.alibaba.fastjson.JSON;
import com.southwind.entity.Menu;
import org.apache.commons.codec.binary.Base64;

import java.security.SecureRandom;


public class Aes {

	public static final String MODE1 = "AES/ECB/PKCS5Padding";
	public static final String MODE2 = "AES/CBC/PKCS5Padding";

	public static void main(String[] args) throws Exception {
		String data = "123456";
		String key = "z3LPVd3DwaEkOhVt";
		//String key = "b55c936cd48048499ffb5d71227711f8";
		//String pas = "Pi/Zx8UQy789JVX55wr+dA==";
		//String pas = "vLe6KVpdi2YDqiY/27HzLg==";
		String pas = "LyxqrScJ1Dy/TDHPqAgXmA==";

		//System.out.println(Aes.encrypt(data, key));

		//System.out.println(Aes.decrypt(Aes.encrypt(data, key), key));

		//System.out.println(Aes.decrypt("oX0/vPmulii8LKvweWGBhw==", key));
		//System.out.println(Aes.decrypt(pas, key));

		//encrypt = encryptOrdecrypt(true,content.getBytes(CHARSET),key,getIV(),AESType.AES_128,EncodeType.AES_CBC_NoPadding);

		//System.out.println(Aes.decrypt(pas,key));
		System.out.println("密钥："+new String(generateDesKey(128),"utf-8"));
		System.out.println("密钥："+ JSON.toJSONString(generateDesKey(128)));
	}

	public static byte[] generateDesKey(int length) throws Exception {
		//实例化
		KeyGenerator kgen = null;
		kgen = KeyGenerator.getInstance("AES");
		//设置密钥长度
		kgen.init(length);
		//生成密钥
		SecretKey skey = kgen.generateKey();
		//返回密钥的二进制编码
		return skey.getEncoded();
	}

	public static String encrypt(String input, String key){
		byte[] crypted = null;
		try{

			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(key.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();

			SecretKeySpec skey = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance(MODE2);
			IvParameterSpec iv = new IvParameterSpec(key.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, skey ,iv);
			crypted = cipher.doFinal(input.getBytes());
		}catch(Exception e){
			System.out.println(e.toString());
		}
		return new String(Base64.encodeBase64(crypted));
	}

	public static String decrypt(String input, String key){
		byte[] output = null;
		try{

			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(key.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();

			SecretKeySpec skey = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance(MODE1);
			IvParameterSpec iv = new IvParameterSpec(key.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skey);

			output = cipher.doFinal(Base64.decodeBase64(input));
		}catch(Exception e){
			System.out.println(e.toString());
		}
		return new String(output);
	}

	//region AES加解密（第三方Open）32

	/*public static String encryptForOpenMerchant(String input, String key) {
		byte[] crypted = null;
		try {
			SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skey);
			crypted = cipher.doFinal(input.getBytes());
		} catch (Exception e) {
			//LOGGER.error("encryptForOpenMerchant,加密异常,{},{},{}",input,key,Extends.getExceptionDetailInfo(e));
		}
		return new String(org.apache.commons.codec.binary.Base64.encodeBase64(crypted));
	}

	public static String decryptForOpenMerchant(String input, String key) {
		byte[] output = null;
		try {
			SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skey);
			output = cipher.doFinal(org.apache.commons.codec.binary.Base64.decodeBase64(input));
			int i = 1/0;
			return new String(output);
		} catch (Exception e) {
			//LOGGER.error("decryptForOpenMerchant,解密异常,{},{},{}",input,key,Extends.getExceptionDetailInfo(e));
		}
		return null;
	}*/

//endregion


}
