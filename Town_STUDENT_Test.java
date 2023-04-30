import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Town_STUDENT_Test {

	@Test
	public void testGetName() {
		Town town1 = new Town("Green");
		Town town2 = new Town("Blue");
		assertEquals("Green", town1.getName());
		assertEquals("Blue", town2.getName());
	}

	@Test
	public void testHashcode() {
		Town town1 = new Town("townA");
		Town town2 = new Town("townB");
		assertEquals(110553071, town1.hashCode());
		assertEquals(110553072, town2.hashCode());
	}

	@Test
	public void testToString() {
		Town town1 = new Town("Green");
		Town town2 = new Town("Blue");
		assertEquals("Green", town1.toString());
		assertEquals("Blue", town2.toString());
	}

	@Test
	public void testEqualsTown() {
		Town town1 = new Town("Green");
		Town town2 = new Town("Blue");
		String str1= "This is town: Green";
		String str2= "This is town: Blue";
		assertFalse((str1).equals(town2));
		assertTrue((str2).equals(town2));
	}

}
