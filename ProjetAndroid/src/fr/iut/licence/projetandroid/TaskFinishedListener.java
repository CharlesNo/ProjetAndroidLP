package fr.iut.licence.projetandroid;

import android.location.Address;

/**
 * The listener interface for receiving taskFinished events.
 * The class that is interested in processing a taskFinished
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addTaskFinishedListener<code> method. When
 * the taskFinished event occurs, that object's appropriate
 * method is invoked.
 * 
 */
public interface TaskFinishedListener
{
	/**
	 * On task completed.
	 * 
	 * @param value
	 *            the value
	 */
	void onTaskCompleted(Address value);
}
