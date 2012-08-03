package com.cityproperties.util.encrypt;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;

public class Encrypter {

    private static final String password = "propertiesre.com";

    /**
     * Checks an unencrypted (plain) password against an encrypted one (a
     * digest) to see if they match.
     *
     * @param inputPassword - the plain password to check.
     * @param encryptedPassword - the digest against which to check the password.
     * @return true if passwords match, false if not.
     */
    public static boolean checkPassword(String inputPassword,
            String encryptedPassword) {
        return (inputPassword.contentEquals(decrypt(encryptedPassword)) ? true : false);
    }

    /**
     * Decrypt a string.
     *
     * @param str - String to decrypt.
     * @return decrypted string.
     */
    public static String decrypt(String str) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(password);
        return textEncryptor.decrypt(str);
    }

    /**
     * Encrypt a string. Encrypted String using this method can be decrypted by
     * using <i>Encrypter.decrypt(String str)</i>.
     *
     * @param str - String to encrypt.
     * @return encrypted string.
     */
    public static String encrypt(String str) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(password);
        return textEncryptor.encrypt(str);
    }

    /**
     * Encrypt a string. There is no way to decrypt the string if this method is
     * used. To allow decryption, use <i>Encrypter.encrypt(String str)</i>
     * instead.
     *
     * @param message - String to encrypt.
     * @return encrypted string.
     */
    public static String encryptPassword(String message) {
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        return passwordEncryptor.encryptPassword(message);
    }

}
