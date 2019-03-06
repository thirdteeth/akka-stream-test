package com.github.thirdteeth.akka.actor.sample3;

import akka.actor.AbstractLoggingActor;
import akka.actor.Props;

import java.util.Optional;

public class NonTrustWorthyChild extends AbstractLoggingActor {

    public static class Command {}

    private long messages = 0L;
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Command.class, this::onCommand)
                .build();
    }

    private void onCommand(Command c) {
        messages++;
        if (messages % 4 == 0) {
            throw new RuntimeException("Oh no, I got four commands, I can't handle any more");
        } else {
            log().info("Got a command " + messages);
        }
    }

    public static Props props() {
        return Props.create(NonTrustWorthyChild.class);
    }

    @Override
    public void preRestart(Throwable reason, Optional<Object> message) throws Exception {
        super.preRestart(reason, message);
        log().info("NonTrustWorthyChild pre-restart");
    }

    @Override
    public void postRestart(Throwable reason) throws Exception {
        super.postRestart(reason);
        log().info("NonTrustWorthyChild post-restart");
    }
}
