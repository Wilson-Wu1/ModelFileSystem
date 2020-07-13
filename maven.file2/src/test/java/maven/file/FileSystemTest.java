package maven.file;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class FileSystemTest {
	
	FileSystem system;
	
	@Before
	public void beforeEach() {
		system = new FileSystem();
	}
	
	@Test
	public void testingOpenFile() {
		File result = system.openFile("TextFile");
		assertNotNull(result);
	}
	
	@Test
	public void testingOpenFileButFileDoesNotExist() {
		File result = system.openFile("ThisFileDoesn'tExist");
		File expected = null;
		assertEquals(result, expected);
	}
	
	@Test
	public void testingAttachScanner() {
		File result = system.openFile("TextFile");
		Scanner sc = system.attachScanner(result);
		assertNotNull(sc);
	}
	//Could not attach Scanner is the intended output.
	@Test
	public void testingAttachScannerGoneWrong() {
		File result=null;
		Scanner sc = system.attachScanner(result);
		Scanner nullsc = null;
		assertEquals(sc,nullsc);
	}
	@Test
	public void testingImportDirectorySuccesfully() {
		File result = system.openFile("TextFile");
		Scanner sc = system.attachScanner(result);
		system.importFile(sc);

		List<String> output = new ArrayList<String>();
		List<String> finalResult = new ArrayList<String>();
		output = system.showImport("root","dir");
		finalResult.add("root/dir1/");
		finalResult.add("root/dir2/dir3/");
		finalResult.add("root/dir2/");
		assertEquals(output,finalResult);
	}
	@Test
	public void testingImportFileSuccesfully() {
		File result = system.openFile("TextFile");
		Scanner sc = system.attachScanner(result);
		system.importFile(sc);

		List<String> output = new ArrayList<String>();
		List<String> finalResult = new ArrayList<String>();
		output = system.showImport("root","file");
		finalResult.add("root/dir1/file2.pdf");
		finalResult.add("root/dir2/dir3/file3.png");
		finalResult.add("root/file1.txt");
		assertEquals(output,finalResult);
	}
	
	@Test
	public void testingImportNeitherFileOrDirectory() {
		File result = system.openFile("TextFile");
		Scanner sc = system.attachScanner(result);
		system.importFile(sc);
		List<String> output = new ArrayList<String>();
		List<String> finalResult = new ArrayList<String>();
		output = system.showImport("root","file");
		finalResult.add("root/dir1/file2.pdf");
		finalResult.add("root/dir2/dir3/file3.png");
		finalResult.add("root/file1.txt");
		finalResult.add("root/dir4/thisThingIsNeitherFileOrDirectory");
		assertNotEquals(finalResult,output);
	}
	@Test
	public void testingCreateDirect() {
		File result = system.openFile("TextFile");
		Scanner sc = system.attachScanner(result);
		system.importFile(sc);
		system.createDirect("dir4","root");
		List<String> output = new ArrayList<String>();
		List<String> finalResult = new ArrayList<String>();
		output = system.showImport("root","dir");
		finalResult.add("root/dir1/");
		finalResult.add("root/dir2/dir3/");
		finalResult.add("root/dir2/");
		finalResult.add("root/dir4/");
		assertEquals(finalResult,output);
		
	}
	@Test
	public void testingCreateFile() {
		File result = system.openFile("TextFile");
		Scanner sc = system.attachScanner(result);
		system.importFile(sc);
		system.createFile("newfile","dir1");
		List<String> output = new ArrayList<String>();
		List<String> finalResult = new ArrayList<String>();
		output = system.showImport("root","file");
		finalResult.add("root/dir1/file2.pdf");
		finalResult.add("root/dir1/newfile");
		finalResult.add("root/dir2/dir3/file3.png");
		finalResult.add("root/file1.txt");
		assertEquals(finalResult,output);
	}
	@Test
	public void testingFailedCreateDirect() {
		Directory result = system.createDirect("dir4","root");
		assertEquals(result,null);
	}
	@Test
	public void testingFailedCreateFile() {
		FileActual result = system.createFile("failingfile","root");
		assertEquals(result,null);
	}

	
	@Test
	public void testingAttachObserver() {
		File result = system.openFile("TextFile");
		Scanner sc = system.attachScanner(result);
		system.importFile(sc);
		FileActual file2 = system.attachObserver("file2.pdf");
		assertNotNull(file2);
		
	}
	@Test
	public void testingAttachObserverFailure() {
		File result = system.openFile("TextFile");
		Scanner sc = system.attachScanner(result);
		system.importFile(sc);
		FileActual file2 = system.attachObserver("randomFileThatDoesNotExist");
		assertEquals(file2,null);
		
	}
}
