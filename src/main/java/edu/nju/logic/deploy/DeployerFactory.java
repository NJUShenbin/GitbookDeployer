package edu.nju.logic.deploy;

import ch.qos.logback.core.spi.ContextAware;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by sbin on 2017/1/4.
 */
@Component
public class DeployerFactory implements ApplicationContextAware{

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        context = applicationContext;
    }

    public BookDeployer createBookDeployer(String gitUrl){
        return context.getBean(BookDeployer.class,gitUrl);
    }

}
