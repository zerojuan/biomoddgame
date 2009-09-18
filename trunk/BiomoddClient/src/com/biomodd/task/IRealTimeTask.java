package com.biomodd.task;

public interface IRealTimeTask extends ITask{
	/**
	 * Check if the given object is the same as this real time task.
	 * @param object The <code>Object</code> to check against.
	 * @return True if the given object is the same as this task. False otherwise.
	 */
	public boolean equals(Object object);

	/**
	 * Retrieve the time stamp of this real time task.
	 * @return The time stamp of this <code>IRealTimeTask</code>.
	 */
	public long getTimestamp();

	/**
	 * Compare the construction time stamp of this task with the given one.
	 * @param task The <code>IRealTimeTask</code> to be compared with.
	 * @return True if this task is constructed later than the given one.
	 */
	public boolean isLaterThan(IRealTimeTask task);
}
