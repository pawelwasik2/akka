package com;

import akka.actor.AbstractActor;

public class Fish extends AbstractActor {
    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> {
                    if (s.equals("hi")) {
                        System.out.println(getSelf().path().name() + ": Hello, " + getSender().path().name() + ". Nice to meet you");
                        getSender().tell("meet", getSelf());
                    } else if(s.equals("alone")) {
                        System.out.println("I'm " + getSelf().path().name() + " and I'm so alone");
                    }else if(s.equals("meet")) {
                        System.out.println(getSelf().path().name() + ": Nice to meet you too " + getSender().path().name());
                    }else if(s.equals("bye")) {
                        System.out.println(getSelf().path().name() + ": Good bye, " + getSender().path().name());
                        getSender().tell("byebye", getSelf());
                    }else if(s.equals("byebye")) {
                        System.out.println(getSelf().path().name() + ": Good bye!");
                    }
                })
                .matchAny(o -> System.out.println("Sorry i dont speak your langauge"))
                .build();
    }
}