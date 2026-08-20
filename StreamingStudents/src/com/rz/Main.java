package com.rz;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Main {
  public static void main(String[] args) {

    Course c1 = new Course("JS", "Welcome to JavaScript");
    System.out.println("c1 course : " + c1.toString());

    Course pymc = new Course("PYMC", "Python Masterclass");
    Course jmc = new Course("JMC", "jAVA Masterclass");

    //---s
//    Student john = new Student("AU", 2022, 40, "M", true, pymc, jmc);
//
//    System.out.println("john: " + john);
//
//    john.watchLecture("JMC", 10, 5, 2024);
//    john.watchLecture("PYMC", 9, 6, 2021);
//    System.out.println(john);
//
//    Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
//            .limit(10)
//            .forEach(System.out::println);

    //---f

    Student[] students = new Student[800];
    Arrays.setAll(students, i -> Student.getRandomStudent(jmc, pymc));

    var maleStudents = Arrays.stream(students)
            .filter(s -> s.getGender().equals("M"));
    System.out.println("# of male students: " + maleStudents.count());

    for (String gender: List.of("M", "F", "U")) {
      var myStudents = Arrays.stream(students)
              .filter(s -> s.getGender().equals((gender)));
      System.out.println("# Gender -" + gender + "- student number is --> " + myStudents.count());
    }

    List<Predicate<Student>> list = List.of(
            (s) -> s.getAge() < 30,
            (Student s) -> s.getAge() >= 30 && s.getAge() < 60
    );

    long total = 0;
    for (int i = 0; i < list.size(); i++) {
      var myStudents = Arrays.stream(students).filter(list.get(i));
      long cnt = myStudents.count();
      total += cnt;
      System.out.printf(" # of students (%s) = %d%n",
              i == 0 ? " < 30" : ">= 30 & < 60 ", cnt);
    }
      System.out.println("# of students >= 60 = " + (students.length - total));


    //
    var ageStream = Arrays.stream(students)
            .mapToInt(Student::getAgeEnrolled);
    System.out.println("Age summary stats: age: " + ageStream.summaryStatistics());

    var currentAgeStream = Arrays.stream(students)
            .mapToInt(Student::getAge);
    System.out.println("Current age summary stats: age: " + currentAgeStream.summaryStatistics());

    Arrays.stream(students)
            .map(Student::getCountryCode)
            .distinct()
            .sorted()
            .forEach(s -> System.out.printf(s + ", "));

    System.out.println();
    // If any long term student exist in the list
    boolean longTermStudents = Arrays.stream(students)
            .anyMatch(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&
                    (s.getMonthsSinceActive() <= 12));
    System.out.println("Longterm students: " + longTermStudents);

    // Longterm student count
    long longTermStudentsCount = Arrays.stream(students)
            .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&
                    (s.getMonthsSinceActive() < 12))
            .count();
    System.out.println("Longterm students count: " + longTermStudentsCount);


  }
}
