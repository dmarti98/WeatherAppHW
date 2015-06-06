package daniel2.podice.com.weatherapp;

import android.provider.BaseColumns;

//String id, owner, secret, server, farm, title;
//        Boolean isPublic, isFriend, isFamily;

public class Contract{
    public static final String DATABASE_NAME = "weatherapp.db";
    PhotoEntry pe = new PhotoEntry();

    public static final class PhotoEntry implements BaseColumns{
        public int test = 7;

//        public static final String _ID = "_id";

		public static final String TABLE_NAME = "weather_entry";
		public static final String CITY = "city";
		public static final String COUNTRY = "country";
		public static final String HUMIDITY = "humidity";
		public static final String DEGREE = "degree";
		public static final String MAIN = "main";
		public static final String DESCRIPTION = "description";

//		public static final String _ID = "id";

		
		
//        public static final String TABLE_NAME = "photo_entry";
//        public static final String OWNER = "owner";
//        public static final String SECRET = "secret";
//        public static final String SERVER = "server";
//        public static final String FARM = "farm";
//        public static final String TITLE = "title";
//        public static final String PUBLIC = "public";
//        public static final String FRIEND = "friend";
//        public static final String FAMILY = "family";

    }

}
