package concurrency.blockingqueue;

import java.util.concurrent.BlockingQueue;

public class Veterinarian extends Thread {

	protected final BlockingQueue<Appointment<Pet>> appts;
	protected String text = "";
	protected final int restTime;
	private boolean shutdown = false;
	private final String name;
	
	public Veterinarian(BlockingQueue<Appointment<Pet>> lbq, int pause, String name){
		appts = lbq;
		restTime = pause;
		this.name = name;
	}
	
	public synchronized void shutdown() {
		shutdown = true;
	}
	
	@Override
	public void run(){
		while(!shutdown){
			seePatient();
			try{
				Thread.sleep(restTime);
			} catch (InterruptedException e){
				shutdown = true;
			}
		}
	}
	
	public void seePatient(){
		try{
			Appointment<Pet> ap = appts.take();
			Pet patient = ap.getPatient();
			patient.examine(name);
		} catch(InterruptedException e){
			shutdown = true;
		}
	}
}
