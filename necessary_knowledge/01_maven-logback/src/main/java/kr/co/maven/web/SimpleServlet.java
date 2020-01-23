package kr.co.maven.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SimpleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 소켓 통신을 통해 넘어온 http request를 소켓 통신으로 사용자에게 응답(response)하는 구조
        PrintWriter writer = resp.getWriter();
        writer.println("Hello World!!");
    }
}
