package concurrency;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class Timeline {

	private final CopyOnWriteArrayList<Update> updates;
	private final ReentrantLock lock;
	private final String name;
	private Iterator<Update> it;
	
	public Timeline(String name, CopyOnWriteArrayList<Update> list, ReentrantLock lock){
		this.name = name;
		this.updates = list;
		this.lock = lock;
	}
	
	public void addUpdate(Update update){
		updates.add(update);
	}
	
	public void prep(){
		it = updates.iterator();
	}
	
	public void printTimeline(){
		lock.lock();
		try{
			if(it != null){
				System.out.print(name+ ": ");
				while(it.hasNext()){
					Update s = it.next();
					System.out.print(s.getText()+", ");
				}
				System.out.println();
			}
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String [] args){
		final CountDownLatch firstLatch = new CountDownLatch(1);
		final CountDownLatch secondLatch = new CountDownLatch(1);
		final CopyOnWriteArrayList<Update> updates = new CopyOnWriteArrayList<>();
		updates.add(new Update("Ben: I like pie"));
		updates.add(new Update("Charles: I like ham on rye"));
		
		ReentrantLock lock = new ReentrantLock();
		final Timeline tl1 = new Timeline("TL1", updates, lock);
		final Timeline tl2 = new Timeline("TL2", updates, lock);
		
		Thread t1 = new Thread(){
			public void run(){
				updates.add(new Update("Jeffrey: I like a lot of things"));
				tl1.prep();
				firstLatch.countDown();
				try{ secondLatch.await(); } catch(InterruptedException e){}
				tl1.printTimeline();
			}
		};
		Thread t2 = new Thread(){
			public void run(){
				try{
					firstLatch.await();
					updates.add(new Update("Gavin: I like otters"));
					tl2.prep();
					secondLatch.countDown();
				}
				catch(InterruptedException e){}
				tl2.printTimeline();
			}
		};
		t1.start();
		t2.start();
	}
}
