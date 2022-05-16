package Activities;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Activity1 {
    static ArrayList<String> list;
    @BeforeEach
    void setUp() throws Exception {
        list=new ArrayList<String>();
        list.add("alpha"); 
        list.add("beta"); 
    }

    @Test
    public void insertTest() {
        assertEquals(2,list.size(),"Wrong size");
        list.add("Jiki");
        assertEquals(3,list.size(),"Wrong size");
        
        //Verify elements
        assertEquals("alpha",list.get(0),"Wrong element");
        assertEquals("beta",list.get(1),"Wrong element");
        assertEquals("Jiki",list.get(2),"Wrong element");
    }

    @Test
    public void replaceTest() {
    	 assertEquals(3,list.size(),"Wrong size");
        list.set(1, "Jiki");
        assertEquals(2, list.size(), "Wrong size");
        assertEquals("alpha", list.get(0), "Wrong element");
        assertEquals("Jiki", list.get(1), "Wrong element");
    }
}