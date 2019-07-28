package com;

import static org.junit.Assert.assertEquals;

import akka.actor.Props;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;

public class AkkaQuickstartTest {
    static ActorSystem system;

    @BeforeClass
    public static void setup() {
        system = ActorSystem.create();
    }

    @AfterClass
    public static void teardown() {
        TestKit.shutdownActorSystem(system);
        system = null;
    }

    @Test
    public void testGreeterActorSendingOfGreeting() {
        final TestKit testProbe = new TestKit(system);
        final ActorRef TestFish = system.actorOf(Props.create(Fish.class), "TestFish");
        TestFish.tell("hi", ActorRef.noSender());
        TestFish.tell("alone", ActorRef.noSender());
    }
}
