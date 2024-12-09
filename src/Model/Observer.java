package Model;

/**
 * Interface: Observer
 * Purpose: Defines the contract for observer classes to receive notifications about specific events.
 * Authors: Bruno Valdez & Manuel Rodriguez
 */
public interface Observer {

    /**
     * Notifies the observer of an event.
     *
     * @param event A string containing details about the event that occurred.
     */
    void update(String event);
}
