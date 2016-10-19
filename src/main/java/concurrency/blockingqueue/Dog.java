package concurrency.blockingqueue;

public class Dog extends Pet {

	public Dog(String name) {
		super(name);
	}

	@Override
	public void examine(String examiner) {
		System.out.println(examiner +" examines " +name+" says woof");
	}

}
