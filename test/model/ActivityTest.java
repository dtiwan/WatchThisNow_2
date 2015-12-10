package model;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import ninja.Results;

public class ActivityTest extends TestCase{

	public void testJSON(){
		Activity activity = new Activity();
		activity.startTimeStamp = ""+System.currentTimeMillis();
		activity.endTimeStamp = ""+System.currentTimeMillis()+1000;
		
		List<AccelerometerData> data = new ArrayList<AccelerometerData>();
		
		AccelerometerData datum = new AccelerometerData();
		datum.timeStamp = ""+System.currentTimeMillis();
		datum.data = 3.124f;
		data.add(datum);
		
		datum = new AccelerometerData();
		datum.timeStamp = ""+System.currentTimeMillis()+10;
		datum.data = 30.124f;
		data.add(datum);
		
		System.out.println(Results.json().render(activity));
	}
	
}
