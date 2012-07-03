package com.cityproperties.util.encrypt;

import org.jasypt.util.password.BasicPasswordEncryptor;

public class EncryptPassword {
	
	private static BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
	
	public static String encrypt(String password) {
		return passwordEncryptor.encryptPassword(password);
	}
	
	public static boolean checkPassword(String plainPassword, String encryptedPassword) {
		return passwordEncryptor.checkPassword(plainPassword, encryptedPassword);
	}

}
