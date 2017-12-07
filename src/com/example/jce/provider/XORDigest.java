package com.example.jce.provider;


import java.security.MessageDigestSpi;


public class XORDigest extends MessageDigestSpi {
	 private byte mChecksum = 0;

	 public XORDigest() {
	 }

	 @Override
	 protected void engineUpdate(byte input) {
	  mChecksum ^= input;
	 }

	 @Override
	 protected void engineUpdate(byte[] input, int offset, int len) {
	  for (int index = offset; index < offset + len; index++)
	   engineUpdate(input[index]);
	 }

	 @Override
	 protected byte[] engineDigest() {
	  return new byte[] { mChecksum };
	 }

	 @Override
	 protected void engineReset() {
	  mChecksum = 0;
	 }
}
