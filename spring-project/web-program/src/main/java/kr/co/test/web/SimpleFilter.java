package kr.co.test.web;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class SimpleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        log.info("Filter - Hello world");
        chain.doFilter(request, response);
        var writer = response.getWriter();
        writer.println("filter - Hello World!!");
    }
}
