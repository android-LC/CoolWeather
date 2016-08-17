package com.coolweather.app.db;

import java.util.ArrayList;
import java.util.List;

import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CoolWeatherDB {

	/**
	 * @Fields DB_NAME : ���ݿ���
	 */
	public static final String DB_NAME = "cool_weather";
	/**
	 * @Fields VERSION : ���ݿ�汾
	 */
	public static final int VERSION = 1;
	public static CoolWeatherDB coolWeatherDB;
	private SQLiteDatabase db;

	/**
	 * @Title: CoolWeatherDB
	 * @Description: �����췽��˽�л�
	 * @author lxp
	 * @date 2016��4��30�� ����4:50:55
	 * @throws
	 */
	private CoolWeatherDB(Context context) {
		CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();
		// db=SQLiteDatabase.openOrCreateDatabase("/data/data/com.lingdududu.db/databases/stu.db",null);
		// dbHelper.deleteDatabase(context,DB_NAME);
	}

	/**
	 * @Title: getInstance
	 * @Description: ��ȡCoolWeatherDB��ʵ��
	 * @author lxp
	 * @date 2016��4��30�� ����4:53:06
	 * @return CoolWeatherDB ��������
	 * @throws
	 */
	public synchronized static CoolWeatherDB getInstance(Context context) {
		if (coolWeatherDB == null) {
			coolWeatherDB = new CoolWeatherDB(context);
		}
		return coolWeatherDB;
	}

	/**
	 * @Title: saveProvince
	 * @Description: ��Provinceʵ���洢�����ݿ�
	 * @author lxp
	 * @date 2016��4��30�� ����5:02:34
	 * @return void ��������
	 * @throws
	 */
	public void saveProvince(Province province) {
		if (province != null) {
			ContentValues values = new ContentValues();
			values.put("province_name", province.getProvinceName());
			values.put("province_code", province.getProvinceCode());
			db.insert("Province", null, values);
		}
	}

	/**
	 * @Title: loadProvinces
	 * @Description: �����ݿ��ȡȫ�����е�ʡ����Ϣ
	 * @author lxp
	 * @date 2016��4��30�� ����5:11:37
	 * @return List<Province> ��������
	 * @throws
	 */
	public List<Province> loadProvinces() {
		List<Province> list = new ArrayList<Province>();
		Cursor cursor = db.query("Province", null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Province province = new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
				province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
				list.add(province);
			} while (cursor.moveToNext());
		}
		if (cursor != null) {
			cursor.close();
		}
		return list;
	}

	/**
	 * @Title: saveCity
	 * @Description: ��Cityʵ���洢�����ݿ�
	 * @author lxp
	 * @date 2016��4��30�� ����5:24:47
	 * @return void ��������
	 * @throws
	 */
	public void saveCity(City city) {
		if (city != null) {
			ContentValues values = new ContentValues();
			values.put("city_name", city.getCityName());
			values.put("city_code", city.getCityCode());
			values.put("province_id", city.getProvinceId());
			db.insert("City", null, values);
		}
	}

	/**
	 * @Title: loadCities
	 * @Description: �����ݿ��ȡĳʡ�����еĳ�����Ϣ
	 * @author lxp
	 * @date 2016��4��30�� ����5:29:56
	 * @return List<City> ��������
	 * @throws
	 */
	public List<City> loadCities(int provinceId) {
		List<City> list = new ArrayList<City>();
		Cursor cursor = db.query("City", null, "province_id = ?", new String[] { String.valueOf(provinceId) }, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				City city = new City();
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
				city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
				city.setProvinceId(provinceId);
				list.add(city);
			} while (cursor.moveToNext());
		}
		if (cursor != null) {
			cursor.close();
		}
		return list;
	}

	/**
	 * @Title: saveCounty
	 * @Description: ��Countyʵ���洢�����ݿ�
	 * @author lxp
	 * @date 2016��4��30�� ����5:35:00
	 * @return void ��������
	 * @throws
	 */
	public void saveCounty(County county) {
		if (county != null) {
			ContentValues values = new ContentValues();
			values.put("county_name", county.getCountyName());
			values.put("county_code", county.getCountyCode());
			values.put("city_id", county.getCityId());
			db.insert("County", null, values);
		}
	}

	/**
	 * @Title: loadcounties
	 * @Description: �����ݿ��ȡĳ�����µ����е��ص���Ϣ
	 * @author lxp
	 * @date 2016��4��30�� ����5:35:27
	 * @return List<County> ��������
	 * @throws
	 */
	public List<County> loadcounties(int cityId) {
		List<County> list = new ArrayList<County>();
		Cursor cursor = db.query("County", null, "city_id = ?", new String[] { String.valueOf(cityId) }, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				County county = new County();
				county.setId(cursor.getInt(cursor.getColumnIndex("id")));
				county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
				county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
				county.setCityId(cityId);
				list.add(county);
			} while (cursor.moveToNext());
		}
		if (cursor != null) {
			cursor.close();
		}
		return list;
	}
}
