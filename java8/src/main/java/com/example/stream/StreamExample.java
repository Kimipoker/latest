package com.example.stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExample {
    public static void main(String[] args) {
        Stream<double[]> stream = IntStream.rangeClosed(1, 100).boxed().flatMap(
                a -> IntStream.rangeClosed(a, 100).mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)}).filter(t -> t[2] % 1 == 0)
        );
        stream.forEach(ds-> System.out.println(ds[0]+","+ds[1]+","+ds[2]));


    }
}
