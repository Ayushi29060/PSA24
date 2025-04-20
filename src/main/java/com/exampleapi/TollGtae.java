package com.exampleapi;

import java.util.Scanner;

public class TollGtae {
    public static void main(String[] args){
            int balance= 100;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the amount");
        int amount = scan.nextInt();
        if(amount>balance){
            //throw a custom exception
            try{
                 throw new LowBalance ("Black listed!!");
            }catch(LowBalance e){
                e.printStackTrace();

            }

        }else{
            System.out.println("Done...");
        }
    }

}
