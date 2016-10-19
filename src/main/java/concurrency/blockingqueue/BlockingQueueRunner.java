package concurrency.blockingqueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueRunner {

	public static void main (String [] args){
		Pet cat1 = new Cat("tiddlesA");
		Pet cat2 = new Cat("tiddlesB");
		Pet cat3 = new Cat("tiddlesC");
		Pet cat4 = new Cat("tiddlesD");
		Pet cat5 = new Cat("tiddlesE");
		Pet cat6 = new Cat("tiddlesF");
		Pet dog1 = new Dog("roverA");
		Pet dog2 = new Dog("roverB");
		Pet dog3 = new Dog("roverC");
		Pet dog4 = new Dog("roverD");
		Pet dog5 = new Dog("roverE");
		Pet dog6 = new Dog("roverF");
		Pet dog7 = new Dog("roverG");
		Pet dog8 = new Dog("roverH");
		Pet dog9 = new Dog("roverI");
		Pet dog10 = new Dog("roverJ");
		Appointment<Pet> ap1 = new Appointment<Pet>(cat1);
		Appointment<Pet> ap2 = new Appointment<Pet>(cat2);
		Appointment<Pet> ap3 = new Appointment<Pet>(cat3);
		Appointment<Pet> ap4 = new Appointment<Pet>(cat4);
		Appointment<Pet> ap5 = new Appointment<Pet>(cat5);
		Appointment<Pet> ap6 = new Appointment<Pet>(cat6);
		Appointment<Pet> ap7 = new Appointment<Pet>(dog1);
		Appointment<Pet> ap8 = new Appointment<Pet>(dog2);
		Appointment<Pet> ap9 = new Appointment<Pet>(dog3);
		Appointment<Pet> ap10 = new Appointment<Pet>(dog4);
		Appointment<Pet> ap11 = new Appointment<Pet>(dog5);
		Appointment<Pet> ap12 = new Appointment<Pet>(dog6);
		Appointment<Pet> ap13 = new Appointment<Pet>(dog7);
		Appointment<Pet> ap14 = new Appointment<Pet>(dog8);
		Appointment<Pet> ap15 = new Appointment<Pet>(dog9);
		Appointment<Pet> ap16 = new Appointment<Pet>(dog10);
		
		List<Appointment<Pet>> appointments = new ArrayList<>();
		appointments.add(ap1);
		appointments.add(ap2);
		appointments.add(ap3);
		appointments.add(ap4);
		appointments.add(ap5);
		appointments.add(ap6);
		appointments.add(ap7);
		appointments.add(ap8);
		appointments.add(ap9);
		appointments.add(ap10);
		appointments.add(ap11);
		appointments.add(ap12);
		appointments.add(ap13);
		appointments.add(ap14);
		appointments.add(ap15);
		appointments.add(ap16);
		Collections.shuffle(appointments);
		BlockingQueue<Appointment<Pet>> queue = new LinkedBlockingQueue<Appointment<Pet>>(appointments);
		
		
		Veterinarian joe = new Veterinarian(queue, 500, "joe");
		Veterinarian dave = new Veterinarian(queue, 1500, "dave");
		Veterinarian bill = new Veterinarian(queue, 3000, "bill");
		
		joe.start();
		dave.start();
		bill.start();
		
		
	}
}
