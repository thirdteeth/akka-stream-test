package com.github.thirdteeth.akka.actor.sample2;

import akka.actor.AbstractLoggingActor;
import akka.actor.Props;

public class AlarmActor extends AbstractLoggingActor {

    static class Activity {}
    static class Disable {
        private final String password;

        public Disable(String password) {
            this.password = password;
        }
    }

    static class Enable {
        private final String password;

        public Enable(String password) {
            this.password = password;
        }
    }

    private final String password;

    public AlarmActor(String password) {
        this.password = password;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Enable.class, this::onEnable)
                .build();
    }

    private void onEnable(Enable enable) {
        if (password.equals(enable.password)) {
            log().info("Alarm enable");
            getContext().become(receiveBuilder()
                    .match(Disable.class, this::onDisable)
                    .match(Activity.class, this::onActivity)
                    .build());
        } else {
            log().info("Someone failed to enable the alarm");
        }
    }

    private  void onActivity(Activity activity) {
        log().warning("oeoeoeoeoe, alarm alarm!!!");
    }

    private void onDisable(Disable disable) {
        if (password.equals(disable.password)) {
            log().info("Alarm disabled");
            getContext().become(receiveBuilder()
                    .match(Enable.class, this::onEnable)
                    .build());
        } else {
            log().warning("Someone who didn't know the password tried to disable it");
        }
    }

    public static Props props(String password) {
        return Props.create(AlarmActor.class, password);
    }
}
