/**********************************************************************************
* Author : Sharayu Jadhav
* This .java file is part of the project Recipe Organizer for OSS (cs510) summer 2011 class project. 
**/

/**********************************************************************************************************
 *  Copyright(c) 2011 Recipe Organizer is free software: you can redistribute 
 * it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, 
 *either version 3 of the License, or(at your option) any later version.Recipe Organizer is distributed in the hope that 
 * it will be useful,but WITHOUT ANY WARRANTY; without even the implied warranty ofMERCHANTABILITY or FITNESS FOR A 
 * PARTICULAR PURPOSE. See theGNU General Public License for more details.You should have received a copy of the GNU 
 * General Public License along with Recipe Organizer. If not, see <http://www.gnu.org/licenses/>.
 * 
 * Contact Info:
 * Author:Sharayu Jadhav.
 * For feedback please email at sharayuj4u@gmail.com.           
*********************************************************************************************************/
package com.android.sharayu.recipeorganizer;

import java.io.FileOutputStream;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHandler extends SQLiteOpenHelper{

	private static String DB_PATH = "/data/data/com.android.sharayu.recipeorganizer/databases/";
 
    private static String DB_NAME = "testdb3.db";
    
    public static final int DB_VERSION = 1;
 
    private SQLiteDatabase myDataBase; 
 
    private final Context myContext3;
    
    public DataHandler(Context context) {
    	 
    	super(context, DB_NAME, null, DB_VERSION);
        this.myContext3 = context;
    }
    
    public void openDataBase() throws SQLException{
    	 
    	//Open the database
        String myPath = DB_PATH + DB_NAME;
    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    }
    
    @Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL(	"create table RecpOrg (_id integer primary key autoincrement,name text,serves integer, level text, time_req TIME,ingredients text,procedure text);");
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		android.util.Log.w("RecpOrg",
		"Upgrading database, which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS RecpOrg");
		onCreate(db);
 
	}
    
}
