package org.transitclock.detours.model;

public class Match {

	private double accumulatedDistance;
	private double distanceAlongSegmnt;
	private double distanceToSegment;
	private int matchIndex;
	private ApiStop nextStop;
	public ApiStop getNextStop() {
		return nextStop;
	}
	private ApiStop previousStop;
	public ApiStop getPreviousStop() {
		return previousStop;
	}
	public double getAccumulatedDistance() {
		return accumulatedDistance;
	}
	public void setAccumulatedDistance(double accumulatedDistance) {
		this.accumulatedDistance = accumulatedDistance;
	}
	public double getDistanceAlongSegmnt() {
		return distanceAlongSegmnt;
	}
	public void setDistanceAlongSegmnt(double distanceAlongSegmnt) {
		this.distanceAlongSegmnt = distanceAlongSegmnt;
	}
	public double getDistanceToSegment() {
		return distanceToSegment;
	}
	public void setDistanceToSegment(double distanceToSegment) {
		this.distanceToSegment = distanceToSegment;
	}
	public int getMatchIndex() {
		return matchIndex;
	}
	public void setMatchIndex(int matchIndex) {
		this.matchIndex = matchIndex;
	}
	
	@Override
	public String toString() {
		return "Match [accumulatedDistance=" + accumulatedDistance + ", distanceAlongSegmnt=" + distanceAlongSegmnt
				+ ", distanceToSegment=" + distanceToSegment + ", matchIndex=" + matchIndex + ", nextStop=" + nextStop
				+ ", previousStop=" + previousStop + "]";
	}
	public void setNextStop(ApiStop nextStop) {
		this.nextStop=nextStop;
		
	}
	public void setPreviousStop(ApiStop previousStop) {
		this.previousStop=previousStop;
		
	}
	
}
