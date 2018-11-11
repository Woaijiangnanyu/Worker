// IMyBinderPoolInterface.aidl
package com.example.guojialin.worker;

// Declare any non-default types here with import statements

interface IMyBinderPoolInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    IBinder queryBinder(int binderCode);
}
