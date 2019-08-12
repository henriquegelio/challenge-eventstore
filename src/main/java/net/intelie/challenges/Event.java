package net.intelie.challenges;

/**
 * This is just an event stub, feel free to expand it if needed.
 */
public class Event {
    private final String type;
    private final Long timestamp;

    public Event(String type, Long timestamp) {
        this.type = type;
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public Long getTimeStamp() {
        return timestamp;
    }
    
    @Override
    public boolean equals(Object obj) {
    	
    	if (obj == this) {
	      return true;
    	}
    	if (!(obj instanceof Event)) {
	      return false;
    	}
    	
    	Event event = (Event) obj;
    	
    	return (event.type.equals(type) && event.timestamp == timestamp);
    	
    }
}
