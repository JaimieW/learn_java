package generics;

import java.util.List;

public class Util {
	public static <K, V> boolean compare(Pair<K,V> p1, Pair<K,V>p2){
		return p1.getKey().equals(p2.getKey()) &&
				p1.getValue().equals(p2.getValue());
	}
	
	public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem){
		int count = 0;
		for(T e : anArray){
			if(e.compareTo(elem) > 0){
				count++;
			}
		}
		return count;
	}
}

class Pair<K,V>{
	private K key;
	private V value;
	
	public Pair(K key, V value){
		this.key = key;
		this.value = value;
	}
	
	public void setKey(K key) { this.key = key;}
	public void setValue(V value){this.value = value;}
	public K getKey() {return key; }
	public V getValue(){return value;}
}

class A {}
interface B {}
interface C{}

class D <T extends A & B & C>{}


class WildcardError {
	 
    void foo(List<?> i) {
    	fooHelper(i);
    }
    
    private <T> void fooHelper(List<T> l){
    	l.set(0, l.get(0));
    }
}