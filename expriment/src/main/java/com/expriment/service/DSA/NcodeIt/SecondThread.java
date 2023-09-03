package com.expriment.service.DSA.NcodeIt;

public class SecondThread extends Thread
{
   /* @Override
    public void run(){
        System.out.println("Thread is running   "+Thread.currentThread().getName());
        getDetails();
    }
*/
    public void getDetails(){
        System.out.println("Another method"+Thread.currentThread().getName());
    }

    public static void main(String args[]){
        SecondThread st=new SecondThread();
        System.out.println("executed by main thread\t"+Thread.currentThread().getName()+" "+Thread.currentThread().getPriority() );
        st.start();
    }
}
