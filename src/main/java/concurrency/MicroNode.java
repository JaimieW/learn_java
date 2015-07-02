package concurrency;

public class MicroNode implements SimpleNode {

	private final String identifier;

	public MicroNode(String identifier){
		this.identifier = identifier;
	}
	
	@Override
	public String getIdentifier() {
		return identifier;
	}

	@Override
	public synchronized void propagateUpdate(Update update, SimpleNode node) {
		System.out.println(identifier + ": recvd: "+ update.getText()+ " ; backup: "+node.getIdentifier());
		sleep();
		node.confirmUpdateReceived(this, update);

	}

	private void sleep() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized boolean confirmUpdateReceived(SimpleNode node, Update update) {
		System.out.println(identifier + ": recvd confirm: "+update.getText() + " ; from: " + node.getIdentifier());
		return false;
	}

	@Override
	public boolean confirmUpdateReceived(Update update) {
		// TODO Auto-generated method stub
		return false;
	}

}
