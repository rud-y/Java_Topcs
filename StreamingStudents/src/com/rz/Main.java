package com.rz;

import java.util.stream.Stream;

public class Main {
  public static void main(String[] args) {

    Course c1 = new Course("JS", "Welcome to JavaScript");
    System.out.println("c1 course : " + c1.toString());

    Course pymc = new Course("PYMC", "Python Masterclass");
    Course jmc = new Course("JMC", "jAVA Masterclass");
//    Student john = new Student("AU", 2022, 40, "M", true, pymc, jmc);

//    System.out.println("john: " + john);
//
//    john.watchLecture("JMC", 10, 5, 2024);
//    john.watchLecture("PYMC", 9, 6, 2021);
//    System.out.println(john);

    Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
            .limit(10)
            .forEach(System.out::println);
  }
}
