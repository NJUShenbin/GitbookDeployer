package edu.nju.logic.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created by sbin on 2017/1/3.
 */
@Data
public class DeployResponse {

    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date date;

}
