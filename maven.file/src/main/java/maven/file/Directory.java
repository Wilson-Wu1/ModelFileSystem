package maven.file;

import java.util.ArrayList;
import java.util.List;

class Directory extends Node {

    private List < Node > _children;

    Directory(String n) {
        super(n, null);
        this._children = new ArrayList < Node > ();

    }
    Directory(String n, Directory p) {

        super(n, p);
        this._children = new ArrayList < Node > ();
        Node a = getNode(n);
        p.add(a);
    }


    public String getAbsoluteName() {
        return _name;
    }

    public void add(Node n) {
        _children.add(n);
    }


    public Directory getParent() {
        return this._parent;
    }

    public List < Node > getChildList() {
        return this._children;
    }
    public Node getNode(String _name) {
        return this;
    }


    public List < String > find(String s) {

        String Finalresult = "";
        List < String > result = new ArrayList < String > ();


        if (_name.indexOf(s) != -1) {


            Finalresult = Finalresult + this.getAbsoluteName() +"/" ;
           Directory nextParent = _parent;
            if (nextParent != null) {
                while (nextParent.getParent() != null) {
                    Finalresult = nextParent + "/" + Finalresult;
                    nextParent = nextParent.getParent();
                }
            }
            if (nextParent != null) {
                Finalresult = nextParent + "/" + Finalresult;
            }
        }

        for (Node child: _children) {
            result.addAll(child.find(s));


        }
        if (Finalresult != "") {
            result.add(Finalresult);
        }

        return result;

    }

}