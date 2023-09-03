package com.expriment.service.DSA.NcodeIt;

public class MyThread extends Thread
{
    @Override
    public void run()
    {
       System.out.println(Thread.currentThread().getId()+" "+
               Thread.currentThread().getName()
               +" "+Thread.currentThread().getPriority()+" Time: "+System.currentTimeMillis());
    }

    public static void main(String args[])
    {
        for(int i=1;i<=5;i++)
        {
            MyThread mt = new MyThread();
            //start method creates new thread and calls run() method
            mt.start();

            mt.setPriority(i);
        }
    }
}
