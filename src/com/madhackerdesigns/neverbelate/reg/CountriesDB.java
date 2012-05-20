package com.madhackerdesigns.neverbelate.reg;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class CountriesDB extends SQLiteAssetHelper {
	
	private static final String DATABASE_NAME = "countries";
	private static final int DATABASE_VERSION = 1;
	
	protected static final String ID = "_id";
	protected static final String NAME = "name";
	protected static final String CODE = "code";
	protected static final String ZIP = "zip";
	protected static final String[] PROJECTION = { ID, NAME, CODE, ZIP };
	protected static final int PROJ_ID = 0;
	protected static final int PROJ_NAME = 1;
	protected static final int PROJ_CODE = 2;
	protected static final int PROJ_ZIP = 3;

	public CountriesDB(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);	
	}

	public Cursor getCountries() {
		return getCountries(null, null);
	}
	
	public Cursor getCountries(String selection, String[] selectionArgs) {		
		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		String [] sqlSelect = PROJECTION; 
		String sqlTables = "countries";

		qb.setTables(sqlTables);
		Cursor c = qb.query(db, sqlSelect, selection, selectionArgs,
				null, null, null);

		return c;
	}
	
	public Cursor getCountryByCode(String code) {
		return getCountries(CODE + "=?", new String[] { code });
	}
}
