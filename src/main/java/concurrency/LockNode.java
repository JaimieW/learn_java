package concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockNode implements SimpleNode {

	private final String identifier;
	
	private final Lock lock = new ReentrantLock();
	
	public LockNode(String identifier){
		this.identifier = identifier;
	}
	
	@Override
	public String getIdentifier() {
		return identifier;
	}

	// DEADLOCK
	/*
	@Override
	public void propagateUpdate(Update update, SimpleNode backup) {
		lock.lock();
		try{
			System.out.println(identifier + ": recvd: "+ update.getText()+ " ; backup: "+backup.getIdentifier());
			backup.confirmUpdateReceived(this, update);
		} finally {
			lock.unlock();
		}
	}
	*/
	
	// STILL POTENTIAL FOR DEADLOCK
	
	@Override
	public void propagateUpdate(Update update, SimpleNode backup) {
		boolean acquired = false;
		
		while(!acquired){
			try{
				int wait = (int)Math.random() * 10;
				acquired = lock.tryLock(wait, TimeUnit.MILLISECONDS);
				if(acquired){
					System.out.println(identifier + ": recvd: "+update.getText() + " ; backup: "+backup.getIdentifier());
					sleep();
					backup.confirmUpdateReceived(this, update);
				} else {
					Thread.sleep(wait);
				}
			} catch (InterruptedException e) {// no op // 
			} finally{ if(acquired) lock.unlock(); }
		}
	}
	
	private void sleep() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Override
//	public void propagateUpdate(Update update, SimpleNode backup) {
//		boolean acquired = false;
//		boolean done = false;
//		
//		while(!done){
//			int wait = (int)Math.random() * 10;
//			try{
//				acquired = lock.tryLock(wait, TimeUnit.MILLISECONDS);
//				if(acquired){
//					System.out.println(identifier + ": recvd: "+update.getText() + " ; backup: "+backup.getIdentifier());
//					done = backup.confirmUpdateReceived(this, update);
//				}
//			} catch (InterruptedException e) {// no op // 
//			} finally{ if(acquired) lock.unlock(); }
//			if(!done) try {
//				Thread.sleep(wait);
//			} catch(InterruptedException e) {}
// 		}
//	}
//	
//	public boolean confirmUpdateReceived(SimpleNode node, Update update) {
//		long startTime = System.currentTimeMillis();
//		boolean acquired = false;
//		
//		try{
//			int wait = (int)Math.random() * 10;
//			acquired = lock.tryLock(wait, TimeUnit.MILLISECONDS);
//			if(acquired){
//				long elapsed = System.currentTimeMillis() - startTime;
//				System.out.println(identifier + ": recvd confirm: "+
//									update.getText() + " ; from: " + node.getIdentifier() + 
//									" - took "+elapsed+" ms");
//				return true;
//			}
//		} catch(InterruptedException e ){// no op
//		} finally{
//			if(acquired) lock.unlock();
//		}
//		return false;
//	}
	
	@Override
	public boolean confirmUpdateReceived(Update update) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean confirmUpdateReceived(SimpleNode node, Update update) {
		lock.lock();
		try{
			System.out.println(identifier + ": recvd confirm: "+update.getText() + " ; from: " + node.getIdentifier());
			
		} finally {
			lock.unlock();
		}
return false;
	}

}
