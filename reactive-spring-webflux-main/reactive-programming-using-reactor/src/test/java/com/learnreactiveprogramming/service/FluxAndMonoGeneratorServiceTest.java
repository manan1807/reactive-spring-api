package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoGeneratorServiceTest {
    FluxAndMonoGeneratorService fluxAndMonoGeneratorService;
    @BeforeEach
    void beforeEachTest(){
        fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();
    }

    @Test
    void monoFlatMapExample() {

//        when
        final Mono<String> stringMono = fluxAndMonoGeneratorService.monoFlatMapExample();
//    then
        StepVerifier.create(stringMono)
                .expectNext("MANANAgg")
                .verifyComplete();
    }

    @Test
    void fluxFlatMapExample() {

        // when
        final Flux<String> stringFlux = fluxAndMonoGeneratorService.fluxFlatMapExample();
        // then
        StepVerifier.create(stringFlux)
                .expectNext("M","A","N","A","N","R","A","M","E","S","H")
                .verifyComplete();


    }

    @Test
    void handlesEmptyMono() {

        final Flux<String> stringFlux = fluxAndMonoGeneratorService.handlesEmptyMono();
        StepVerifier.create(stringFlux)
                .expectNext("empty")
                .verifyComplete();
    }

    @Test
    void exploreZip() {
        final Flux<String> stringFlux = fluxAndMonoGeneratorService.exploreZip();
        StepVerifier.create(stringFlux)
                .expectNext("Manan Agg")
                .verifyComplete();

    }
}