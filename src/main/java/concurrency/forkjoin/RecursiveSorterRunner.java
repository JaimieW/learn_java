package concurrency.forkjoin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class RecursiveSorterRunner {

	public static void main(String[] args) {
		List<Update> lu = new ArrayList<>();
		StringBuilder text = new StringBuilder("");
		final Update.Builder ub = new Update.Builder();
		final Author a = new Author("Tallulah");
		
		for (int i=0; i<256; i++){
			text.append("X");
			long now = System.currentTimeMillis();
			lu.add(ub.author(a).updateText(text.toString()).createTime(now).build());
			try{
				Thread.sleep(1);
			} catch (InterruptedException e) {}
		}
		
		Collections.shuffle(lu);
		
		Update[] updates = lu.toArray(new Update[0]);
		
		MicroBlogUpdateSorter sorter = new MicroBlogUpdateSorter(updates);
		ForkJoinPool pool = new ForkJoinPool(8);
		
		long start = System.nanoTime();
		pool.invoke(sorter);
		long time = System.nanoTime() - start;
		
		for(Update u : sorter.getResult()){
			System.out.println(u);
		}
		
		System.out.println("recursive merge sort took "+time/1000000L+"ms");
	}

}
