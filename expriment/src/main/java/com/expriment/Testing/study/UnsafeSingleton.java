package com.expriment.Testing.study;
public class UnsafeSingleton {
    private static UnsafeSingleton instance;
    private boolean initialized = false;

    private UnsafeSingleton() {
        try { Thread.sleep(100); } catch (InterruptedException e) {}
        initialized = true;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public static UnsafeSingleton getInstance() {
        if (instance == null) {
            synchronized (UnsafeSingleton.class) {
                if (instance == null) {
                    instance = new UnsafeSingleton();
                }
            }
        }
        return instance;
    }
}
