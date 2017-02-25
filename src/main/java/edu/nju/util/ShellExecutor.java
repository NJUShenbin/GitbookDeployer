package edu.nju.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sbin on 2017/1/4.
 */
public class ShellExecutor {

    public static void execute(String shellName,String... args){

        if(!shellName.startsWith("/")){
            shellName = "./"+shellName;
        }

        List<String> argList = new ArrayList<>();
        argList.add("sh");
        argList.add(shellName);
        argList.addAll(Arrays.asList(args));

        ProcessBuilder pb = new ProcessBuilder
                (argList.toArray(new String[argList.size()]));
        String s = null;

        try {
            Process p = pb.start();
            BufferedReader buf = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            while ((line=buf.readLine())!=null) {
                System.out.println(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return;
    }

}
