package generics;

import static org.fest.assertions.Assertions.*;
import org.junit.Test;

public class UtilTest {
	@Test
	public void generalTestHarness() throws Exception {
		Pair<Integer, String> p1 = new Pair<>(1, "apple");
		Pair<Integer, String> p2 = new Pair<>(2, "A");
		boolean same = Util.<Integer, String>compare(p1, p2);
		assertThat(same).isFalse();
	}
}
