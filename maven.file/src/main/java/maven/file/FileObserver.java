package maven.file;

class FileObserver implements Observer {
	private File _subject;
	FileObserver(File f){
		f.attach(getObserver());
		_subject = f;
	}
	public Observer getObserver(){
		return this;
	}
	


	public void update() {
		System.out.println( "WARNING: file "+_subject.getAbsoluteName()+" has changed.");
		
		
	}

}
