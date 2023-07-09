package com.learnreactiveprogramming.service;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class FluxAndMonoGeneratorService {

    public static void main(String[] args) {
        FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();
        fluxAndMonoGeneratorService.monoFlatMapExample().subscribe();
    }

    Function<Flux<String>, Flux<String>> transform = name -> name.map(String::toUpperCase);

    public Flux<String> fluxFlatMapExample() {
        return Flux.fromIterable(List.of("manan", "ramesh"))
                .transform(transform)
//                .map(String::toUpperCase)
                .concatMap(name -> splitName(name)).log();
    }

    private Flux<String> splitName(String name) {
        return Flux.fromArray(name.split(""))
                .delayElements(Duration.ofMillis(1000));
    }

    public Mono<String> monoFlatMapExample() {
        return Mono.just("manan")
                .map(String::toUpperCase)
                .flatMap(this::splitNameMono).log();
    }

    private Mono<String> splitNameMono(String s) {
        return Mono.just(s.concat("Agg"));
    }

    public Flux<String> handlesEmptyMono(){
       return Flux.just("manan")
               .transform(transform)
               .flatMap(this::checkSize)
               .switchIfEmpty(Flux.just("empty"))
               .log();

    }

    private Flux checkSize(String s) {
        return Flux.empty();
    }

    public Flux<String> exploreZip(){
        return Flux.zip(Flux.just("Manan "), Flux.just("Agg"), (a,b) -> a + b)
                .log();
    }

}
