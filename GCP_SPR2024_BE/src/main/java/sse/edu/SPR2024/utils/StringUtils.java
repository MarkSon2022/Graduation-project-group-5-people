package sse.edu.SPR2024.utils;

public class StringUtils {
    public static String randomPassword(int length) {
        String password = "";
        for (int i = 0; i < length; i++) {
            password += (char) (Math.random() * 26 + 'a');
        }
        return password;
    }
}
