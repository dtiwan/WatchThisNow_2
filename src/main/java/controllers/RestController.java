package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.inject.Singleton;

import model.Activity;
import ninja.Result;
import ninja.Results;

@Singleton
public class RestController {

	private static Logger logger = Logger.getLogger(RestController.class.getName());
	
	public Result postActivity(Activity activity){
		PersistenceManager pmf = JDOHelper.getPersistenceManagerFactory("transactions-optional").getPersistenceManager();
		List<Activity> activities = new ArrayList<Activity>();
		try {
			pmf.makePersistent(activity);
			Extent<Activity>  e  = pmf.getExtent(Activity.class, true);
			for (Activity activity2 : e) {
				activities.add(activity2);
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Caught exception in get rest", e);
		} finally{
			pmf.close();
		}
		return Results.json().render(activities);
	}
	
	public Result getActivity(){
		PersistenceManager pmf = JDOHelper.getPersistenceManagerFactory("transactions-optional").getPersistenceManager();
		Activity activity = new Activity();
		try {
			Query maxSTS = pmf.newQuery("SELECT max(startTimeStamp) FROM model.Activity");
			String maxSTSActivity = (String) maxSTS.execute();
			logger.log(Level.FINE, "maxSTS = " + maxSTSActivity);
			if(maxSTSActivity != null && !maxSTSActivity.isEmpty()) {
				Query query = pmf.newQuery(Activity.class); //"SELECT activeStatus FROM model.Activity WHERE startTimeStamp == "
				query.setFilter("startTimeStamp==maxSTSActivity");
				query.declareParameters("String maxSTSActivity");
				List<Activity> results = (List<Activity>) query.execute(maxSTSActivity);
				logger.log(Level.FINE, results.toString());
				if (results != null && results.size() > 0) {
					logger.log(Level.INFO, results.get(0).toString());
					activity = results.get(0);
				}
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Caught exception in get rest", e);
		} finally{
			pmf.close();
		}
		
		return Results.json().render(activity);

	}
}
