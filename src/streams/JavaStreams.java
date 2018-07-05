package streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// You have to create FunctionalInterface here since Supplier in it's get method definition does not throw any exception
// and while using it with Files.lines() method that throws an IOException, you gotto create a custom Supplier interface
// that throws an exception.
@FunctionalInterface
interface Supplier_WithException <T, E extends Exception> {
	T get() throws E;
}

public class JavaStreams {
	public static void main(String[] args) throws IOException {
		
		String abc = new String("abc");
		String xyz = new String("abc");
		System.out.println("abc hashcode " + abc.hashCode());
		System.out.println("xyz hashcode " + xyz.hashCode());
		System.out.println("is abc equals to xyz: " + (abc == xyz));
		
		System.out.println("****************1) Print 1 to 9");
		IntStream.range(1, 10).forEach(System.out::print);
		System.out.println();
		
		System.out.println("****************2) Print 1 to 10");
		IntStream.rangeClosed(1, 10).forEach(x->System.out.print(x + " "));
		System.out.println();
		
		System.out.println("****************3) Print 5 to 10");
		IntStream.range(1, 10).skip(4).forEach(System.out::print);
		System.out.println();
		
		System.out.println("****************4) Print sum of first 10 numbers");
		System.out.println("Sum: " + IntStream.rangeClosed(1, 10).sum());
		
		System.out.println("****************5) Sort and print strings in desc order");
		System.out.println(Stream.of("Fenil", "Amit", "Kashyap").sorted((x,y) -> y.compareTo(x)).collect(Collectors.toList()));
		
		System.out.println("****************6) Sort and print first");
		Stream.of("Fenil", "Stavan", "Kashyap").sorted().findFirst().ifPresent(System.out::println);
		
		System.out.println("****************7) Stream from Array, change value, filter, sort and print");
		String[] name = {"Praveen", "Swati", "Gopi", "Shipra", "Ashish"};
		Arrays.stream(name).map(x -> x + "_checked").filter(x -> x.startsWith("S")).sorted().forEach(System.out::println);
		
		System.out.println("****************8) Average of squares of an int array");
		int[] numbers = {1,2,3,4,5,6};
		Arrays.stream(numbers).map(x -> x*x).average().ifPresent(System.out::println);
		
		System.out.println("9) Stream from List, filter and print");
		List<String> list = Arrays.asList("Al", "Ankit", "Brent",  "Sarika", "Amanda", "Hans", "Shivi");
		list.stream().filter(x -> x.length() > 3).forEach(System.out::println);
		
		System.out.println("****************10) Parallel Stream rows from text file, sort, filter and print using forEachORDERED");
		Stream<String> bands = Files.lines(Paths.get("bands.txt"));
		bands.parallel().filter(x -> x.split(" ").length > 1).sorted().forEachOrdered(System.out::println);
		
		System.out.println("****************11) Stream rows from text file, sort, filter and count using Supplier");
		// Supplier is used to reuse stream since stream once consumed cannot be reused and throws IllegalStateException.
		// Supplier<Stream<String>> streamSupplier = () -> Files.lines(Paths.get("bands.txt"));
		// Also using my custom supplier due to reasons specified above.
		Supplier_WithException<Stream<String>, IOException> streamSupplier = () -> Files.lines(Paths.get("bands.txt"));
		System.out.println("Total Rows: " + streamSupplier.get().parallel().filter(x -> x.split(" ").length > 1).sorted().count());
		streamSupplier.get().map(x -> x.split(" ")).filter(x -> x.length > 1).forEach(x -> System.out.println(Arrays.asList(x)));
		
		System.out.println("****************12) Stream to be stored in HashMap");
		Map<String, String> map = streamSupplier.get().map(x -> x.split(" ")).filter(x -> x.length > 1)
				.collect(Collectors.toMap(x -> x[0], x-> x[1]));
		System.out.println(map);
		
		System.out.println("****************13) Reduction: sum");
		double total = Stream.of(7.3, 1.5, 4.8).reduce(0.0, (Double a, Double b) -> a + b);
		System.out.println("Sum: " + total);
		
		System.out.println("****************14) Reduction: summary statistics *ONLY WORKS FOR INTEGERS*");
		IntSummaryStatistics summary = IntStream.of(7,2,19,88,73,4,10).summaryStatistics();
		System.out.println(summary);
		
	}
}
