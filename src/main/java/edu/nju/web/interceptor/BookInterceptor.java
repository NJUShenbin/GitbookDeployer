package edu.nju.web.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Created by sbin on 2017/1/4.
 */
@Component
public class BookInterceptor extends HandlerInterceptorAdapter {

    @Value("${spring.resources.staticLocations}")
    private String staticLocation;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //处理对书首页的请求.
        //假设一本书叫B,直接请求 /B 会重定向至/error,返回404
        //这里发现这种情况,检查 /static(默认的静态文件夹)/B/index.html是否存在,
        //假如存在,将请求重定向至index
        if(request.getRequestURI().equals("/error")&&response.getStatus()==404){

            String staticDir = staticLocation.split(":")[1];

            String requestURI = getRequestUri(request);
            String indexFilePath = staticDir+requestURI+"index.html";
            File indexFile = new File(indexFilePath);
            if(indexFile.exists()){
                String indexFileUrl = requestURI+"index.html";

                if(!indexFileUrl.startsWith("/")){
                    indexFileUrl = "/"+indexFileUrl;
                }

                response.setStatus(200);
                response.sendRedirect(indexFileUrl);
                return false;
            }
        }

        return true;
    }

    //直接获得request.uri会得到/error,这里取出原始请求,找出原始请求uri
    private String getRequestUri(HttpServletRequest request){

        if( !(request instanceof HttpServletRequestWrapper)){
            return request.getRequestURI();
        }

        HttpServletRequest originRequest =
                (HttpServletRequest)
                        ((HttpServletRequestWrapper)request).getRequest();
        //if url is a.b.com/oneBook, requestURI is oneBook
        String requestURI = originRequest.getRequestURI().substring(1);
        if(!requestURI.endsWith("/")){
            requestURI+="/";
        }

        return requestURI;
    }
}
