package com.ningboz.mygithubproject.async;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
    public static void main(String[] args) {
        ReadWriteLock rwLock = new ReentrantReadWriteLock();
        Lock readLock = rwLock.readLock();
        Lock writeLock = rwLock.writeLock();
    }
}
