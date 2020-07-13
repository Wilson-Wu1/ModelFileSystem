package maven.file;

import java.util.List;

abstract class Node {
    protected String _name;
    protected Directory _parent;

    public abstract List < String > find(String s);

    Node(String name, Directory parent) {
        _name = name;
        _parent = parent;
    }

    public String getAbsoluteName() {
        return _name;

    }

    public String toString() {
        return getAbsoluteName();
    }

    public Node getNode(String _name) {
        return this;
    }

    public Directory getParent() {
        return this._parent;
    }



}