package generics;

import static org.fest.assertions.Assertions.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class BoxTest {

	@Test
	public void generalTestHarness() throws Exception {
		Box<Integer> intBox = new Box<>();
		intBox.set(4);
		assertThat(intBox.get()).isEqualTo(4);
		
		Box<String> strBox = new Box<>();
		strBox.set("hello");
		assertThat(strBox.get()).isEqualTo("hello");
		
		// raw types
		Box objBox = new Box();
		Box<Integer> int2Box = objBox;
		int2Box.get();
		Object obj = new Object();
		objBox.set(obj);
		assertThat(objBox.get()).isEqualTo(obj);
		
	}
	
	@Test
	public void boundedTypeParameters() throws Exception {
		Box<Integer> intBox = new Box<>();
		intBox.set(new Integer(10));
		intBox.inspect(new Float(10.4));
	}
	
	
	private void foo(Box<? extends Number> n){}
}
