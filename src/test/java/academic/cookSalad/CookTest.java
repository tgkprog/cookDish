package academic.cookSalad;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test for cook salads.
 * A more modular approach would be to make a excel reader based impl
 */
public class CookTest

{
	Cook cook;
	Ingredients in;
	
	@BeforeClass
	public static void once(){
		System.out.println("@BeforeClass Any set up once per test class");
		
	}
	/**
	 * Not neccessary but a nice way to initialize before every test
	 * */
	@Before
	public void setup(){
		System.out.println("setup");
		cook = new Cook();
		in = new Ingredients();
		in.setBeans(100);
		in.setIceBergLettuce(100);
		in.setChickens(100);
		in.setCarrots(100);
	}
	
	@Test
	public void cookCeasersWithEnoughIngre() {

		Status st = cook.cookIt(in, Dish.CEASERS);
		assertTrue("Enough ingredients ceasrers ", st.isSuccess());
		assertEquals(1, 1);

	}
	
	/*
	  While debugging can quickly disable other tests by commenting the annotation.
	  So can focus on the problematic test.
	  */
	@Test
	public void cookCeasersLess1Chicken() {
		in.setChickens(1);
		Status st = cook.cookIt(in, Dish.CEASERS);
		assertTrue("Enough ingredients ceasrers ", st.isSuccess());
		assertEquals(1, 1);

	}
	
	@Test
	public void cookRussianLess1Chicken() {
		in.setChickens(1);
		Status st = cook.cookIt(in, Dish.RUSSIAN);
		assertTrue("1 less chicken russian, enough carrots ", st.isSuccess());
		assertEquals(1, 1);
		print("less 1 chicken", st);

	}

	public static void print(String msg, Status st) {
		System.out.println(msg);
		System.out.println("[Success " + st.isSuccess());
		Ingredients out = st.getLeftOverIngrediants();
		System.out.println(", carrots " + out.getCarrots());
		System.out.println(", chicken " + out.getChickens());
		System.out.println(", beans " + out.getBeans());
		System.out.println(", ice burg " + out.getIceBergLettuce());
		System.out.println("]");
		
	}
}
