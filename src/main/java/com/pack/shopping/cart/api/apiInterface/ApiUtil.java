package com.pack.shopping.cart.api.apiInterface;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.NativeWebRequest;

public class ApiUtil {
    
    private static HttpServletResponse res;

    public static void setExampleResponse(NativeWebRequest req, String contentType, String example) {
        try {
            res = req.getNativeResponse(HttpServletResponse.class);
            res.setCharacterEncoding("UTF-8");
            res.addHeader("Content-Type", contentType);
            res.getWriter().print(example);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
