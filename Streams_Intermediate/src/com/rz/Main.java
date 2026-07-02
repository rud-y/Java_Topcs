package com.rz;

import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
                .map(Character::toUpperCase)
                .distinct()
//                .dropWhile(i -> Character.toUpperCase(i) <= 'J')
//                .takeWhile(i -> i < 'a')
//                .skip(5)
//                .filter(i -> Character.toUpperCase(i) > 'D')
                .forEach(d -> System.out.printf("%c ", d));


        System.out.println("\n- - - ");
        Random random = new Random();

        // Project structure - Project SDK version compatibility
//        Stream.generate(() -> random.nextInt((int)'A', (int)'Z' + 1))
//                .limit(50)
//                .distinct()
//                .sorted()
//                .forEach(d -> System.out.printf("%c ", d));

        int min = 10;
        int max = 50;
        int randomNum = random.nextInt(max - min) + min;

        System.out.println(randomNum);

        System.out.println();
        int maxSeats = 100;
        int seatsInRow = 10;
        var stream = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(i -> new Seat((char) ('A' + i / seatsInRow),
                        i % seatsInRow + 1))
//                .sorted(Comparator.comparing(Seat::toString));
//                .thenComparing(Seat::price));
                .mapToDouble(Seat::price)
                .boxed()
                .map(val -> "%.2f".formatted(val));
        stream.forEach(System.out::println);

    }
}
