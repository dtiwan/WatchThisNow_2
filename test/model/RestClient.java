package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class RestClient {
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://game-chaat.appspot.com/activity/postActivity.json");
		
		Activity activity = new Activity();
		activity.startTimeStamp = ""+System.currentTimeMillis();
		activity.endTimeStamp = ""+System.currentTimeMillis()+1000;
		activity.setActiveStatus("active");
		
		List<AccelerometerData> data = new ArrayList<AccelerometerData>();
		
		AccelerometerData datum = new AccelerometerData();
		datum.timeStamp = ""+System.currentTimeMillis();
		datum.data = 3.124f;
		data.add(datum);
		
		datum = new AccelerometerData();
		datum.timeStamp = ""+System.currentTimeMillis()+10;
		datum.data = 30.124f;
		data.add(datum);
		
		activity.setData(data);
		
		JSONObject json = new JSONObject();
		try {
			json.put("startTimeStamp", activity.getStartTimeStamp());
		} catch (JSONException e) {
			System.out.println( " Found error in starTimeStamp " + e);
		}
		try {
			json.put("endTimeStamp", activity.getEndTimeStamp());
		} catch (JSONException e) {
			System.out.println(  "Found error in endTimeStamp " + e);
		}
		try {
			
			JSONArray array = new JSONArray();
			for (AccelerometerData accelerometerData : activity.getData()) {
				JSONObject obj = new JSONObject();
				obj.put("timeStamp", accelerometerData.getTimeStamp());
				obj.put("data", accelerometerData.getData());
				array.put(obj);
			}
			json.put("data", array);
		} catch (JSONException e) {
			System.out.println(  "Found error in data " + e);
		}

		String jsonString = json.toString();
		System.out.println(jsonString);
		StringEntity entity = new StringEntity(jsonString);
		
		/*entity = new StringEntity("{\"startTimeStamp\":\"1449704841437\","
				+ "\"endTimeStamp\":\"14497048414371000\","
				+ "\"data\":[{\"timeStamp\":\"1449704841437\",\"data\":3.124},"
				+ "{\"timeStamp\":\"144970484143710\",\"data\":30.124}]}");*/
		
		entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		post.setEntity(entity);

		HttpResponse response = client.execute(post);
		
		/*if (response.getStatusLine().getStatusCode() != 201) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatusLine().getStatusCode());
		}*/

		BufferedReader br = new BufferedReader(
                        new InputStreamReader((response.getEntity().getContent())));

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}

		client.getConnectionManager().shutdown();
		

	}

}
