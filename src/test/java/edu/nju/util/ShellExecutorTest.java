package edu.nju.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sbin on 2017/1/7.
 */
public class ShellExecutorTest {
    @Test
    public void execute() throws Exception {
//        ShellExecutor.execute("sh","deployHtml.sh",
//                "https://github.com/NjuHumanComputer/HeuristicEvaluation",
//                "HeuristicEvaluation");

        Runtime.getRuntime().exec("sh deployHtml.sh https://github.com/NjuHumanComputer/HeuristicEvaluation HeuristicEvaluation");

    }

}