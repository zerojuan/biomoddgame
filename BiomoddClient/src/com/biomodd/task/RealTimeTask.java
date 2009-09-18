package com.biomodd.task;

import com.biomodd.game.BiomoddGame;

public abstract class RealTimeTask extends Task implements IRealTimeTask{

	/**
	 * The time stamp of this <code>RealTimeTask</code>
	 */
	protected final long timestamp;
	/**
	 * Constructor of <code>RealTimeTask</code>
	 * @param enumn The <code>ETask</code> enumeration of this </code>Task</code>.
	 * @param game The <code>Game</code> instance.
	 */
	public RealTimeTask(ETask enumn, BiomoddGame game) {
		super(enumn, game);
		this.timestamp = System.currentTimeMillis();
	}
	@Override
	public boolean equals(Object o) {
		if(o instanceof RealTimeTask) {
			RealTimeTask task = (RealTimeTask)o;
			return this.enumn == task.enumn;
		}
		return false;
	}
	@Override
	public long getTimestamp() {
		return this.timestamp;
	}
	@Override
	public boolean isLaterThan(IRealTimeTask task) {
		// Consider equal to be later than.
		return (this.timestamp >= task.getTimestamp());
	}
}
