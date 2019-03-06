package com.github.thirdteeth.akka.steam.sample;

import akka.Done;
import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.stream.*;
import akka.stream.javadsl.*;
import akka.japi.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;

public class Sample1 {
    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create();
        final Materializer materializer = ActorMaterializer.create(system);
        List<Test> testList = new ArrayList<>();
        testList.add(new Test("a", "b"));
        testList.add(new Test("c", "d"));
        testList.add(new Test("e", "f"));
        testList.add(new Test("g", "h"));
        testList.add(new Test("i", "j"));
        testList.add(new Test("k", "l"));
        final Source<Test, NotUsed> source = Source.from(testList);

        final Graph<FanInShape2<String, String, String>, NotUsed> zip =
                ZipWith.create((String left, String right) -> left + right);

        final Flow<String, String, NotUsed> flow = Flow.of(String.class).map(str -> str.toUpperCase());


        Flow<Test, String, NotUsed> graphFlow = Flow.fromGraph(GraphDSL.create(flow, (builder, aflow) -> {
            final FanOutShape2<Test, String, String> unzip = builder.add(UnzipWith.create(test -> Pair.create(test.a, test.b)));

            final FanInShape2<String, String, String> zipShape = builder.add(zip);

            builder.from(unzip.out0()).via(aflow).toInlet(zipShape.in0());
            builder.from(unzip.out1()).toInlet(zipShape.in1());

            FlowShape<String, String> flowShape = builder.add(Flow.fromFunction((String str) -> {
                System.out.println(str);
                return str;
            }));
            builder.from(zipShape.out()).toInlet(flowShape.in());
            return FlowShape.of(unzip.in(), flowShape.out());
        }));
//        final Flow<Integer, String, NotUsed> flow = Flow.fromFunction((Integer n) -> n.toString());
        final Sink<String, CompletionStage<Done>> sink = Sink.foreach(str -> System.out.println(str));
        final RunnableGraph<NotUsed> runnableGraph = source.via(graphFlow).to(Sink.ignore());
        runnableGraph.run(materializer);
    }


    public static class Test{
        private String a;
        private String b;

        public Test(String a, String b) {
            this.a = a;
            this.b = b;
        }

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }
}
