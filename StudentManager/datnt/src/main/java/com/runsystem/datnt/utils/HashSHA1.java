package com.runsystem.datnt.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashSHA1 {

	/*
	 * Hash a plaintext to sha1
	 * 
	 * @param plaintext  
	 * @return sha1 string 
	 */
	public static String hashSHA1(String plaintext) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return toString(md.digest(plaintext.getBytes()));
	}

	/*
	 * convert byte code to string
	 * 
	 * @param b       byte code
	 * @return string
	 */
	private static String toString(byte[] b) {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
		}
		return result;
	}

}
