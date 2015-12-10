package model;

import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class Activity {
	
	@Persistent
	String startTimeStamp;
	
	@Persistent
	String endTimeStamp;
	
	@Persistent
	String activeStatus;
	
	@Persistent
	List<AccelerometerData> data;
	public String getStartTimeStamp() {
		return startTimeStamp;
	}
	public void setStartTimeStamp(String startTimeStamp) {
		this.startTimeStamp = startTimeStamp;
	}
	public String getEndTimeStamp() {
		return endTimeStamp;
	}
	public void setEndTimeStamp(String endTimeStamp) {
		this.endTimeStamp = endTimeStamp;
	}
	public List<AccelerometerData> getData() {
		return data;
	}
	public void setData(List<AccelerometerData> data) {
		this.data = data;
	}
	
	public String getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
	
}
