package daniel2.podice.com.weatherapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class WeatherInfo {
    private String dt, humidity, degree, id, main, description;
	private static String cityName, countryName;

    public WeatherInfo(JSONObject jsonEntry) throws JSONException {
        this.dt = (String) jsonEntry.optString("dt");
		this.humidity = (String) jsonEntry.optString("humidity");

        //May Have To Change this


		this.degree = (String) jsonEntry.optString("degree");

        Log.i(Constants.TAG, "Humidity: " + humidity);


//        String weather = (String) jsonEntry.optString("weather");


        JSONObject m =jsonEntry.getJSONArray("weather").getJSONObject(0);
        this.main = m.getString("main");
        this.description = m.getString("description");


        Log.i(Constants.TAG, "Description" + description);

		
//		this.id = (String) weather.optString("id");


		//********************************************
		
    }

    public static ArrayList<WeatherInfo> makeWeatherList(String photoData) throws JSONException {
        ArrayList<WeatherInfo> flickrPhotos = new ArrayList<>();
		

        JSONObject data = new JSONObject(photoData);
//        JSONObject photos = data.optJSONObject("photos");
//        JSONArray photoArray = photos.optJSONArray("photo");


//May Have to Change
//******************************************
		JSONObject cityInfo = data.optJSONObject("city");
		
		cityName = (String) cityInfo.optString("name");
        Log.i(Constants.TAG, "City Name: " + cityName);
		countryName = (String) cityInfo.optString("country");
		
//****************************************
		
		JSONArray listArray = data.optJSONArray("list");

        for (int i = 0; i < listArray.length(); i++) {
            JSONObject info = (JSONObject) listArray.get(i);
            WeatherInfo currentInfo = new WeatherInfo(info);
            flickrPhotos.add(currentInfo);
        }
        return flickrPhotos;
    }
	
	
//May Not Need the Get URL
//    public static String getURL(String farm, String server, String id, String secret, boolean big) {
//        String opt = "n";
//        if (big) {
//            opt = "c";
//        }
//        String photoURI = "http://farm" + farm + ".staticflickr.com/" + server + "/"
//                + id + "_" + secret + "_" + opt + ".jpg";
//        Log.i(Constants.TAG, "Photo url: " + photoURI);
//        return photoURI;
//    }

	
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
    public String getdt() {
        return dt;
    }

    public void setdt(String dt) {
        this.dt = dt;
    }
	

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public String getCityName() {
        return cityName;
    }

    public void setCityName(String newName) {
        this.cityName = newName;
    }
	
	public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String newName) {
        this.countryName = newName;
    }
//    public String getPhotoURL(Boolean big) {
//        String opt = "n";
//        if (big) {
//            opt = "c";
//        }
//        String photoURI = "http://farm" + this.farm + ".staticflickr.com/" + this.server + "/"
//                + this.id + "_" + this.secret + "_" + opt + ".jpg";
//        Log.i(Constants.TAG, "Photo url: " + photoURI);
//        return photoURI;
//    }


}
