package methodreference;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class MethodReferenceExamples {
	public static void main(String[] args) {
		List<Person> list = Person.createPersons();
		
		System.out.println("***********Print Person's money using static method reference");
		list.forEach(MethodReferenceExamples::printPersonMoney);
		
		System.out.println("************Print new Person using static method reference");
		Supplier<Person> supplier = MethodReferenceExamples::createPerson;
		Person p = supplier.get();
		System.out.println(p);
		
		System.out.println("************Print Persons using instance method reference");
		MethodReferenceExamples ex1 = new MethodReferenceExamples();
		list.forEach(System.out::println);
		
		System.out.println("************Print Persons using instance method reference");
		Supplier<Person> supplier2 = ex1::instanceCreatePerson;
		Person p2 = supplier2.get();
		System.out.println(p2);
		
		System.out.println("************Create new locale object using constructor reference");
		BiFunction<String, String, Locale> bif = Locale::new;
		System.out.println(bif.apply("en", "US"));
		
		System.out.println("************Sort Integers using constructor reference");
		List<Integer> numbers = Arrays.asList(10,20,2,3,5,-1,19);
		numbers.sort(Integer::compareTo);
		numbers.forEach(System.out::println);
	}
	
	// Static method Consumer Method Reference
	public static void printPersonMoney(Person p) {
		System.out.println(p.money);
	}
	
	// Static method Supplier Method Reference
	public static Person createPerson() {
		return new Person("Ashish", 300.0, "Hybrid");
	}
	
	public void instancePrintPerson(Person p) {
		System.out.println(p);
	}
	
	public Person instanceCreatePerson() {
		return new Person("Shipra", 400.0, "Ford");
	}
}
