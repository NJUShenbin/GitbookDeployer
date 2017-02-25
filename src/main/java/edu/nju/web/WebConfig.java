package edu.nju.web;

import edu.nju.web.interceptor.BookInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by sbin on 2017/1/4.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private BookInterceptor bookInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(bookInterceptor);
    }

}
