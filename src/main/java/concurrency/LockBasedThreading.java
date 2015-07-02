package concurrency;

public class LockBasedThreading {

	public static void main (String[] args){
		new LockBasedThreading().run();
	}

	public void run(){
		final LockNode local = new LockNode("localhost:8888");
		final LockNode remote = new LockNode("localhost:9999");

		final Update up1 = getUpdate("1");
		final Update up2 = getUpdate("2");

		new Thread(new Runnable(){
			@Override
			public void run() {
				local.propagateUpdate(up1, remote);
			}
		}).start();

		new Thread(new Runnable(){
			@Override
			public void run() {
				remote.propagateUpdate(up2, local);
			}
		}).start();
	}

	private static Update getUpdate(String id){
		return new Update(id);
	}
}
