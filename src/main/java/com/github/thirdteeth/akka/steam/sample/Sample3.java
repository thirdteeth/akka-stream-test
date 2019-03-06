package com.github.thirdteeth.akka.steam.sample;

//import akka.NotUsed;
//import akka.actor.ActorSystem;
//import akka.http.javadsl.ConnectHttp;
//import akka.http.javadsl.Http;
//import akka.http.javadsl.ServerBinding;
//import akka.http.javadsl.model.*;
//import akka.http.javadsl.server.Route;
//import akka.stream.ActorMaterializer;
//import akka.stream.Materializer;
//import akka.japi.Pair;
//import static akka.http.javadsl.server.Directives.*;
//
//import akka.stream.javadsl.Source;
//import akka.util.ByteString;
//
//import java.net.InetSocketAddress;
//import java.util.Optional;
//import java.util.concurrent.CompletionStage;

public class Sample3 {

//    public static void main(String[] args) {
//        final ActorSystem system = ActorSystem.create();
//        final Materializer materializer = ActorMaterializer.create(system);
//        final ConnectHttp host = ConnectHttp.toHost("127.0.0.1", 9000);
//        final Http http = Http.get(system);
//
//       final Source<ByteString, NotUsed> numbers = Source.unfold(0L, n -> {
//           long next = n+1;
//           return Optional.of(Pair.create(next, next));
//       }).map(n -> ByteString.fromString(n.toString()+ "\n"));
//
//       final Route route =
//               path("numbers", () ->
//                    get(() ->
//                            complete(HttpResponse.create()
//                                    .withStatus(StatusCodes.OK)
//                                    .withEntity(HttpEntities.create(ContentTypes.TEXT_PLAIN_UTF8, numbers))
//                            )));
//        final CompletionStage<ServerBinding> bindingCompletionStage =
//                http.bindAndHandle(route.flow(system, materializer), host, materializer);
//
//        bindingCompletionStage.thenAccept((binding) -> {
//            final InetSocketAddress address = binding.localAddress();
//            System.out.println("Akka HTTP server running at " + address.getHostString() + ":" + address.getPort());
//        }).exceptionally((ex) -> {
//            System.out.print("Failed to bind HTTP server: " + ex.getMessage());
//            ex.fillInStackTrace();
//            return null;
//        });
//    }
}
