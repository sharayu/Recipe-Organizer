/**********************************************************************************
* Author : Sharayu Jadhav
* This .java file is part of the project Recipe Organizer for OSS (cs510) summer 2011 class project. 
**/

/**********************************************************************************************************
 *  Copyright(c) 2011 Recipe Organizer is free software: you can redistribute 
 * it and/or modify it under the terms of the GNU General Public License as published bythe Free Software Foundation, 
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
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class EditRecp extends Activity{
	
	public static final String TABLE_NAME = "RecpOrg";
	public String Recipe_Name = new String();
	int x1=0;
	String string1 = new String();
	public void onCreate(Bundle SavedInstance){
		super.onCreate(SavedInstance);
		setContentView(R.layout.edit);
		Bundle bundle = getIntent().getExtras();
		Recipe_Name = bundle.getString("RecipeName");
		getRecipe();
	}
  
	
	public void getRecipe()
	{
		
		DataHandler dh = new DataHandler(this); 
		SQLiteDatabase db1 = dh.getReadableDatabase();
		String sql = "select name,serves,level,time_req,ingredients,procedure from RecpOrg where name ='" + Recipe_Name+ "'";
		Cursor cursor = db1.rawQuery(sql, null);
		
		int x1=0;
		String Recp[] = new String[6];
		if(cursor.moveToFirst())
 		{
 			do	
 			{			
 				Recp[0]= cursor.getString(0);
 				Recp[1]= cursor.getString(1);
 				Recp[2]= cursor.getString(2);
 				Recp[3]= cursor.getString(3);
 				Recp[4]= cursor.getString(4);
 				Recp[5]= cursor.getString(5);
 				       
 				
 				x1=x1+1;
 			}while(cursor.moveToNext());
 		}
 		
		AutoCompleteTextView act1 = (AutoCompleteTextView) findViewById(R.id.eRecipeName);
	 	act1.setText(Recp[0]);
	 	AutoCompleteTextView act2 = (AutoCompleteTextView) findViewById(R.id.eServes);
	 	act2.setText(Recp[1]);
	 	AutoCompleteTextView act5 = (AutoCompleteTextView) findViewById(R.id.eLevel);
	 	act5.setText(Recp[2]);
	 	AutoCompleteTextView act6 = (AutoCompleteTextView) findViewById(R.id.eTimeReq);
	 	act6.setText(Recp[3]);
	 	AutoCompleteTextView act3 = (AutoCompleteTextView) findViewById(R.id.eIngredients);
	 	act3.setText(Recp[4]);
	 	AutoCompleteTextView act4 = (AutoCompleteTextView) findViewById(R.id.eProcedure);
	 	act4.setText(Recp[5]);
	 	
 		if(cursor != null && !cursor.isClosed())
 		{
 			cursor.close();
 		}
 		cursor.close(); 
 		db1.close();
		
	}
	
	public void OnClickUpdateRecipe(View v){
		AutoCompleteTextView RecipeName = (AutoCompleteTextView) findViewById(R.id.eRecipeName);
		AutoCompleteTextView Serves = (AutoCompleteTextView) findViewById(R.id.eServes);
		AutoCompleteTextView Level = (AutoCompleteTextView) findViewById(R.id.eLevel);
		AutoCompleteTextView TimeReq = (AutoCompleteTextView) findViewById(R.id.eTimeReq);
		AutoCompleteTextView Ingredients = (AutoCompleteTextView) findViewById(R.id.eIngredients);
		AutoCompleteTextView Procedure = (AutoCompleteTextView) findViewById(R.id.eProcedure);
		
		String recipe[] = new String[6];
		recipe[0] = RecipeName.getText().toString();
		recipe[1] = Serves.getText().toString();
		recipe[2] = Level.getText().toString();
		recipe[3] = TimeReq.getText().toString();
		recipe[4] = Ingredients.getText().toString();
		recipe[5] = Procedure.getText().toString();
		
			DataHandler dh = new DataHandler(this); 
		dh.openDataBase();
 		SQLiteDatabase db1 = dh.getWritableDatabase();
 		 
 		
 		ContentValues dataToInsert = new ContentValues(); 
 		dataToInsert.put("serves", recipe[1]);
 		dataToInsert.put("time_req", recipe[3]);
 		dataToInsert.put("ingredients", recipe[4]);
 		dataToInsert.put("procedure", recipe[5]);
 		
 		String where = "name=?";
 		String[] whereArgs = {recipe[0]};
 		db1.update("RecpOrg", dataToInsert, where, whereArgs);
 		
		db1.close();
		
		Intent IntentAdd = new Intent();
    	IntentAdd.setClass(this,AddRecipe.class);
    	startActivity(IntentAdd);   
	}
}



