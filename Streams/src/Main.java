import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class Main {
    static void main() {
        String[] strings = {"One", "Two", "Three", "Four"};
        var firstStream = Arrays.stream(strings)
                .sorted(Comparator.reverseOrder());
//                .forEach(System.out::println);

        var secondStream = Stream.of("Nine", "Eight", "Seven", "Six")
                .map(String::toUpperCase);
//                .forEach(System.out::println);


        Stream.concat(secondStream, firstStream)
                .map(s -> s.charAt(0) + " - " + s)
                .forEach(System.out::println);



    }
}
