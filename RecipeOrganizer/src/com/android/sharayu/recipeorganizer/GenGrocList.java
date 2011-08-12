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

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

public class GenGrocList extends Activity{
	
	public static final String TABLE_NAME = "RecpOrg";
	public String Recipe_Name = new String();
	int x1=0;
	String string1 = new String();
	public void onCreate(Bundle SavedInstance){
		super.onCreate(SavedInstance);
		setContentView(R.layout.lookup);
		Bundle bundle = getIntent().getExtras();
		Recipe_Name = bundle.getString("RecipeName");
		getRecipe();
	}
  
	
	public void getRecipe()
	{
		
		DataHandler dh = new DataHandler(this); 
		SQLiteDatabase db1 = dh.getReadableDatabase();
		String sql = "select name,ingredients,procedure from RecpOrg where name ='" + Recipe_Name +"'";
		Cursor cursor = db1.rawQuery(sql, null);
		
		int x1=0;
		String string1 = new String();
		if(cursor.moveToFirst())
 		{
 			do	
 			{			
 				string1= "    " + cursor.getString(0)+ "\n"
 						+"==========================\n\n"
 				       + "INGREDIENTS:\n " + cursor.getString(1)+ "\n";
 				       
 				
 				x1=x1+1;
 			}while(cursor.moveToNext());
 		}
		TextView t1 = (TextView) findViewById(R.id.textView1);
		t1.setText(string1);
	 	
 		if(cursor != null && !cursor.isClosed())
 		{
 			cursor.close();
 		}
 		cursor.close();  
		
	}
}



