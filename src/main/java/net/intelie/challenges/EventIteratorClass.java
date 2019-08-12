package net.intelie.challenges;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Implements EventIterator interface..
 */
@EqualsAndHashCode @ToString
public class EventIteratorClass implements EventIterator {
	
	@Getter private List<Event> events;
	private Event event;
	@Getter private int index = -1;
	
	public EventIteratorClass(List<Event> events) {
		this.events = events;
		if (!this.events.isEmpty()) {
			index = 0;
			event = events.get(index);
		}
	}

	@Override
	public void close() throws Exception {
		events = null;
	}

	@Override
	public boolean moveNext() {
		if (events.isEmpty() || events == null) {
            return false;
        }
		else {
			index++;
            if (index <= events.size() - 1) {
                event = events.get(index);
                return true;
            }
            else {
            	index = -1;
            	return false;
            }
        }
	}

	@Override
	public Event current() {
		if (events.isEmpty() || events == null || index == -1 || event == null) {
			throw new IllegalStateException("Event Iterator was never started.");
		}
		else if (index > events.size() - 1) {
			throw new IllegalStateException("There is no current event.");
		}
		else {
			return this.event;
		}
	}

	@Override
	public void remove() {
		if (events.isEmpty() || events == null || index == -1 || event == null) {
			throw new IllegalStateException("Event Iterator was never started.");
		}
		else if (index > events.size() - 1) {
			throw new IllegalStateException("There is no current event.");
		}
		else {
			events.remove(index);
			if (!events.isEmpty()) {
				if (index > events.size() - 1) {
					index--;
				}
				this.event = events.get(index);
			}
			else {
				this.event = null;
				index = -1;
			}
		}
	}

}
