package com.example.reflection.interfaces;

public interface ChildInterface extends BaseInterface {
    default void show() {
        System.out.println("Default method in ChildInterface");
    }
}