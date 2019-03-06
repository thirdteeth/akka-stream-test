package com.github.thirdteeth.akka.actor.sample3;

import akka.actor.*;
import akka.japi.pf.DeciderBuilder;
import scala.concurrent.duration.Duration;
import static akka.actor.SupervisorStrategy.*;

public class Supervisor extends AbstractLoggingActor {

    final ActorRef child = getContext().actorOf(NonTrustWorthyChild.props(), "child");

    public static final OneForOneStrategy STRATEGY = new OneForOneStrategy(
            10,
            Duration.create("10 seconds"),
            DeciderBuilder
                    .match(RuntimeException.class, ex -> stop())
                    .build()
    );

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchAny( any -> child.forward(any, getContext()))
                .build();
    }

    public static Props props() {
        return Props.create(Supervisor.class);
    }

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return STRATEGY;
    }


}
