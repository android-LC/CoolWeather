package com.coolweather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class CoolWeatherOpenHelper extends SQLiteOpenHelper {

	/**
	 * @Fields CREATE_PROVINCE : Province����
	 */
	public static final String CREATE_PROVINCE = "create table Province(id integer primary key autoincrement,province_name text,province_code text)";

	/**
	 * @Fields CREATE_CITY : city����
	 */
	public static final String CREATE_CITY = "create table City(id integer primary key autoincrement,city_name text,city_code text,province_id integer)";
	/**
	 * @Fields CREATE_COUNTY : county����
	 */
	public static final String CREATE_COUNTY = "create table County(id integer primary key autoincrement,county_name text,county_code text,city_id integer)";

	public CoolWeatherOpenHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_PROVINCE);// ����province��
		db.execSQL(CREATE_CITY);// ����city��
		db.execSQL(CREATE_COUNTY);// ����county��
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public boolean deleteDatabase(Context context, String name) {
		return context.deleteDatabase(name);
	}
}
