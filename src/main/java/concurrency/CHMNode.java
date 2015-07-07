package concurrency;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CHMNode implements SimpleNode {

	private final String identifier;
	private final Map<Update, Long> arrivalTime = new ConcurrentHashMap<>();
	
	public CHMNode(String identifier){
		this.identifier = identifier;
	}
	
	@Override
	public synchronized String getIdentifier() {
		return identifier;
	}

	@Override
	public void propagateUpdate(Update update, SimpleNode node) {
		long currentTime = System.currentTimeMillis();
		arrivalTime.putIfAbsent(update, currentTime);
		node.confirmUpdateReceived(this, update);
	}

	@Override
	public boolean confirmUpdateReceived(Update update) {
		Long timeReceived = arrivalTime.get(update);
		System.out.println(timeReceived != null ? getIdentifier() + " received update "+update.getText() : "update failed") ;
		return timeReceived != null;
	}

	@Override
	public boolean confirmUpdateReceived(SimpleNode node, Update update) {
		// TODO Auto-generated method stub
		return false;
	}

}
