package spotxchange;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TasksTest {

	
	Tasks t;
	
	@Before
	public void setup(){
		t = new Tasks();
	}
	
	@After
	public void teardown(){
		t = null;
	}
	@Ignore
	@Test
	public void simpleCase(){
//		                      start 	1		2		3		4		5		6		7		8 		9		10		11		12
		int[] tc0 = new int[]{-1,		-1, 	3, 		4, 		5};	
		int[] tc1 = new int[]{1,		-1, 	3, 		4, 		5};
		int[] tc2 = new int[]{1,		4,	 	3, 		-1,		2};
		int[] tc3 = new int[]{1,		4,	 	3, 		8,		12,		-1,		-1,		-1,		10,		-1,		6,		-1,		2};
		
		int[] tc4 = new int[]{1,		4, 		-1, 	3, 		2};	

		
		assertThat(t.solution(tc0)).isEqualTo(1);
		assertThat(t.solution(tc1)).isEqualTo(2);
		assertThat(t.solution(tc2)).isEqualTo(5);
		assertThat(t.solution(tc3)).isEqualTo(9);
		assertThat(t.solution(tc4)).isEqualTo(4);
//		assertThat(t.solution(new int[]{-1})).isEqualTo(1);
//		assertThat(t.solution(new int[]{-1})).isEqualTo(1);
	}
	
	
	@Ignore
	@Test
	public void testTask2(){
//        						start 	1		2		3		4		5		6		7		8 		9		10		11		12
		int[] tc0 = new int[]{	1,		2, 		3, 		3, 		1, 		3, 		1};
		int[] tc1 = new int[]{	1,		0, 		0, 		3, 		0, 		1, 		1};
		
		int[]tc2 = new int[]{2};
		int[]tc3 = new int[]{0,1,0};
		int[]tc4 = new int[]{0,1,2,3,4,5};
		
		System.out.println("val from func: "+t.solution(3, tc0));
		System.out.println("val from func: "+t.solution(3, tc1));
		System.out.println("val from func: "+t.solution(3, tc2));
		System.out.println("val from func: "+t.solution(1, tc3));
		System.out.println("val from func: "+t.solution(5, tc4));
		
	}
	
	
	@Test
	public void testTask3(){
		System.out.println("o + odd: "+t.solution("o", "odd"));
		System.out.println("==============================================");
		System.out.println("nice + niece: "+t.solution("nice", "niece"));
		System.out.println("nice + nicet: "+t.solution("nice", "nicet"));
		System.out.println("ice + nice: "+t.solution("ice", "nice"));
		
		System.out.println("==============================================");

		System.out.println("nict + niece: "+t.solution("nict", "niece"));
		System.out.println("niect + nice:" +t.solution("niect", "nice"));
		System.out.println("==============================================");
		System.out.println("niece + nice:" +t.solution("niece", "nice"));
		System.out.println("niece + niec:" +t.solution("niece", "niec"));
		System.out.println("nice + ice:" +t.solution("nice", "ice"));

		System.out.println("==============================================");
		System.out.println("from + from:" +t.solution("from", "from"));
		System.out.println("==============================================");

		System.out.println("from + form:" +t.solution("from", "form"));

		System.out.println("form + from:" +t.solution("form", "from"));
	}
	
}
