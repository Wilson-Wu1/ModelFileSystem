package maven.file;
import java.io.*;
import java.util.*;
public class FileSystem {

    File file;
    Scanner sc;

    //For Some reason, the try and catch statement I originally used did not catch a non existing file.
    //Instead, I used a if statement.
    public File openFile(String filename) {

        String fullpathName = "src/main/resources/"+ filename;
        file = new File(fullpathName);

        if (file.exists() == true) {
            System.out.println("File succesfully obatained");
            return file;
        }
        System.out.println("File doesn't Exist Dummy");
        return null;
    }

    public Scanner attachScanner(File file) {
        try {

            sc = new Scanner(file);
            System.out.println("Scanner succesfully attached");
            return sc;
        } catch (Exception f) {
            System.out.println("Could not attach Scanner");
            //f.printStackTrace();
            return null;
        }
    }



    List < Directory > direct = new ArrayList < Directory > ();
    List < String > stringOfDirect = new ArrayList < String > ();
    List < FileActual > fileList = new ArrayList < FileActual > ();
    List < String > stringOfFiles = new ArrayList < String > ();
    public void importFile(Scanner sc) {

    	Directory root = new Directory("root");
        direct.add(root);
        stringOfDirect.add("root");
        while (sc.hasNext()) {

            List < String > directories = new ArrayList < String > ();
            String line = sc.nextLine();
            String currentName = "";
            int directoryCounter = 0;
            String previousName = "";

            if (line.charAt(0) == 'd') {
                for (int i = 2; i < line.length(); i++) {
                    if (line.charAt(i) == '/') {
                        directories.add(currentName);
                        
                        
                        if ((i + 1) != line.length()) {
                            previousName = currentName;
                            currentName = "";
                            directoryCounter++;
                        } else {
                            directoryCounter++;
                        }
                        

                    } else {
                        currentName = currentName + line.charAt(i);
                    }


                }
                if (directoryCounter == 1) {
                    direct.add(root);
                    stringOfDirect.add("root");
                    
                }
                else if (stringOfDirect.lastIndexOf(previousName)==-1){
                	System.out.println("ERROR: DIRECTORY " + currentName + "'s PARENT DIRECTORY "+previousName+" DOES NOT EXIST");
                	System.out.println("Directory "+currentName +" was not created");
                }
                else {

                    Directory parentName = direct.get(stringOfDirect.lastIndexOf(previousName));
                    String directName = directories.get(directoryCounter - 1);
                    Directory d = new Directory(currentName, parentName.getDirect());

                    direct.add(d);
                    stringOfDirect.add(directName);
                }


            } else if (line.charAt(0) == 'f') {
                for (int i = 2; i < line.length(); i++) {
                    if (line.charAt(i) == '/') {

                        directories.add(currentName);
                        previousName = currentName;
                        currentName = "";
                        directoryCounter++;
                    } else {
                        currentName = currentName + line.charAt(i);
                    }


                }

                if (stringOfDirect.lastIndexOf(previousName) == -1) {
                    System.out.println("ERROR: FILE " + currentName + "'s PARENT DIRECTORY DOES NOT EXIST");
                    System.out.println("File "+currentName +" was not created");
                } else {
                    Directory parentName = direct.get(stringOfDirect.lastIndexOf(previousName));
                    //String fileName = directories.get(directoryCounter-1);
                    FileActual f = new FileActual(currentName, parentName.getDirect());
  
                    fileList.add(f);
                    stringOfFiles.add(currentName);


                }

            } else {
                System.out.println("ERROR, FILE OR DIRECTORY NOT DETECTED");
                
            }






        }

    }
    public List < String > showImport(String start, String name) {
        Directory startingDirectory = direct.get(stringOfDirect.lastIndexOf(start));
        System.out.println(startingDirectory.find(name));
        return startingDirectory.find(name);
    }
    
    public FileActual attachObserver(String fileName){
    	if(stringOfFiles.lastIndexOf(fileName)==-1) {
    		System.out.println("attachObserver() FAILED: FILE name does not exist.");
    		return null;
    	}
  
    	FileActual name = fileList.get(stringOfFiles.lastIndexOf(fileName));
    	FileObserver obs = new FileObserver(name);
    	return name;
    }
    
    public Directory createDirect(String directName, String parentName) {
    	if(stringOfDirect.lastIndexOf(parentName)==-1){
    		System.out.println("createDirect() FAILED: Parent Directory does not exist.");
    		return null;
    	}
        Directory parent = direct.get(stringOfDirect.lastIndexOf(parentName));
        Directory d = new Directory(directName, parent);
        direct.add(d);
        stringOfDirect.add(directName);
        return d;
        
    }
    
    public FileActual createFile(String fileName, String parentName) {
    	if(stringOfDirect.lastIndexOf(parentName)==-1){
    		System.out.println("createFile() FAILED: Parent Directory does not exist.");
    		return null;
    	}
        Directory parent = direct.get(stringOfDirect.lastIndexOf(parentName));
        FileActual f = new FileActual(fileName,parent);
        return f;
    	
    }





}