package com.github.thirdteeth.akka.steam.sample;

//import akka.NotUsed;
//import akka.actor.ActorSystem;
//import akka.stream.ActorMaterializer;
//import akka.stream.Materializer;
//import akka.stream.alpakka.elasticsearch.ElasticsearchSinkSettings;
//import akka.stream.alpakka.elasticsearch.ElasticsearchSourceSettings;
//import akka.stream.alpakka.elasticsearch.IncomingMessage;
//import akka.stream.alpakka.elasticsearch.javadsl.*;
//import akka.stream.alpakka.elasticsearch.ElasticsearchFlowStage;
//import akka.stream.javadsl.Sink;
//import akka.stream.javadsl.Source;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.RestClient;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;

public class Sample5 {
//    public static class TestDoc {
//        public String id;
//        public String a;
//        public String b;
//        public String c;
//        // #custom-search-params
//        public TestDoc() {}
//
//        public TestDoc(String id, String a, String b, String c) {
//            this.id = id;
//            this.a = a;
//            this.b = b;
//            this.c = c;
//        }
//        // #custom-search-params
//    }
//    public static void main(String[] args) throws Exception {
//        String indexName = "test_using_search_params_versions_java";
//        String typeName = "doc";
//
//        final ActorSystem system = ActorSystem.create();
//        final Materializer materializer = ActorMaterializer.create(system);
//
//        RestClient client = RestClient.builder(new HttpHost("localhost", 9200)).build();
//
//        List<TestDoc> docs =
//                Arrays.asList(
//                        new TestDoc("1", "a1", "b1", "c1"),
//                        new TestDoc("2", "a2", "b2", "c2"),
//                        new TestDoc("3", "a3", "b3", "c3"));
//
//        // Insert document
//        Source.from(docs)
//                .map((TestDoc d) -> IncomingMessage.create(d.id, d))
//                .via(
//                        ElasticsearchFlow.create(
//                                indexName,
//                                typeName,
//                                ElasticsearchSinkSettings.Default().withBufferSize(5).withMaxRetry(0),
//                                client,
//                                new ObjectMapper()))
//                .map(results -> {
//                    results.stream().forEach(result -> {
//                        if(result.success()) {
//                            System.out.println(result.success());
//                        } else {
//                            System.out.println(result.errorMsg().get());
//                        }
//                    });
//                    return NotUsed.getInstance();
//                })
//                .runWith(Sink.seq(), materializer)
//                .toCompletableFuture()
//                .get();
//
//        flush(client, indexName);
//        // #custom-search-params
//        // Search for docs and ask elastic to only return some fields
//
//        Map<String, String> searchParams = new HashMap<>();
//        searchParams.put("query", "{\"match_all\": {}}");
////        searchParams.put("_source", "[\"id\", \"a\", \"c\"]");
//
//        List<TestDoc> result =
//                ElasticsearchSource.<TestDoc>typed(
//                        indexName,
//                        typeName,
//                        searchParams, // <-- Using searchParams
//                        ElasticsearchSourceSettings.Default(),
//                        client,
//                        TestDoc.class,
//                        new ObjectMapper())
//                        .map(
//                                o -> {
//                                    return o.source(); // These documents will only have property id, a and c (not b)
//                                })
//                        .runWith(Sink.seq(), materializer)
//                        .toCompletableFuture()
//                        .get();
//        flush(client, indexName);
//        System.out.println(result);
//        // #custom-search-param
//
//    }
//
//    private static void flush(RestClient client, String indexName) throws IOException {
//        client.performRequest("POST", indexName + "/_flush");
//    }
}
