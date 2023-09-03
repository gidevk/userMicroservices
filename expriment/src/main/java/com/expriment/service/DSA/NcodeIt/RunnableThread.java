package com.expriment.service.DSA.NcodeIt;

public class RunnableThread implements Runnable
{

    @Override
    public void run() {
        System.out.println("Thread is running");
    }

    public static void main(String args[])
    {
        //current class instance
        RunnableThread rt=new RunnableThread();

        //passing current class instance to thread class constructor
        Thread thread=new Thread(rt);

        thread.start();
    }
}
