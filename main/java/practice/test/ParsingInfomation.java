package practice.test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ParsingInfomation {
    private String information;
    public ParsingInfomation(String infomation){
        this.information = infomation;
    }

    public String getInformation(){
        return information;
    }
    public String[] parsing (String infomation){
        String[] parsedInfomation = infomation.split("/");
        return parsedInfomation;
    }

}
