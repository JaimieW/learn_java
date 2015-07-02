package filesystem;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AsyncIOCallbackStyle {
	public static void main(String[] args){
		try{
			Path file = Paths.get("F:\\tmp\\big.txt");
			
			AsynchronousFileChannel channel = AsynchronousFileChannel.open(file);
			ByteBuffer buffer = ByteBuffer.allocate(100_000);
			
			channel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>(){

				@Override
				public void completed(Integer result, ByteBuffer attachment) {
					System.out.println("Bytes read : ["+result+"]");
				}

				@Override
				public void failed(Throwable exc, ByteBuffer attachment) {
					System.out.println(exc.getMessage());
				}
				
			});
			

			System.out.println("the callback only prints if there's a delay");
			System.out.println("otherwise the main thread exits and");
			System.out.println("the spawned daemon? thread is killed");
			
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
}
