package com.company;

public class Main {

    public static void main(String[] args) {
        Conflict conflict1 = new Conflict("C1");
        Conflict conflict2 = new Conflict("C2");

        Thread one = new Thread(() -> {
            while (true) {
                try {
                    conflict1.bow(conflict2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread two = new Thread(() -> {
            while (true) {
                try {
                    conflict2.bow(conflict1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        one.start();
        two.start();
    }
}