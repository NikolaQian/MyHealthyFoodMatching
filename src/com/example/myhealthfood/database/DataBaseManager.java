package com.example.myhealthfood.database;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseManager {
	
	//数据库路径
	private static final String DATABASE_PATH = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/FoodMatching/database";
	
	//图片路径
	private static final String PHOTO_PATH = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/FoodMatching/photo";
	
	//数据库名
	private static final String DATABASE_NAME = "foodlist.db";
		
	//数据表名
	private static final String TABLE_NAME = "foodlist";
	
	private SQLiteDatabase db;
	
	private Context context;
	
	public DataBaseManager(Context context){
		this.context = context;
		
		File databaseFile = new File(DATABASE_PATH);
		File photoFile = new File(PHOTO_PATH);
		
		if(!photoFile.exists()){
			photoFile.mkdirs();
		}
		
		if(!databaseFile.exists()){
			databaseFile.mkdirs();
		}
		
		this.db = SQLiteDatabase.openOrCreateDatabase(DATABASE_PATH + "/" + DATABASE_NAME, null);
		
		StringBuffer stringBuffer = new StringBuffer();
		
		stringBuffer.append("CREATE TABLE IF NOT EXISTS [" + TABLE_NAME + "] (");
		stringBuffer.append("[_id] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,");
		stringBuffer.append(" [name] TEXT NOT NULL,");
		stringBuffer.append(" [description] TEXT NOT NULL,");
		stringBuffer.append(" [cannotMatchFood] TEXT NOT NULL,");
		stringBuffer.append(" [cannotMatchFoodReason] TEXT,");
		stringBuffer.append(" [photoPath] TEXT)");
		
		db.execSQL(stringBuffer.toString());
	}
	
	public void addOne(Food food){
		db.execSQL("INSERT INTO" + TABLE_NAME + "VALUES(NULL,?,?,?,?,?)", new String[]{food.getName(), food.getDescription(),
						food.getCannotMatchFood(), food.getCannotMatchFoodReason(), food.getPhotoPath()});
	}
	
	public void addList(List<Food> lst){
		db.beginTransaction();
		
		for(Food food:lst){
			addOne(food);
		}
		
		db.setTransactionSuccessful();
		db.endTransaction();
	}
	
	public void delete(String name){
		db.delete(TABLE_NAME, "name=?", new String[]{name});
	}
	
	public void update(String name, Food food){
		ContentValues cv = new ContentValues();
		cv.put("name", food.getName());
		cv.put("description", food.getDescription());
		cv.put("cannotMatchFood", food.getCannotMatchFood());
		cv.put("cannotMatchFoodReason", food.getCannotMatchFoodReason());
		cv.put("photoPath", food.getPhotoPath());
		
		db.update(TABLE_NAME, cv, "name=?", new String[]{name});
	}
	
	public ArrayList<Food> query(String name){
		ArrayList<Food> lst = new ArrayList<Food>();
		Cursor c = db.rawQuery("SELECT  * FROM "+ TABLE_NAME +" where name like '%" + name + "%'", null);
		while(c.moveToNext()){
			Food food = new Food();
			food.set_id(c.getInt(c.getColumnIndex("_id")));
			food.setName(c.getString(c.getColumnIndex("name")));
			food.setDescription(c.getString(c.getColumnIndex("description")));
			food.setCannotMatchFood(c.getString(c.getColumnIndex("cannotMatchFood")));
			food.setCannotMatchFoodReason(c.getString(c.getColumnIndex("cannotMatchFoodReason")));
			food.setPhotoPath(PHOTO_PATH + "/" + c.getString(c.getColumnIndex("photoPath")) + ".jpg");
			lst.add(food);
		}
		
		return lst;
	}
	
	public ArrayList<Food> queryAll(){
		ArrayList<Food> lst = new ArrayList<Food>();
		Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
		while(c.moveToNext()){
			Food food = new Food();
			food.set_id(c.getInt(c.getColumnIndex("_id")));
			food.setName(c.getString(c.getColumnIndex("name")));
			food.setDescription(c.getString(c.getColumnIndex("description")));
			food.setCannotMatchFood(c.getString(c.getColumnIndex("cannotMatchFood")));
			food.setCannotMatchFoodReason(c.getString(c.getColumnIndex("cannotMatchFoodReason")));
			food.setPhotoPath(PHOTO_PATH + "/" + c.getString(c.getColumnIndex("photoPath")) + ".jpg");
			lst.add(food);
		}
		
		return lst;
	}
	
	public void close(){
		db.close();
	}
}
