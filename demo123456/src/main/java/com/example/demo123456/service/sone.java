package com.example.demo123456.service;

import org.springframework.beans.factory.annotation.Autowired;




public class sone  extends RuntimeException{
    @Autowired
    codeMessage codemessage;
    public sone(codeMessage codemessage){
        this.codemessage=codemessage;
    }
}
