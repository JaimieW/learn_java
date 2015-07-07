package concurrency;

public class CHMThreading {
	public static void main (String[] args){
		new CHMThreading().run();
	}

	public void run(){
		final CHMNode local = new CHMNode("localhost:8888");
		final CHMNode remote = new CHMNode("localhost:9999");

		final Update up1 = getUpdate("1");
		final Update up2 = getUpdate("2");

		new Thread(new Runnable(){
			@Override
			public void run() {
				local.propagateUpdate(up1, remote);
				remote.confirmUpdateReceived(up1);
			}
		}).start();

		new Thread(new Runnable(){
			@Override
			public void run() {
				remote.propagateUpdate(up2, local);
				remote.confirmUpdateReceived(up2);
			}
		}).start();
	}

	private static Update getUpdate(String id){
		return new Update(id);
	}
}
