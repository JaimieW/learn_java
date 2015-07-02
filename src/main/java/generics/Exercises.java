package generics;

import java.util.Arrays;
import java.util.Collection;

public class Exercises {
	
	public static void main(String[] args){
		Exercises e = new Exercises();
		OddPredicate p = new OddPredicate();
		Collection<Integer> c = Arrays.asList(2, 4, 6 ,7 ,4, 3, 9, 5);
		System.out.printf("There are %d odd numbers", e.count(c, p));
	}

	public <T> int count(Collection<T> c, UnaryPredicate<T> p){
		int count = 0;
		for(T t : c){
			if(p.test(t)) count++;
		}
		return count;
	}
}

interface UnaryPredicate <T>{
	boolean test(T t);
}

class OddPredicate implements UnaryPredicate<Integer>{

	@Override
	public boolean test(Integer t) {
		// TODO Auto-generated method stub
		return t % 2 != 0;
	}
	
}
