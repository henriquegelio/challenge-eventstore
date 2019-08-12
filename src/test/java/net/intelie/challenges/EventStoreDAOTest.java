package net.intelie.challenges;

import static org.junit.Assert.*;
import org.junit.Test;

public class EventStoreDAOTest {
	@Test
	//Testing the insertion method from EventStoreDAO. Checked for emptiness on a new instance and successful inserts.
	public void insertTest() {
		Event event = new Event("anything", 11);
		EventStoreDAO eventSDao = new EventStoreDAO();
		assertTrue(eventSDao.getEvents().isEmpty());
		EventIteratorClass eventIterator = eventSDao.query("anything", 0, 46);
		assertTrue(eventIterator.getEvents().isEmpty());
		eventSDao.insert(event);
		assertFalse(eventSDao.getEvents().isEmpty());
		assertNotEquals(eventIterator, eventSDao.query("anything", 0, 46));
	}
	
	//Testing the removal method from EventStoreDAO. Compared the states prior and after the removal process.
	@Test
	public void removeTest() {
		EventStoreDAO eventSDao = new EventStoreDAO();
		eventSDao.insert(new Event("anything", 11));
		eventSDao.removeAll("anything");
		assertTrue(eventSDao.getEvents().isEmpty());
		EventIteratorClass eventIterator = eventSDao.query("anything", 0, 50);
		assertTrue(eventIterator.getEvents().isEmpty());
		eventSDao.insert(new Event("anything", 11));
		eventSDao.insert(new Event("another thing", 30));
		eventIterator = eventSDao.query("anything", 0, 30);
		eventSDao.removeAll("anything");
		assertFalse(eventSDao.getEvents().isEmpty());
		assertNotEquals(eventIterator, eventSDao.query("anything", 0, 30));
	}
	
	//Testing the retrieve of events of different types to check if they are retrieved correctly.
	@Test
	public void queryTest() {
		EventStoreDAO eventSDao = new EventStoreDAO();
		eventSDao.insert(new Event("anything", 11));
		eventSDao.insert(new Event("another thing", 2));
		EventIteratorClass eventIterator = eventSDao.query("anything", 0, 46);
		System.out.println(eventIterator.getEvents().toString());
		System.out.println(eventSDao.query("anything", 0, 46).getEvents().toString());
		System.out.println(eventSDao.query("another thing", 0, 46).getEvents().toString());
		assertNotEquals(eventIterator, eventSDao.query("another thing", 0, 46));
		assertEquals(eventIterator, eventSDao.query("anything", 0, 46));
	}
}
