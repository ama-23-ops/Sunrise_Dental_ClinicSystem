package com.sunrisedental.util;
import org.mindrot.jbcrypt.BCrypt;

public final class PasswordUtil {
    private PasswordUtil(){}
    public static String hashPassword(String password){ return BCrypt.hashpw(password, BCrypt.gensalt(12)); }
    public static boolean matches(String plain, String hash){
        if(hash == null || !hash.startsWith("$2")) return false;
        return BCrypt.checkpw(plain, hash);
    }
}
