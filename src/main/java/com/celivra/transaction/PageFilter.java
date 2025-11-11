package com.celivra.transaction;

import com.celivra.transaction.Pojo.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class PageFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //获取当前的req和resp
        HttpServletRequest req= (HttpServletRequest) servletRequest;
        HttpServletResponse resp= (HttpServletResponse) servletResponse;

        //从当前req中获取当前访问的接口路径
        String path=req.getRequestURI();

        List<String> whiteList = Arrays.asList(
                "/",              // 首页
                "/index",         // 主页
                "/Login", "/doLogin",   // 登录页与登录请求
                "/Register", "/doReg"   // 注册页与注册请求
        );

        boolean isStatic = path.startsWith("/css/")
                || path.startsWith("/js/")
                || path.startsWith("/images/")
                || path.startsWith("/static/");

        if (whiteList.contains(path) || isStatic) {
            filterChain.doFilter(req, resp);
            return;
        }

        //获取当前存在的用户信息
        User user = (User) req.getSession().getAttribute("user");

        //若存在user或者admin，则放行
        //否则跳转到/login提示用户进行登入
        if(user != null){
            filterChain.doFilter(servletRequest, servletResponse);
        } else{
            resp.sendRedirect("/Login");
        }
    }
}
