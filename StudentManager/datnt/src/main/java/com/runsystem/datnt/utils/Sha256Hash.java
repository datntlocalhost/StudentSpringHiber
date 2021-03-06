package com.runsystem.datnt.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256Hash {
	
	public static String hash(String text) {
		MessageDigest digest = null;
		StringBuffer hexString = new StringBuffer();
		
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hashCode = digest.digest(text.getBytes(StandardCharsets.UTF_8)); 
			for (int i = 0; i < hashCode.length; i++) {
				String hex = Integer.toHexString(0xff & hashCode[i]);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		return hexString.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(hash("admin"));
	}
}
