package daniel2.podice.com.weatherapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    private String[] projection = {
			Contract.PhotoEntry._ID,
            Contract.PhotoEntry.CITY,
            Contract.PhotoEntry.COUNTRY,
            Contract.PhotoEntry.HUMIDITY,
            Contract.PhotoEntry.DEGREE,
            Contract.PhotoEntry.MAIN,
			Contract.PhotoEntry.DESCRIPTION,};


/*
    private String[] projection = {
            Contract.PhotoEntry._ID,
            Contract.PhotoEntry.FAMILY,
            Contract.PhotoEntry.FRIEND,
            Contract.PhotoEntry.FARM,
            Contract.PhotoEntry.TITLE,
            Contract.PhotoEntry.SECRET,
            Contract.PhotoEntry.OWNER,
            Contract.PhotoEntry.PUBLIC,
            Contract.PhotoEntry.SERVER,};
*/
			

    private static final String DATABASE_CREATE =
            "CREATE TABLE " +
					
                    Contract.PhotoEntry.TABLE_NAME + " (" +
					
					
                    Contract.PhotoEntry._ID + " INTEGER PRIMARY KEY, " +
					Contract.PhotoEntry.CITY + " TEXT NOT NULL, " +
					Contract.PhotoEntry.COUNTRY + " TEXT NOT NULL, " +
					Contract.PhotoEntry.HUMIDITY + " TEXT NOT NULL, " +
					Contract.PhotoEntry.DEGREE + " TEXT NOT NULL, " +
					Contract.PhotoEntry.MAIN + " TEXT NOT NULL, " +
					Contract.PhotoEntry.DESCRIPTION + " INTEGER NOT NULL " + ")";

					
					
/*					
                    Contract.PhotoEntry.OWNER + " TEXT NOT NULL, " +
                    Contract.PhotoEntry.SECRET + " TEXT NOT NULL, " +
                    Contract.PhotoEntry.SERVER + " TEXT NOT NULL, " +
                    Contract.PhotoEntry.FARM + " TEXT NOT NULL, " +
                    Contract.PhotoEntry.TITLE + " TEXT NOT NULL, " +
                    Contract.PhotoEntry.PUBLIC + " INTEGER NOT NULL, " +
                    Contract.PhotoEntry.FRIEND + " INTEGER NOT NULL, " +
                    Contract.PhotoEntry.FAMILY + " INTEGER NOT NULL " + ")";
*/
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Contract.PhotoEntry.TABLE_NAME;

    public DataBaseHelper(Context context) {
        super(context, Contract.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(Constants.TAG,DATABASE_CREATE);
        Log.i(Constants.TAG, "Create table command: " + DATABASE_CREATE);
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
    }

    public void insertPhotoEntry(WeatherInfo info) {
        SQLiteDatabase db = getWritableDatabase();
         ContentValues cv = new ContentValues();

        cv.put(Contract.PhotoEntry._ID, info.getdt());
		cv.put(Contract.PhotoEntry.CITY, info.getCityName());
		cv.put(Contract.PhotoEntry.COUNTRY, info.getCountryName());
		cv.put(Contract.PhotoEntry.HUMIDITY, info.getHumidity());
		cv.put(Contract.PhotoEntry.DEGREE, info.getDegree());
		cv.put(Contract.PhotoEntry.MAIN, info.getMain());
		cv.put(Contract.PhotoEntry.DESCRIPTION, info.getDescription());
        Log.i(Constants.TAG, "TableID: " + Contract.PhotoEntry._ID);
//        Log.i(Constants.TAG, "DatabaseHelperMain: " + info.getMain());
        db.insert(Contract.PhotoEntry.TABLE_NAME, null, cv);

/*
					Contract.PhotoEntry._ID + " INTEGER PRIMARY KEY, " +
					Contract.PhotoEntry.CITY + " TEXT NOT NULL, " +
					Contract.PhotoEntry.COUNTRY + " TEXT NOT NULL, " +
					Contract.PhotoEntry.HUMIDITY + " TEXT NOT NULL, " +
					Contract.PhotoEntry.DEGREE + " TEXT NOT NULL, " +
					Contract.PhotoEntry.MAIN + " TEXT NOT NULL, " +
					Contract.PhotoEntry.DESCRIPTION + " INTEGER NOT NULL " + ")";

		
        cv.put(Contract.PhotoEntry.OWNER, info.getOwner());
        cv.put(Contract.PhotoEntry.SECRET, info.getSecret());
        cv.put(Contract.PhotoEntry.SERVER, info.getServer());
        cv.put(Contract.PhotoEntry.FARM, info.getFarm());
        cv.put(Contract.PhotoEntry.TITLE, info.getTitle());
        cv.put(Contract.PhotoEntry.FAMILY, info.getIsFamily() ? 1 : 0);
        cv.put(Contract.PhotoEntry.PUBLIC, info.getIsPublic() ? 1 : 0);
        cv.put(Contract.PhotoEntry.FRIEND, info.getIsFriend() ? 1 : 0);
*/

        db.insert(Contract.PhotoEntry.TABLE_NAME, null, cv);
    }

    public Cursor getAllRows() {
        SQLiteDatabase db = getReadableDatabase();

        Log.i(Constants.TAG, "Made it to the getAllRows Category");

        return db.query(Contract.PhotoEntry.TABLE_NAME, projection, null, null, null, null, null);

//        Here's the method with arguments:
//        public Cursor query (String table, String[] columns, String selection, String[]
//        selectionArgs, String groupBy, String orderBy, String limit)

    }

    public Cursor getRowByID(long id) {
        SQLiteDatabase db = getReadableDatabase();
        String[] ids = {String.valueOf(id)};
        return db.query(Contract.PhotoEntry.TABLE_NAME, projection, Contract.PhotoEntry._ID + "==?", ids, null, null, null);
    }

    public void deleteRow(long id) {
        SQLiteDatabase db = getWritableDatabase();
        String[] ids = {String.valueOf(id)};
        db.delete(Contract.PhotoEntry.TABLE_NAME, Contract.PhotoEntry._ID + "==?", ids);
    }

    public void addRows(List<WeatherInfo> photos) {
        for (WeatherInfo photo : photos) {
            insertPhotoEntry(photo);
        }
    }

    public void clearTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from " + Contract.PhotoEntry.TABLE_NAME);
    }

    public void dropTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(SQL_DELETE_ENTRIES);
    }


}
