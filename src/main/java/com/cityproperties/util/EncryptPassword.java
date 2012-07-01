package com.cityproperties.util;

import org.jasypt.util.password.BasicPasswordEncryptor;

public class EncryptPassword {
	
	private static BasicPasswordEncryptor passwordEncryptor;
	
	public static String encrypt(String password) {
		return passwordEncryptor.encryptPassword(password);
	}
	
	public static boolean checkPassword(String plainPassword, String encryptedPassword) {
		return passwordEncryptor.checkPassword(plainPassword, encryptedPassword);
	}

	public static void setPasswordEncryptor(BasicPasswordEncryptor passwordEncryptor) {
		EncryptPassword.passwordEncryptor = passwordEncryptor;
	}

}
