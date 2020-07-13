package maven.file;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DirectoryTest {

    Directory root;
    @Before
    public void beforeEach() {
        root = new Directory("root");
    }

    @Test
    public void testingWithOnlyStringParameter() {

        Directory result = root.getParent();
        assertEquals(result, null);

    }

    @Test
    public void testingWithTwoParameters() {

        Directory usr = new Directory("urs", root);
        Directory result = usr.getParent();
        assertEquals(result, root);

    }
    @Test
    public void testingGetAbsName() {

        String result = root.getAbsoluteName();
        assertEquals(result, "root");

    }
    @Test
    public void testingEmptyChildList() {

        Directory root1 = new Directory("root");
        assertEquals(root.getChildList(), root1.getChildList());


    }
    //different files should be detected as different
    @Test
    public void testingFilledChildList() {

        Directory root1 = new Directory("root1");
        new File("file", root);
        new File("file", root1);
        assertNotEquals(root.getChildList(), root1.getChildList());


    }
    @Test
    public void testingFindString() {

        Directory root1 = new Directory("root1", root);
        Directory root2 = new Directory("root2", root1);
        new File("file", root2);
        List < String > result = root.find("file");
        List < String > equal = new ArrayList < String > ();
        equal.add("root/root1/root2/file");

        assertEquals(result, equal);
    }
    @Test
    public void testingFindDirectory() {

        Directory root1 = new Directory("root1", root);
        Directory root2 = new Directory("root2", root1);
        new File("file", root2);
        List < String > result = root.find("2");
        List < String > equal = new ArrayList < String > ();
        equal.add("root/root1/root2/");

        assertEquals(result, equal);
    }
    @Test
    public void testingNotFound() {

        List < String > result = root.find("file");
        List < String > equal = new ArrayList < String > ();
        assertEquals(result, equal);


    }
    @Test
    public void testingJustRoot() {
        List < String > result = root.find("root");
        List < String > equal = new ArrayList < String > ();
        equal.add("root/");
        assertEquals(equal,result);
    }





}