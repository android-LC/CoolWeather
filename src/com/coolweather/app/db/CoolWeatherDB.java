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
	 * @Fields DB_NAME : 数据库名
	 */
	public static final String DB_NAME = "cool_weather";
	/**
	 * @Fields VERSION : 数据库版本
	 */
	public static final int VERSION = 1;
	public static CoolWeatherDB coolWeatherDB;
	private SQLiteDatabase db;

	/**
	 * @Title: CoolWeatherDB
	 * @Description: 将构造方法私有化
	 * @author lxp
	 * @date 2016年4月30日 下午4:50:55
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
	 * @Description: 获取CoolWeatherDB的实例
	 * @author lxp
	 * @date 2016年4月30日 下午4:53:06
	 * @return CoolWeatherDB 返回类型
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
	 * @Description: 将Province实例存储到数据库
	 * @author lxp
	 * @date 2016年4月30日 下午5:02:34
	 * @return void 返回类型
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
	 * @Description: 从数据库读取全国所有的省份信息
	 * @author lxp
	 * @date 2016年4月30日 下午5:11:37
	 * @return List<Province> 返回类型
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
	 * @Description: 将City实例存储到数据库
	 * @author lxp
	 * @date 2016年4月30日 下午5:24:47
	 * @return void 返回类型
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
	 * @Description: 从数据库读取某省下所有的城市信息
	 * @author lxp
	 * @date 2016年4月30日 下午5:29:56
	 * @return List<City> 返回类型
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
	 * @Description: 将County实例存储到数据库
	 * @author lxp
	 * @date 2016年4月30日 下午5:35:00
	 * @return void 返回类型
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
	 * @Description: 从数据库读取某城市下的所有的县的信息
	 * @author lxp
	 * @date 2016年4月30日 下午5:35:27
	 * @return List<County> 返回类型
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
