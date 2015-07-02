package concurrency;

public interface SimpleNode {

	public String getIdentifier();
	public void propagateUpdate(Update update, SimpleNode node);
	public boolean confirmUpdateReceived(Update update);
	public boolean confirmUpdateReceived(SimpleNode node, Update update);
}
