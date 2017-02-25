package edu.nju.logic.deploy;

import edu.nju.logic.vo.DeployRequest;
import edu.nju.logic.vo.DeployResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by sbin on 2017/1/13.
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BookDeployer {

    @Autowired
    private DeployRequestConsumer requestConsumer;
    private LinkedBlockingQueue<DeployRequest> requestQueue;
    private String gitUrl;
    private Thread consumerThread;

    public BookDeployer(String gitUrl){
        this.gitUrl = gitUrl;
    }

    @PostConstruct
    private void init(){
        requestQueue = new LinkedBlockingQueue<>();
        requestConsumer.setRequestQueue(requestQueue);

        consumerThread = new Thread(requestConsumer);
        consumerThread.start();
    }


    /**
     * 请求deploy一个book,这里只是发送一个部署命令并立即返回,部署操作由consumer执行.
     * @param request
     * @return DeployResponse
     */
    public DeployResponse deployOneBook(DeployRequest request) {

        DeployResponse response = new DeployResponse();
        response.setDate(new Date());

        String gitUrl = request.getRepository().getUrl();
        if(!match(gitUrl)){
            response.setMessage("not match,require "+this.gitUrl+",but got "+gitUrl);
            return response;
        }

        requestQueue.add(request);

        response.setMessage("success");
        return response;
    }

    public boolean match(String gitUrl){
        return this.gitUrl.equals(gitUrl);
    }

}
