package org.transitclock.core;

import org.transitclock.db.structs.Block;

public class SpatialMatch {
	protected final long avlTime;
	protected final Block block;
	protected final int tripIndex;
	
	public SpatialMatch(long avlTime, Block block, int tripIndex) {
		super();
		this.avlTime = avlTime;
		this.block = block;
		this.tripIndex = tripIndex;
	}
	public long getAvlTime() {
		return avlTime;
	}
	public Block getBlock() {
		return block;
	}
	public int getTripIndex() {
		return tripIndex;
	}
	
}
