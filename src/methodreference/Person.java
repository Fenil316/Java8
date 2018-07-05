package methodreference;

import java.util.ArrayList;
import java.util.List;

public class Person {
	String firstName;
	Double money;
	String car;
	
	public Person() {
		
	}
	
	public Person (String firstName, Double money, String car) {
		this.firstName = firstName;
		this.money = money;
		this.car = car;
	}
	
	public static List<Person> createPersons() {
		List<Person> list = new ArrayList<Person>();
		list.add(new Person("Fenil", 100.0, "Merc"));
		list.add(new Person("Amit", 200.0, "Rogue"));
		
		return list;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", money=" + money + ", car="
				+ car + "]";
	}
	
	
}
