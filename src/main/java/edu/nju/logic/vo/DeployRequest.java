package edu.nju.logic.vo;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by sbin on 2017/1/3.
 */
@Data
public class DeployRequest {

    private Repository repository;
    private Sender sender;

}
