package com.example.demo123456.Thread;

import java.util.concurrent.Callable;

public class Tone implements Callable< String > {
    private String flag;
    final private String flagnew="处理后的";

    public  Tone(String biubiu){
        this.flag=biubiu;
    }

    @Override
    public String call() throws Exception {
        flag=flagnew+flag;
        return flag;
    }
}
