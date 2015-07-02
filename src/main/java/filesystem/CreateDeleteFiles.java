package filesystem;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.Set;


public class CreateDeleteFiles {

	public static void main(String[] args) throws IOException{
		Path target = Paths.get("F:\\tmp\\tmp.txt");
		Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw-rw-rw-");
		FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
		Path file = Files.createFile(target, attr);
		
	}
}
