package com.github.thirdteeth.akka.actor.sample2;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.github.thirdteeth.akka.actor.util.StdIn;

public class Sample2 {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create();

        final ActorRef alarm = system.actorOf(AlarmActor.props("cat"), "alarm");

        alarm.tell(new AlarmActor.Activity(), ActorRef.noSender());
        alarm.tell(new AlarmActor.Enable("dogs"), ActorRef.noSender());
        alarm.tell(new AlarmActor.Enable("cat"), ActorRef.noSender());
        alarm.tell(new AlarmActor.Activity(), ActorRef.noSender());
        alarm.tell(new AlarmActor.Disable("dogs"), ActorRef.noSender());
        alarm.tell(new AlarmActor.Disable("cat"), ActorRef.noSender());
        alarm.tell(new AlarmActor.Activity(), ActorRef.noSender());

        System.out.println("ENTER to terminate");
        StdIn.readLine();
        system.terminate();
    }
}
