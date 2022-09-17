package com.company;

/**
 * Created by IntelliJ IDEA.
 * lab3.Conflict
 *
 * @Author: Palijchuk Nazar
 * @DateTime: 17.09.2022|15:35
 * @Version Conflict: 1.0
 */

class Conflict {
    private final String name;

    private static boolean isFree = true;

    public Conflict (String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public synchronized void bow(Conflict bower) throws InterruptedException {
        if (!isFree) {
            while (!isFree) {
                try {
                    wait();
                } catch (InterruptedException ignored) { }
            }
        }

        isFree = false;

        System.out.format("%s: %s" + " пропускає мене!%n", this.name, bower.getName());
        Thread.sleep(1000);
        bower.bowBack(this);

        isFree = true;
    }

    public synchronized void bowBack(Conflict bower) {
        System.out.format("%s: %s"
                        + " пропускає мене у відповідь!%n",
                this.name, bower.getName());
    }
}