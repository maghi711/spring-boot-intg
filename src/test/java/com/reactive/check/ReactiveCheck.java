package com.reactive.check;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

public class ReactiveCheck {

    public static void main(String[] args) {
        stepFiveCombineTwoStreams();
    }

    private static void stepFiveCombineTwoStreams() {
        List<String> integers = new ArrayList<>();
        Flux.just(1,2,3,4,5)
                .log()
                .map(i -> i * 2)
                .zipWith(Flux.range(0, Integer.MAX_VALUE), (one, two) -> String.format("First Flux: %d, Second Flux: %d", one, two))
                .subscribe(integers::add);
        //map() will be applied when onNext() is called.
        integers.forEach(System.out::println);
    }

    private static void stepFourMappingDataInAStream() {
        List<Integer> integers = new ArrayList<>();
        Flux.just(1,2,3,4,5)
                .log()
                .map(i -> i * 2)
                .subscribe(integers::add);
        //map() will be applied when onNext() is called.
        integers.forEach(System.out::println);
    }

    private static void stepThreeReactiveBackPressure() {
        List<Integer> integers = new ArrayList<>();
        Flux.just(1,2,3,4,5)
                .log()
                .subscribe(
                        new Subscriber<Integer>() {
                            Subscription s;
                            int next;
                            @Override
                            public void onSubscribe(Subscription subscription) {
                                this.s = subscription;
                                s.request(2);
                            }

                            @Override
                            public void onNext(Integer integer) {
                                integers.add(integer);
                                next++;
                                if (next % 2 == 0) {
                                    s.request(2);
                                    next = 0;
                                    System.out.println("next --> " + next);
                                }
                            }

                            @Override
                            public void onError(Throwable throwable) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );
    }

    private static void stepTwo() {
        List<Integer> integers = new ArrayList<>();
        Flux.just(1,2,3,4,5)
                .log()
                .subscribe(
                        new Subscriber<Integer>() {
                            @Override
                            public void onSubscribe(Subscription subscription) {
                                System.out.println("onSubscribe");
                                subscription.request(Long.MAX_VALUE);
                            }

                            @Override
                            public void onNext(Integer integer) {
                                System.out.println("onNext " + integer);
                                integers.add(integer);
                            }

                            @Override
                            public void onError(Throwable throwable) {
                            }

                            @Override
                            public void onComplete() {
                                System.out.println("onComplete");
                            }
                        }
                );
    }

    private static void stepOne() {
        List<Integer> integers = new ArrayList<>();
        Flux.just(1,2,3,4,5).log().subscribe(integers::add);
        Flux.just(1,2,3,4,5).subscribe(System.out::println);
        assertThat(integers).containsExactly(1,2,3,4,5);
    }

    static void generateElements() {
        // This streams 0..n elements
        Flux<Integer> just = Flux.just(1, 2, 3, 4);

        // This streams 0..1 elements
        Mono<Integer> one = Mono.just(1);
    }
}
