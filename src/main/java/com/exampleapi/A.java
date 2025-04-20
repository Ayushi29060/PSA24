package com.exampleapi;

import org.hibernate.query.sqm.mutation.internal.cte.CteInsertStrategy;

public class A {
    int x = 10;

    public static void main(String[]args) {
          B b1 = new B();
          int x = b1.otpGen();
          System.out.println(x);

    }

}

