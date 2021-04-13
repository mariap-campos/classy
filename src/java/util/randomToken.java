/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.security.SecureRandom;

/**
 *
 * @author Maria Paula
 */

public class randomToken {
    public static final String ab = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUM = "0123456789";
    public static final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static SecureRandom rnd = new SecureRandom();
    
    public static String randomString() {
    StringBuilder sb = new StringBuilder(10);
    for(int i = 0; i < 3; i++) {
       sb.append(AB.charAt(rnd.nextInt(AB.length())));
       sb.append(NUM.charAt(rnd.nextInt(NUM.length())));
       sb.append(ab.charAt(rnd.nextInt(NUM.length())));
    }
    
    return sb.toString();
    }
}
