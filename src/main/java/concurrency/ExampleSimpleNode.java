package concurrency;

import java.util.HashMap;
import java.util.Map;

public class ExampleSimpleNode implements SimpleNode {

	private final String identifier;
	private final Map<Update, Long> arrivalTime = new HashMap<>();
	
	public ExampleSimpleNode(String identifier){
		this.identifier = identifier;
	}
	
	@Override
	public synchronized String getIdentifier() {
		return identifier;
	}

	@Override
	public synchronized void propagateUpdate(Update update, SimpleNode node) {
		long currentTime = System.currentTimeMillis();
		arrivalTime.put(update, currentTime);
		System.out.println(node.confirmUpdateReceived(update) ? node.getIdentifier() + "received update" : "update failed") ;
	}

	@Override
	public synchronized boolean confirmUpdateReceived(Update update) {
		Long timeReceived = arrivalTime.get(update);
		return timeReceived != null;
	}

	@Override
	public boolean confirmUpdateReceived(SimpleNode node, Update update) {
		// TODO Auto-generated method stub
		return false;
	}

}
