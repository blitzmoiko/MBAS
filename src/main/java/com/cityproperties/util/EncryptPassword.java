package com.cityproperties.util;

import org.jasypt.util.password.BasicPasswordEncryptor;

public class EncryptPassword {
	
	public static String encrypt(String password) {
		
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		
		return passwordEncryptor.encryptPassword(password);
		
	}
	
	public static boolean checkPassword(String plainPassword, String encryptedPassword) {
		
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		
		return passwordEncryptor.checkPassword(plainPassword, encryptedPassword);
		
	}

}
