package maven.file;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//Assignment 2 Testing
		/*
		Directory root = new Directory("root");
		Directory usr = new Directory ("usr",root);
		Directory stud = new Directory("stud",usr);
		Directory par = new Directory("par",usr);
		FileActual core4 = new FileActual("core4",stud);
		new FileActual("core",usr);
		new FileActual("adm",usr);
		new FileActual("core1",usr);
		new FileActual("core2",root);
		
		FileObserver obs = new FileObserver(core4);
		
		FileObserver obs1 = new FileObserver(core4);
		core4.write("Hello TA");
		
		List<String> finalList = root.find("par");
		System.out.println(finalList);	
		*/	
		
		//Assignment 3 Testing
		FileSystem system = new FileSystem();
		File file = system.openFile("TextFile");
		Scanner sc = system.attachScanner(file);
		system.importFile(sc);
		
		system.showImport("root","file");
		system.showImport("root","dir");
		
		//FileActual file2 = system.attachObserver("file2.pdf");
		//file2.write("hello");
		
		
		//system.createFile("newfile","dir3");
		
		//system.createDirect("dir4","root");
		//system.createDirect("dir5","dir4");
		//system.showImport("root","dir");

	
		
	}

}
