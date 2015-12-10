package model;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class AccelerometerData {
	
	@Persistent
	String timeStamp;
	
	@Persistent
	float data;
	
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public float getData() {
		return data;
	}
	public void setData(float data) {
		this.data = data;
	}
	
	
}
