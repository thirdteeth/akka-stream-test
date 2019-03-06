package com.github.thirdteeth.test;

import akka.Done;
import akka.actor.ActorSystem;
import akka.kafka.ProducerSettings;
import akka.kafka.javadsl.Consumer;
import akka.kafka.javadsl.Producer;
import akka.kafka.testkit.javadsl.EmbeddedKafkaTest;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.stream.javadsl.Source;
import akka.testkit.javadsl.TestKit;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProducerTests extends EmbeddedKafkaTest {

    private static final ActorSystem system = ActorSystem.create("ProducerExampleTest");
    private static final Materializer materializer = ActorMaterializer.create(system);
    // #testkit

    private final Executor executor = Executors.newSingleThreadExecutor();
    private final ProducerSettings<String, String> producerSettings = producerDefaults();

    protected ProducerTests() {
        super(system, materializer, 9042);
    }

    @AfterAll
    void shutdownActorSystem() {
        TestKit.shutdownActorSystem(system);
    }

    @Test
    void plainSink() throws Exception {
        String topic = createTopic(1, 1, 1);
        // #plainSink
        CompletionStage<Done> done =
                Source.range(1, 100)
                        .map(number -> number.toString())
                        .map(value -> new ProducerRecord<String, String>(topic, value))
                        .runWith(Producer.plainSink(producerSettings), materializer);
        // #plainSink

        Consumer.DrainingControl<List<ConsumerRecord<String, String>>> control =
                consumeString(topic, 100);
        assertEquals(Done.done(), resultOf(done));
        assertEquals(Done.done(), resultOf(control.isShutdown()));
        CompletionStage<List<ConsumerRecord<String, String>>> result =
                control.drainAndShutdown(executor);
        assertEquals(100, resultOf(result).size());
    }

}
