package edu.nju.logic.deploy;

import edu.nju.logic.vo.DeployRequest;
import edu.nju.util.ShellExecutor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * Created by sbin on 2017/1/13.
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Log
public class DeployRequestConsumer implements Runnable {

    private BlockingQueue<DeployRequest> requestQueue;

    @Value("${gitbook.deployShell}")
    private String deployShell;

    public void setRequestQueue(BlockingQueue<DeployRequest> requestQueue) {
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        while (true){

            DeployRequest request = null;
            try {
                request = requestQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(requestQueue.size()>0){
                log.info("将 "+(1+requestQueue.size())+" 个请求合并处理");
            }

            while (!requestQueue.isEmpty()){
                request = requestQueue.poll();
            }

            executeDeployScript(request);
        }
    }

    private void executeDeployScript(DeployRequest request){
        String repoName = request.getRepository().getName();
        String gitUrl = request.getRepository().getUrl();
        ShellExecutor.execute(deployShell,gitUrl,repoName);
    }

}
