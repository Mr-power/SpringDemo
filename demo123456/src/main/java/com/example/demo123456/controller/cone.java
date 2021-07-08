package com.example.demo123456.controller;


import com.example.demo123456.Emun.exceCodeAll;
import com.example.demo123456.Thread.Tone;
import com.example.demo123456.service.codeMessage;
import com.example.demo123456.service.sone;
import com.google.common.util.concurrent.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import javax.websocket.server.PathParam;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping(value="/test")
public class cone {
    /**
     * Guava异步回调
     *
     */

    @RequestMapping(value = "/subscribeNew001", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String getResult(@RequestParam (value="q") String flag){
        String[] message = new String[1];
        //controller类提交任务给线程
        Callable<String > getNewString = new Tone(flag);
        ExecutorService pool = Executors.newFixedThreadPool(5);
        ListeningExecutorService guavaPool= MoreExecutors.listeningDecorator(pool);
        ListenableFuture<String> getFlag=guavaPool.submit(getNewString);
        Futures.addCallback(getFlag, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String s) {
                message[0] =s;
            }

            @Override
            public void onFailure(Throwable throwable) {
                message[0] ="失败的数据";
                System.out.println("获取数据失败");
            }
        });

        return message[0];



    }
}
