package com.company;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

    private static boolean isBusy = false;

    public Conflict (String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public synchronized void bow(Conflict bower) throws InterruptedException {
        if (!isBusy) {

            isBusy = true;

            System.out.format("%s: %s" + " пропускає мене!%n", this.name, bower.getName());
            Thread.sleep(1500);

            isBusy = false;

            bower.bowBack(this);

        } else {

          while (isBusy) {
              try {
                 wait();
              } catch (InterruptedException ignored) { }
          }
        }
    }

    public synchronized void bowBack(Conflict bower) throws InterruptedException {
            System.out.format("%s: %s"
                            + " пропускає мене у відповідь!%n",
                    this.name, bower.getName());

            this.bow(bower);
    }
}