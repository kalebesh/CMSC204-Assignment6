import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class Road_STUDENT_Test {

	@Test
	public void testGetName() {
		Road road = new Road(new Town("Baltimore"), new Town("Silver Spring"), 34, "I395-Highway");
		assertEquals("I395-Highway", road.getRoadName());
	}

	@Test
	public void testGetSource() {
		Town town1 = new Town("Baltimore");
		Town town2 = new Town("Silver Spring");
		Road road = new Road(town1, town2, 34, "G67-Rootway");
		assertEquals(town1, road.getSource());
	}

	@Test
	public void testGetDestination() {
		Town town1 = new Town("Baltimore");
		Town town2 = new Town("Silver Spring");
		Road road = new Road(town1, town2, 34, "G67-Rootway");
		assertEquals(town2, road.getDestination());
	}

	@Test
	public void testGetWeight() {
		Road road = new Road(new Town("Baltimore"), new Town("Silver Spring"), 34, "T21-Jull");
		assertEquals(34, road.getWeight());
	}

	@Test
	public void testHashcode() {
		Road road = new Road(new Town("Baltimore"), new Town("Silver Spring"), 34, "Route396");
		assertEquals(road.hashCode(), road.hashCode());
	}

	@Test
	public void testContains() {
		Town town1 = new Town("Baltimore");
		Town town2 = new Town("Silver Spring");
		Road road = new Road(town1, town2, 23, "G67-Rootway");
		assertEquals(true, road.contains(town2));
		assertEquals(false, road.contains(new Town("Louisville")));
	}

}
