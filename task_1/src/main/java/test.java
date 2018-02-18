import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class test {

    public static void main(String[] args) {

        Stream.of("java", "python", "node", null, "ruby", null, "php")
        .filter(Objects::nonNull).filter(x -> x.contains("1231")).findAny(); //forEach(System.out::println);

    }

}
