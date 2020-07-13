package maven.file;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FileActualTest {
	Directory root;
	
	@Before
	public void beforeEach() {
		root = new Directory ("root");
	}
	
	@Test
	public void testingFindString() {
		Directory root1 = new Directory("root1",root);
		Directory root2 = new Directory("root2",root1);
		new FileActual("file",root2);
		List<String> result = root.find("file");
		List<String> equal = new ArrayList<String>();
		equal.add("root/root1/root2/file");
	
		assertEquals(result,equal);
	}
	@Test 
	public void testingAttach() {
		FileActual file = new FileActual("file",root);
		Observer obs = new FileObserver(file);
		file.attach(obs);
		List<Observer> equal = new ArrayList<Observer>();
		equal.add(obs);
		List<Observer> result = file.getObserverList();
		
		assertEquals(result, equal);
	}
	@Test
	public void testingRemove() {
		FileActual file = new FileActual("file",root);
		Observer obs = new FileObserver(file);
		file.attach(obs);
		file.detach(obs);
		List<Observer> equal = new ArrayList<Observer>();
		List<Observer> result = file.getObserverList();
		assertEquals(result, equal);
		
	}
	@Test
	public void testingNotify() {
		FileActual file = new FileActual("file",root);
		Observer obs1 = new FileObserver(file);
		Observer obs2 = new FileObserver(file);
		Observer obs3 = new FileObserver(file);
		file.write("hello");
		//Notify calls update 3 times.
		assertEquals(3,file.getCounter());
		
	}
}
