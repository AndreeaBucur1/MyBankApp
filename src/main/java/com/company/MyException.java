package com.company;

public class MyException extends Exception {
    // default constructor
    MyException() {
    }

    // parametrized constructor
    MyException(String str) {
        super(str);
    }

}