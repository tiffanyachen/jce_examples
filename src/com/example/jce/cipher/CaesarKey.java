package com.example.jce.cipher;

import javax.crypto.SecretKey;

public class CaesarKey implements SecretKey {
 private static final long serialVersionUID = -542084808194990775L;

 private byte mValue;

 public CaesarKey(byte value) {
  // converts any value to [0, 26), even negative ones
  mValue = (byte) (((value % 26) + 26) % 26);
 }

 @Override
 public String getAlgorithm() {
  return "Caesar";
 }

 @Override
 public String getFormat() {
  return getAlgorithm() + " format";
 }

 @Override
 public byte[] getEncoded() {
  return new byte[] { mValue };
 }
}
