package generics;

import java.util.ArrayList;
import java.util.List;

public class BoxDemo {

	public static <U> void addBox(U u, List<Box<U>> boxes){
		Box<U> box = new Box<>();
		box.set(u);
		boxes.add(box);
	}
	
	public static <U> void outputBoxes(List<Box<U>> boxes){
		int count = 0;
		for(Box<U> box : boxes){
			U boxContents = box.get();
			System.out.println("Box #"+count+" contains ["+boxContents.toString()+"]");
			count++;
		}
	}
	
	public static void main(String[] args){
		ArrayList<Box<Integer>> intBoxes = new ArrayList<>();
		BoxDemo.<Integer>addBox(Integer.valueOf(10), intBoxes);
		BoxDemo.addBox(Integer.valueOf(20), intBoxes);
		BoxDemo.<Integer>addBox(Integer.valueOf(30), intBoxes);
		BoxDemo.outputBoxes(intBoxes);
	}
}
