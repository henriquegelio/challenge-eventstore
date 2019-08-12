# Implement EventStore

In this challenge, I have created a class that implements the `EventStore` 
interface.
 
```java
public interface EventStore {
    void insert(Event event);

    void removeAll(String type);

    EventIterator query(String type, long startTime, long endTime);
}
```

My implementation stores events in memory using Synchronized Lists data structures.
I chose to use them because they are naturally thread-safe.
The behavior for the interface is described in the
provided code javadocs, please see `EventStore` and `EventIterator`
interfaces inside the `src/main/java` directory.
 
Some tests were also written for each method from the created classes.
The evidence of the thread-safety is the wrapper Synchronized List itself,
but it might also be verified at `https://www.baeldung.com/java-synchronized-collections`

"Collections.synchronizedList() method Returns a synchronized (thread-safe) list backed by the specified list. In order to guarantee serial access, it is critical that all access to the backing list is accomplished through the returned list."

There were created two classes:

-> `EventStoreDAO`, which implements the EventStore interface and is responsible for the CRUD operations;

-> `EventInteretorClass`, which implements the EventInterator interface and is designed to be the iterator itself.

It's easier to understand this way and we can reserve each class to it's main purpose, 
so we can increase cohesion between classes and decrease coupling.
  
I used `Lombok` dependency as well in order to decrease verbosity.

As instructed, the original repo was forked and a link will be provided to Intelie.
