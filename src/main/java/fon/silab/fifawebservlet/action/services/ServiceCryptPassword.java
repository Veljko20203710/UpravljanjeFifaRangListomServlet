/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.fifawebservlet.action.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Veljko
 */
public class ServiceCryptPassword {

    private ServiceCryptPassword() {}

    public static String cryptPassword(String passoword) {
        String cyrptedPassword = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(passoword.getBytes());
            byte[] digest = messageDigest.digest();
            for (byte b : digest) {
                cyrptedPassword += String.format("%02x", b);
            }
            return cyrptedPassword;
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
}
