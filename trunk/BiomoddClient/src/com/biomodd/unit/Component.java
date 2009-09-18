package com.biomodd.unit;

import com.biomodd.exception.MissingComponentException;

public abstract class Component implements IComponent{
	/**
	 * The flag indicates the activeness of this <code>Component</code>.
	 */
	private boolean active;

	@Override
	public void activate() throws MissingComponentException {
		if(this.validate()) {
			this.active = true;
			this.initialize();
		}
	}

	@Override
	public void deactivate() {
		this.active = false;
	}

	@Override
	public boolean isActive() {
		return this.active;
	}
}
