package me.oudedong.springbootDeveloper.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.SerializationUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Base64;
import java.util.List;

public class CookieUtil {
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge){

        Cookie cookie = new Cookie(name, value);

        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name){

        Cookie[] cookies = request.getCookies();

        if(cookies == null) return;
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(name)){
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }
    public static String serialize(Object obj){
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(obj));
    }
    public static <T> T deSerialize(Cookie cookie, Class<T> cls) throws Exception {
        return cls.cast((new ObjectInputStream(new ByteArrayInputStream(Base64.getUrlDecoder().decode(cookie.getValue()))).readObject()));
    }
}
