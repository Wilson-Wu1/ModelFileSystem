package maven.file;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class FileObserverTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	
	@Test
	public void testingGetObserver() {
		Directory root = new Directory("root");
		File file = new File("file",root);
		Observer obs1 = new FileObserver(file);
		obs1.getObserver();
		assertEquals(obs1,obs1.getObserver());
	}
	/*
	@Test
	public void testingUpdate() {
		System.setOut(new PrintStream(outContent));
		Directory root = new Directory("root");
		File file = new File("file",root);
		Observer obs = new FileObserver(file);
		file.write("hi");
		

		assertEquals("WARNING: file "+file.getAbsoluteName()+" has changed.",outContent);
		
	}
	*/
	

}
