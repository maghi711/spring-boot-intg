package com.lambdas;

import com.lambdas.model.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Filtering {
    public static void main(String[] args) {
        System.out.println("Hello");
        List<Apple> apples = populateAppleList();
        Predicate<Apple> conditionOne = a -> a.getWeight() > 20;
        Predicate<Apple> conditionTwo = a -> a.getColor().startsWith("y");
        Predicate<Apple> finalCondition = conditionOne.and(conditionTwo);
        List<Apple> sortedByWeightApples = filterApplesByWeight(apples, finalCondition);
        sortedByWeightApples.forEach(System.out::println);
    }

    private static List<Apple> filterApplesByWeight(List<Apple> apples, Predicate<Apple> predicate) {
        return apples.stream().filter(predicate).collect(Collectors.toList());
    }

    private static List<Apple> populateAppleList() {
        List<Apple> apples = Arrays.asList(
                new Apple("red", 10),
                new Apple("green", 30),
                new Apple("blue", 20),
                new Apple("yellow", 40)
        );
        return apples;
    }
}
