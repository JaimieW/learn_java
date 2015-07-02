package concurrency;


public class BlockBasedThreading {

	public static void main (String[] args){
		new BlockBasedThreading().run();
	}
	
	public void run(){
//		SimpleNode local = new ExampleSimpleNode("localhost:8888");
//		SimpleNode remote = new ExampleSimpleNode("localhost:9999");
		final MicroNode local = new MicroNode("localhost:8888");
		final MicroNode remote = new MicroNode("localhost:9999");
		
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
