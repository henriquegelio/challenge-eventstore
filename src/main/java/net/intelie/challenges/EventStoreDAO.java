package net.intelie.challenges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.com.jonyfs.event.EventIteratorImpl;
import lombok.Getter;

public class EventStoreDAO implements EventStore{
	
	//The use of synchronized list wrapper is to be sure it's returning a thread-safe collection.
	@Getter protected final List<Event> events = Collections.synchronizedList(new ArrayList<Event>());

	@Override
	public void insert(Event event) {
		events.add(event);
	}

	@Override
	public void removeAll(String type) {
        events.removeIf(event -> event.getType().equals(type));
	}

	@Override
	public EventIterator query(String type, Long startTime, Long endTime) {
		
		//In order to filter, I'm using streams to make it easier
        return new EventIteratorClass(Collections.synchronizedList(events.stream()
            .filter(event -> event.getType().equals(type))
            .filter(event -> event.getTimeStamp() >= startTime)
            .filter(event -> event.getTimeStamp() < endTime).collect(Collectors.toList())));
	}
}
