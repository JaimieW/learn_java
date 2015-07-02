package filesystem;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsyncIOFutureStyle {

	public static void main(String[] args){
		try{
			Path file = Paths.get("F:\\tmp\\big.txt");
			
			AsynchronousFileChannel channel = AsynchronousFileChannel.open(file);
			ByteBuffer buffer = ByteBuffer.allocate(1_000_000);
			Future<Integer> result = channel.read(buffer, 0);
			
			while(!result.isDone()){
				System.out.println("processing...");
			}
			Integer bytesRead = result.get();
			System.out.println("Bytes read: ["+bytesRead+"]");
			
		}catch(IOException | ExecutionException | InterruptedException e){
			System.out.println(e.getMessage());
		}
	}
}
