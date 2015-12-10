package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import com.google.inject.Singleton;

import model.AccelerometerData;
import model.Activity;
import ninja.Result;
import ninja.Results;

@Singleton
public class RestController {

	public Result postActivity(Activity activity){
		PersistenceManager pmf = JDOHelper.getPersistenceManagerFactory("transactions-optional").getPersistenceManager();
		List<Activity> activities = new ArrayList<Activity>();
		try {
			pmf.makePersistent(activity);
			Extent<Activity>  e  = pmf.getExtent(Activity.class, true);
			for (Activity activity2 : e) {
				activities.add(activity2);
			}
		} finally{
			pmf.close();
		}
		return Results.json().render(activities);
	}
	
	public Result getActivity(){
		Activity activity = new Activity();
		activity.setStartTimeStamp( ""+System.currentTimeMillis());
		activity.setEndTimeStamp(""+System.currentTimeMillis()+1000);
		
		List<AccelerometerData> data = new ArrayList<AccelerometerData>();
		
		AccelerometerData datum = new AccelerometerData();
		datum.setTimeStamp( ""+System.currentTimeMillis());
		datum.setData(3.124f);
		data.add(datum);
		
		datum = new AccelerometerData();
		datum.setTimeStamp(""+System.currentTimeMillis()+10);
		datum.setData( 30.124f);
		data.add(datum);
		
		activity.setData(data);
		
		return Results.json().render(activity);

	}
}
