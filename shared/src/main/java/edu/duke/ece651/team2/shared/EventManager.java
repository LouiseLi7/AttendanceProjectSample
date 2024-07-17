package edu.duke.ece651.team2.shared;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

/**
 * The EventManager class is responsible for managing the list of EventListeners
 * and notifying them when the attendance
 * of a Student changes.
 */
public class EventManager {
    /**
     * The list of EventListeners that are subscribed to this EventManager.
     */
    private final ArrayList<EventListener> listeners = new ArrayList<>();

    /**
     * Subscribes an EventListener to this EventManager.
     *
     * @param listener The EventListener to subscribe.
     */
    public void subscribe(EventListener listener) {
        listeners.add(listener);
    }

    /**
     * Unsubscribes an EventListener from this EventManager.
     *
     * @param listener The EventListener to unsubscribe.
     */
    public void unsubscribe(EventListener listener) {
        listeners.remove(listener);
    }

    /**
     * Notifies all subscribed EventListeners that the attendance of a Student has
     * changed.
     *
     * @param student The student whose attendance has changed.
     * @param record  The attendance record for the student.
     * @throws GeneralSecurityException If there is a security error.
     * @throws IOException              If there is an I/O error.
     */
    public void notifyAttendanceChanged(Student student, AttendanceRecord record)
            throws GeneralSecurityException, IOException {
        for (EventListener listener : listeners) {
            listener.attendanceChanged(student, record);
        }
    }

    /**
     * Retrieves the list of EventListeners subscribed to this EventManager.
     *
     * @return The list of EventListeners.
     */
    public ArrayList<EventListener> getListeners() {
        return listeners;
    }
}
