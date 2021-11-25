import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import hydraulic.Element;
import hydraulic.HSystemExt;
import hydraulic.Sink;
import hydraulic.Source;
import hydraulic.Split;
import hydraulic.Tap;

public class MyTest {

	public static void main(String[] args) {
		HSystemExt s = new HSystemExt();
		
		assertNotNull("Apparently not implemented yet",s.getElements());
		assertEquals("Initially no elements are present in the system",0,s.getElements().length);
	
		// 1) the elements of the system are defined and added
		Source src = new Source("Src");
		s.addElement(src);
		Tap r = new Tap("R");
		s.addElement(r);
		Split t = new Split("T");
		s.addElement(t);
		Split t1 = new Split("T1");
		s.addElement(t1);
		Sink sink1 = new Sink("sink A");
		s.addElement(sink1);
		Sink sink2 = new Sink("sink B");
		s.addElement(sink2);
		Sink sink3 = new Sink("sink C");
		s.addElement(sink2);
		
		

		
		// 2) elements are then connected
		src.connect(r);
		r.connect(t);
		t.connect(t1,1);
		t.connect(sink1,0);
		t1.connect(sink2,0);
		
		System.out.println(s.layout());

	}

}
