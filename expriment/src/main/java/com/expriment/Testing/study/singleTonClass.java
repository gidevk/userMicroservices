package com.expriment.Testing.study;

public class singleTonClass {
    private static volatile singleTonClass instance;

    public singleTonClass(){

    }

    public static singleTonClass getInstance() {
        if (instance == null){
            synchronized (singleTonClass.class){
                if (instance == null){
                    instance= new singleTonClass();
                }
            }
        }
        return instance;
    }
}
