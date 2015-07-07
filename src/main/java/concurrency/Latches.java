package concurrency;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class Latches {
	public static final int MAX_THREADS = 8;
	
	public static void main(String[] args){
		final int quorum = 1 + (int)(MAX_THREADS / 2);
		final CountDownLatch cdl = new CountDownLatch(quorum);
		
		final Set<ProcessingThread> nodes = new HashSet<>();
		try{
			for(int i=0; i<MAX_THREADS; i++){
				ProcessingThread local = new ProcessingThread("localhost:"+(9000+i), cdl);
				nodes.add(local);
				local.start();
			}
			cdl.await();
		} catch(InterruptedException e){
		} finally {
		}
	}
}
class ProcessingThread extends Thread{
	private final String ident;
	private final CountDownLatch latch;

	public ProcessingThread(String ident, CountDownLatch latch){
		this.ident = ident;
		this.latch = latch;
	}
	
	public String getIdentifier(){
		return ident;
	}
	
	public void initialize(){
		latch.countDown();
	}
	
	@Override
	public void run(){
		System.out.println(ident+ " initializing");
		initialize();
	}
}
