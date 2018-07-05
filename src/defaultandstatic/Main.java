package defaultandstatic;

public class Main {
	public static void main(String[] args) {
		Person p = new Student();
		Student s = new Student();
		p.sayHello();
		Person.greetings();
		s.greetings();
	}
}
