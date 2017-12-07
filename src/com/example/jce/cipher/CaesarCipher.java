package com.example.jce.cipher;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;

public class CaesarCipher extends CipherSpi {

	private Key mKey;
	private int mOPMode;

	@Override
	protected byte[] engineDoFinal(byte[] input, int inputOffset, int inputLen)
			throws IllegalBlockSizeException, BadPaddingException {
		return engineUpdate(input, inputOffset, inputLen);
	}

	@Override
	protected int engineDoFinal(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset)
			throws ShortBufferException, IllegalBlockSizeException, BadPaddingException {
		return engineUpdate(input, inputOffset, inputLen, output, outputOffset);
	}

	@Override
	protected int engineGetBlockSize() {
		return 1;
	}

	@Override
	protected byte[] engineGetIV() {
		return null;
	}

	@Override
	protected int engineGetOutputSize(int inputLen) {
		return 0;
	}

	@Override
	protected AlgorithmParameters engineGetParameters() {
		return null;
	}

	@Override
	protected void engineInit(int opmode, Key key, SecureRandom random) throws InvalidKeyException {
		mOPMode = opmode;

		if (key instanceof CaesarKey)
			mKey = key;

		else
			throw new InvalidKeyException("Caesar cipher needs a Caesar key");
	}

	@Override
	protected void engineInit(int opmode, Key key, AlgorithmParameterSpec params, SecureRandom random)
			throws InvalidKeyException, InvalidAlgorithmParameterException {
		engineInit(opmode, key, random);
	}

	@Override
	protected void engineInit(int opmode, Key key, AlgorithmParameters params, SecureRandom random)
			throws InvalidKeyException, InvalidAlgorithmParameterException {
		engineInit(opmode, key, random);
	}

	@Override
	protected void engineSetMode(String mode) throws NoSuchAlgorithmException {
		throw new NoSuchAlgorithmException();
	}

	@Override
	protected void engineSetPadding(String pading) throws NoSuchPaddingException {
		throw new NoSuchPaddingException();
	}

	@Override
	protected byte[] engineUpdate(byte[] input, int inputOffset, int inputLen) {
		byte[] output = new byte[inputLen];
		try {
			engineUpdate(input, inputOffset, inputLen, output, 0);
		} catch (ShortBufferException e) {
			// output buffer is always big enough
		}

		return output;
	}

	@Override
	protected int engineUpdate(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset)
			throws ShortBufferException {
		if (output.length - outputOffset < inputLen)
			throw new ShortBufferException("Buffer too short to hold encrypted data");

		for (int index = inputOffset; index < inputOffset + inputLen; index++)
			output[outputOffset + index - inputOffset] = twistCharacter(input[index], getKey());

		return inputLen;
	}

	private byte getKey() {
		// calculate key depending on encryption/decryption
		if (mOPMode == Cipher.ENCRYPT_MODE)
			return mKey.getEncoded()[0];

		// decode using inverse key
		return (byte) (26 - mKey.getEncoded()[0]);
	}

	private byte twistCharacter(byte data, byte key) {

		if (Character.isLetter(data)) {
			// only operate on characters, leave everything else as-is
			if (Character.isLowerCase(data))
				return (byte) (((data - 'a' + key) % 26) + 'a');
			else
				return (byte) (((data - 'A' + key) % 26) + 'A');
		}

		return data;
	}

	@Override
	protected int engineGetKeySize(Key key) throws InvalidKeyException {
		if (key instanceof CaesarKey)
			return 1;

		throw new InvalidKeyException("Caesar cipher needs a Caesar key");
	}
}