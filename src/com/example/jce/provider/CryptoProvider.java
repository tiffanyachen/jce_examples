package com.example.jce.provider;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;

public class CryptoProvider extends Provider {
	
	 private static final long serialVersionUID = -67123468605911408L;

	 public CryptoProvider() {
	  super("SimpleCrypto", 1.0, "XOR digest v1.0");

	 put("MessageDigest.XOR", XORDigest.class.getName());
	 }
	
	 public static void main(String[] args) {

		  Security.addProvider(new CryptoProvider());

		  try {
		   MessageDigest digest = MessageDigest.getInstance("XOR");
		   byte[] checksum = digest.digest(new byte[] { 0x01, 0x23, 0x45 });
		   System.out.println("Checksum: 0x" + Integer.toHexString(checksum[0]));

		  } catch (NoSuchAlgorithmException e) {
		   e.printStackTrace();
		  }
		 }
	 

}
