package com.github.thirdteeth.akka.actor.sample1;

import akka.actor.AbstractLoggingActor;
import akka.actor.Props;

public class CounterActor extends AbstractLoggingActor {

    private int counter;

    static class Message {}

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Message.class, this::onMessage)
                .build();
    }

    private void onMessage(Message message) {
        counter ++;
        log().info("Increased counter: " + counter);
    }

    public static Props props() {
        return Props.create(CounterActor.class);
    }
}
