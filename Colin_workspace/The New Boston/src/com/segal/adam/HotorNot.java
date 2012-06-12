package com.segal.adam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater.Factory;

public class HotorNot {
	
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "persons_name";
	public static final String KEY_HOTNESS = "persons_hotness";
	
	private static final String DATABASE_NAME = "HotOrNotDb";
	private static final String DATABASE_TABLE = "peopleTable";
	private static final int DATABASE_VERSION = 1;
	private static final CursorFactory DATABASE_FACTORY = null;
	
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, DATABASE_FACTORY, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}
		

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" 
					+ KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
					+ KEY_NAME + " TEXT NOT NULL, "
					+ KEY_HOTNESS + " TEXT NOT NULL);"
					);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
		
	}
	public HotorNot(Context c){
		ourContext = c;
	}
	
	public HotorNot open() throws SQLException {
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		ourHelper.close();
	}

	public long createEntry(String name, String hotness) {
		// TODO Auto-generated method stub
		ContentValues cv =  new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_HOTNESS, hotness);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

	public String getData() {
		String[] columns = new String[] {KEY_ROWID,KEY_NAME,KEY_HOTNESS};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = "";
		
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iName = c.getColumnIndex(KEY_NAME);
		int iHotness = c.getColumnIndex(KEY_HOTNESS);
		
		for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			result =  result + c.getString(iRow) + " " + c.getString(iName) + " " + c.getString(iHotness) + "\n";
		}
		
		return result;
	}

	public String getName(long l) {
		String[] columns = new String[] {KEY_ROWID,KEY_NAME,KEY_HOTNESS};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns,KEY_ROWID + "=" + l , null, null, null, null);
		if (c != null){
			c.moveToFirst();
			String name = c.getString(1);
			return name;
		}
		return null;
	}

	public String getHotness(long l) {
		String[] columns = new String[] {KEY_ROWID,KEY_NAME,KEY_HOTNESS};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns,KEY_ROWID + "=" + l , null, null, null, null);
		if (c != null){
			c.moveToFirst();
			String hotness = c.getString(2);
			return hotness;
		}
		return null;
	}

	public void updateEntrey(long l, String name, String hotness) {
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_NAME,name);
		cvUpdate.put(KEY_HOTNESS,hotness);
		ourDatabase.update(DATABASE_TABLE,cvUpdate, KEY_ROWID + '=' + l,null);		
	}
	
}
