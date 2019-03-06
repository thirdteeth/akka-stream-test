package com.github.thirdteeth.akka.actor.sample1;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.github.thirdteeth.akka.actor.util.StdIn;

public class Sample1 {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("sample1");

        final ActorRef counter = system.actorOf(CounterActor.props(), "counter");

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    counter.tell(new CounterActor.Message(), ActorRef.noSender());
                }
            }).start();
        }


        System.out.println("ENTER to terminate");
        StdIn.readLine();
        system.terminate();
    }
}
