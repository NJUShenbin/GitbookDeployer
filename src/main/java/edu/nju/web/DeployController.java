package edu.nju.web;

import edu.nju.logic.DeployService;
import edu.nju.logic.vo.DeployRequest;
import edu.nju.logic.vo.DeployResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by sbin on 2017/1/3.
 */
@Controller
public class DeployController {

    @Autowired
    DeployService deployService;

    @RequestMapping(value = "/deploy",method = RequestMethod.POST)
    @ResponseBody
    public DeployResponse deploy(@RequestBody DeployRequest request){
        return deployService.deploy(request);
    }

    @RequestMapping(value = "/")
    @ResponseBody
    public String home(){
        return "hello,gitbook deployer!";
    }

}
