package com.lightbend.akka.sample;

import java.util.Scanner;
import java.util.LinkedList;
import java.lang.*;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class AkkaQuickstart {
  public static void main(String[] args) {
    final ActorSystem system = ActorSystem.create("aquarium");
    LinkedList<ActorRef> Fishes = new LinkedList<>();
    Scanner scanString = new Scanner(System.in);
    Scanner scanInt = new Scanner(System.in);
    while(true) {
      try {
        if (Fishes.isEmpty()) {
          System.out.println("There is no fishes in aquarium. What do you want to do?");
        } else {
          System.out.println(Fishes.size() + " fish/es in aquarium. ");
          for (int i = 0; i < Fishes.size(); i++) {
            System.out.println((Fishes.get(i).path().name()) + " ");
          }
          System.out.println("What do you want to do?");
        }
        System.out.println("1 - Add Fish");
        System.out.println("2 - Delete Fish");
        System.out.println("3 - Close Aquarium");

        int val = scanInt.nextInt();
        switch (val) {
          case 1:
            System.out.println("Choose name for a fish");
            String nName = scanString.nextLine();
            final ActorRef newFish = system.actorOf(Props.create(Fish.class), nName);
            Fishes.add(newFish);
            if (Fishes.size() == 1) {
              (Fishes.getLast()).tell("alone", ActorRef.noSender());
            } else {
              for (int i = 0; i < Fishes.size() - 1; i++) {
                Fishes.get(i).tell("hi", Fishes.getLast());
              }
            }
            break;
          case 2:
            System.out.println("Which fish do you want to leave aquarium?");
            String dName = scanString.nextLine();
            for (int i = 0; i < Fishes.size(); i++) {
              if (dName.equals((Fishes.get(i)).path().name())) {
                for (int j = 0; j < Fishes.size(); j++) {
                  if (dName.equals((Fishes.get(j)).path().name())) {
                    continue;
                  } else {
                    Fishes.get(i).tell("bye", Fishes.get(j));
                  }
                }
              }
            }
            break;
          case 3:
            throw new Exception();
            //break;
        } //switch
      } //try
      catch(Exception e) {
          system.terminate();
          break;
        }
    } //while
    System.out.println("END");
  }
}