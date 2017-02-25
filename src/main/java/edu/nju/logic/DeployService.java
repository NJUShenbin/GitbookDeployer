package edu.nju.logic;

import edu.nju.logic.deploy.BookDeployer;
import edu.nju.logic.deploy.DeployerFactory;
import edu.nju.logic.vo.DeployRequest;
import edu.nju.logic.vo.DeployResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by sbin on 2017/1/3.
 */
@Service
public class DeployService {

    List<BookDeployer> deployerList = new ArrayList<>();

    @Autowired
    DeployerFactory deployerFactory;

    public DeployResponse deploy(DeployRequest request){

        String gitUrl = request.getRepository().getUrl();

        //在list中找符合git url的deployer. deployer和gitUrl一一对应.
        Optional<BookDeployer> deployerOptional = deployerList
                .stream()
                .filter((deployer)->deployer.match(gitUrl))
                .findFirst();

        if(deployerOptional.isPresent()){
            return deployerOptional.get().deployOneBook(request);
        }
        //没找到就创建一个新的
        else {
            BookDeployer deployer = deployerFactory.createBookDeployer(gitUrl);
            deployerList.add(deployer);
            return deployer.deployOneBook(request);
        }
    }

}
