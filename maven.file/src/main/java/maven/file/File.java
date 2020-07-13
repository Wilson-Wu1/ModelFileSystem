package maven.file;

import java.util.ArrayList;
import java.util.List;

class File extends Node {
	private List<Observer> _observers = new ArrayList<Observer>();
	private int count;
	
    File(String n, Directory p) {
        super(n, p);
        Node a = getNode(n);
        p.add(a);

    }
   
    public void attach(Observer o) {
    	if(!_observers.contains(o)) {
    		_observers.add(o);
    	}
    }
    
    public void detach(Observer o) {
    	_observers.remove(o);
    }
    
    public void notifyObservers() {
    	for(Observer obs: _observers) {
    		obs.update();
    		counter();

    	}
    }
    public void counter() {
    	count++;
    }
    public int getCounter() {
    	return count;
    }

    public void write(String s) {
    	notifyObservers();
    	
    }
    
    public List<Observer> getObserverList() {
    	return _observers;
    }


    public List < String > find(String s) {
        String Finalresult = "";
        List < String > result = new ArrayList < String > ();
        if (_name.indexOf(s) != -1) {
            Finalresult = Finalresult + this.getAbsoluteName();
            Directory nextParent = _parent;
            while (nextParent.getParent() != null) {
                Finalresult = nextParent + "/" + Finalresult;
                nextParent = nextParent.getParent();
            }

            Finalresult = nextParent + "/" + Finalresult;
        }
        if (Finalresult != "") {
            result.add(Finalresult);
        }
        return result;



    }


}