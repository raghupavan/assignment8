package pack;

import static org.junit.Assert.*;

import org.junit.Test;

public class LoginServletTest1 {

	@Test
	public final void test() {
		String message="chicago";
		String la[]={"41.8781136","-87.6297982","-78.67485"};
		LoginServlet ls=new LoginServlet();
		try {
			String[] lat=LoginServlet.getLatLongPositions(message);
			System.out.println(lat.length);
			System.out.println("initially declared"+la.length);
			assertEquals(lat.length, la.length);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public final void test2()
	{
		Double d=603.5815906789378;
		LoginServlet ls=new LoginServlet();
		Double abc=LoginServlet.distance(41.8781136,  -87.6297982, 39.0119020, -98.4842465,
				"k");
		System.out.println("abc value"+abc);
		System.out.println("d value"+d);
		assertEquals(d, abc);
	}

}
