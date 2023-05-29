package com.example.springdemo.Servlet;

import com.example.springdemo.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(urlPatterns = "/TestServlet")
public class TestServlet extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = -3325041776508043481L;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }
    /*
     *  实现请求uri和header打印，另外返回一个json
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("RequestURI:" + req.getRequestURI());

        System.out.println("Request Headers:");

        StringBuilder sb = new StringBuilder();
        Enumeration<?> names = req.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement().toString();
            Enumeration<?> hs = req.getHeaders(name);
            sb.append(name).append(":");
            while (hs.hasMoreElements()) {
                sb.append(hs.nextElement()).append(";");
            }
        }
        System.out.println(sb);

        ObjectMapper om=new ObjectMapper();
        User user=new User();
        user.setId(1);
        user.setName("zwqh");
        user.setAge(18);
        String resultJson=om.writeValueAsString(user);

        resp.setContentType("application/json;charset=UTF-8");

        resp.getWriter().print(resultJson);
    }
}
