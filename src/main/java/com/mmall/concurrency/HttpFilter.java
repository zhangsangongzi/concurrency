package com.mmall.concurrency;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.mmall.concurrency.example.threadLocal.RequestHolder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpFilter implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException
    {
        // TODO Auto-generated method stub
        HttpServletRequest request = (HttpServletRequest)arg0;
        log.info("do filter,{}, {}", Thread.currentThread().getId(), request.getServletPath());
        RequestHolder.add(Thread.currentThread().getId());
        arg2.doFilter(arg0,arg1);
        
    }
    
    @Override
    public void destroy()
    {
        
    }

}
