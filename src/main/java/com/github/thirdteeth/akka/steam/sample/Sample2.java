package com.github.thirdteeth.akka.steam.sample;


import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.stream.javadsl.Source;

import java.util.ArrayList;
import java.util.List;

public class Sample2 {

    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create();
        final Materializer materializer = ActorMaterializer.create(system);
//        Source.range(0, 100).map(Object::toString)
//                .runForeach(str -> System.out.println(str), materializer);

        List<String> stringList = new ArrayList<String>();
        stringList.add("aa");
        stringList.add("ab");
        stringList.add("bc");
        stringList.add("bd");
        Source.from(stringList).filter(s -> s.startsWith("a")).runForeach(s -> stringList.remove(s), materializer);
        System.out.println(stringList);
        system.terminate();
    }



    public void test1() {
        List<String> stringList = new ArrayList<String>();
        stringList.add("aa");
        stringList.add("ab");
        stringList.add("bc");
        stringList.add("bd");
        stringList.stream().filter(s -> s.startsWith("a")).forEach(s -> stringList.remove(s));
        System.out.println(stringList);
    }
}
