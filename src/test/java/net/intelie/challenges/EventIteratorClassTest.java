package net.intelie.challenges;

import static org.junit.Assert.*;
import org.junit.Test;

public class EventIteratorClassTest {
	
	@Test
	//Testing the indexes from the EventIteratorClass to check if they are moving accordingly.
	public void moveTest() {
		int index;
		EventStoreDAO eventSDao = new EventStoreDAO();
		eventSDao.insert(new Event("anything", 11));
		eventSDao.insert(new Event("anything", 51));
		eventSDao.insert(new Event("anything", 71));
		eventSDao.insert(new Event("anything", 91));
		eventSDao.insert(new Event("anything", 111));
		eventSDao.insert(new Event("anything", 211));
		eventSDao.insert(new Event("anything", 311));
		eventSDao.insert(new Event("anything", 351));
		eventSDao.insert(new Event("anything", 411));
		eventSDao.insert(new Event("anything", 491));
		System.out.println("dsasdsdsd");
		EventIteratorClass eventIterator = eventSDao.query("anything", 200, 400);
		System.out.println(eventIterator.getIndex());
		assertTrue(eventIterator.moveNext());
		System.out.println(eventIterator.getIndex());
		assertTrue(eventIterator.moveNext());
		System.out.println(eventIterator.getIndex());
		assertFalse(eventIterator.moveNext());
		System.out.println(eventIterator.getIndex());
		eventIterator = eventSDao.query("anything", 100, 500);
		System.out.println(eventIterator.getIndex());
		index = eventIterator.getIndex();
		assertNotEquals(index, eventIterator.moveNext());
		System.out.println(eventIterator.getIndex());
	}
	
	@Test
	// Testing if the current method from EventIteratorClass is returning the correct event after some insertions and moves into the eventIterator
	public void currentTest() {
		Event event;
		EventStoreDAO eventSDao = new EventStoreDAO();
		eventSDao.insert(new Event("anything", 11));
		eventSDao.insert(new Event("anything", 51));
		eventSDao.insert(new Event("anything", 71));
		eventSDao.insert(new Event("anything", 91));
		eventSDao.insert(new Event("anything", 111));
		eventSDao.insert(new Event("anything", 211));
		eventSDao.insert(new Event("anything", 311));
		eventSDao.insert(new Event("anything", 351));
		eventSDao.insert(new Event("anything", 411));
		eventSDao.insert(new Event("anything", 491));
		EventIteratorClass eventIterator = eventSDao.query("anything", 200, 400);
		event = eventIterator.current();
		System.out.println(eventIterator.getEvents());
		System.out.println(event);
		System.out.println(eventIterator.getIndex());
		eventIterator.moveNext();
		System.out.println(eventIterator.current());
		System.out.println(eventIterator.getIndex());
		assertNotEquals(event, eventIterator.current());
	}
	
	@Test (expected = IllegalStateException.class)
	// Testing if the current method from the EventIteratorClass is returning the correct exception when one tries to get the current event from a empty eventIterator
	public void currentTestEX() {
		EventStoreDAO eventSDao = new EventStoreDAO();
		EventIteratorClass eventIterator = eventSDao.query("anything", 200, 400);
		System.out.println(eventIterator.getEvents());
		System.out.println(eventIterator.getIndex());
	    eventIterator.current();
	}
	
	@Test
	// Testing if the remove method from EventIteratorClass is removing the correct event from the eventIterator
	public void removeTest() {
		EventStoreDAO eventSDao = new EventStoreDAO();
		eventSDao.insert(new Event("anything", 11));
		eventSDao.insert(new Event("anything", 51));
		eventSDao.insert(new Event("anything", 71));
		eventSDao.insert(new Event("anything", 91));
		eventSDao.insert(new Event("anything", 111));
		eventSDao.insert(new Event("anything", 211));
		eventSDao.insert(new Event("anything", 311));
		eventSDao.insert(new Event("anything", 351));
		eventSDao.insert(new Event("anything", 411));
		eventSDao.insert(new Event("anything", 491));
		EventIteratorClass eventIterator = eventSDao.query("anything", 200, 500);
		System.out.println(eventIterator.getEvents());
		System.out.println(eventIterator.getIndex());
		eventIterator.moveNext();
		System.out.println(eventIterator.getIndex());
		eventIterator.remove();
		assertNotEquals(eventIterator, eventSDao.query("anything", 200, 500));
		System.out.println(eventIterator.getEvents());
		System.out.println(eventIterator.current());
		System.out.println(eventIterator.getIndex());
		eventIterator = eventSDao.query("anything", 200, 500);
		System.out.println(eventIterator.getEvents());
		System.out.println(eventIterator.getIndex());
		eventIterator.remove();
		assertNotEquals(eventIterator, eventSDao.query("anything", 200, 500));
		System.out.println(eventIterator.getEvents());
		System.out.println(eventIterator.current());
		System.out.println(eventIterator.getIndex());
		eventIterator = eventSDao.query("anything", 200, 400);
		eventIterator.moveNext();
		eventIterator.moveNext();
		System.out.println(eventIterator.getEvents());
		System.out.println(eventIterator.getIndex());
		eventIterator.remove();
		System.out.println(eventIterator.getEvents());
		System.out.println(eventIterator.current());
		System.out.println(eventIterator.getIndex());
		assertNotEquals(eventIterator, eventSDao.query("anything", 200, 400));
	}
	
	@Test (expected = IllegalStateException.class)
	// Testing if the remove method from EventIteratorClass is throwing the right exception when ones tries to remove an event from an empty eventIterator
	public void removeTestEX() {
		EventStoreDAO eventSDao = new EventStoreDAO();
		EventIteratorClass eventIterator = eventSDao.query("anything", 200, 400);
		System.out.println(eventIterator.getEvents());
		System.out.println(eventIterator.getIndex());
	    eventIterator.remove();
	}
}
