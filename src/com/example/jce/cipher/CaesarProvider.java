package com.example.jce.cipher;

import java.security.Provider;

public class CaesarProvider extends Provider {

 private static final long serialVersionUID = -67123468605911408L;

 public CaesarProvider() {
  super("Caesar Cipher", 1.0, "Caesar cipher v1.0");

 // put("KeyGenerator.Caesar", CaesarKeyGenerator.class.getName());
  put("Cipher.Caesar", CaesarCipher.class.getName());
 }
}
