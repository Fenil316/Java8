package defaultandstatic;

public interface Person {
	
	String name = "Person";
	
	default void sayHello() {
		System.out.println("Hello");
	}
	
	static void greetings(){
		System.out.println("Greetings!!!");
	}
}
