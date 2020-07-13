package maven.file;

import java.util.List;


public class Main {
	public static void main(String[] args) {
		Directory root = new Directory("root");
		Directory usr = new Directory ("usr",root);
		Directory stud = new Directory("stud",usr);
		File core4 = new File("core4",stud);
		new File("core",usr);
		new File("adm",usr);
		new File("core1",usr);
		new File("core2",root);
		
		FileObserver obs = new FileObserver(core4);
		
		FileObserver obs1 = new FileObserver(core4);
		core4.write("Hello TA");
		
		List<String> finalList = root.find("usr");
		System.out.println(finalList);		
		


		//new Directory("foo",usr);
		//System.out.println(root.find("adm"));
		
	}

}
