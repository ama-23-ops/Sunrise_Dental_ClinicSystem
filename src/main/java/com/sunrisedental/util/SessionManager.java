package com.sunrisedental.util;
import com.sunrisedental.model.User;
public final class SessionManager {
    private static User currentUser;
    private SessionManager(){}
    public static void login(User user){ currentUser=user; }
    public static User getCurrentUser(){ return currentUser; }
    public static void logout(){ currentUser=null; }
    public static boolean isAdmin(){ return currentUser!=null && "ADMIN".equalsIgnoreCase(currentUser.getRole()); }
}
