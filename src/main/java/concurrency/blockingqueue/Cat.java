package concurrency.blockingqueue;

public class Cat extends Pet {

	public Cat(String name) {
		super(name);
	}

	@Override
	public void examine(String examiner) {
		System.out.println(examiner +" examines " +name+" says meow");
	}

}
