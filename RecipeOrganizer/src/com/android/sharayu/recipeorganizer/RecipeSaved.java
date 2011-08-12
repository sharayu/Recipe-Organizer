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

import java.io.IOException;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.util.Log;

public class RecipeSaved extends Activity{
	
	long val;
	
	//public  static int dcounter = 6;
	public void onCreate(Bundle SavedInstance){
		super.onCreate(SavedInstance);
		setContentView(R.layout.saved);
		
		TextView tv1 = (TextView) findViewById(R.id.resultText);
		Bundle bundle = getIntent().getExtras();
		
		if(bundle.getString("Cancel") != null)
		{
			tv1.setText("could not insert values");
		}
		else
		{
			
			String Recipe_Name = bundle.getString("Recipe_Name");
			String Serves = bundle.getString("Serves");
			String Level = bundle.getString("Level");
			String TimeReq = bundle.getString("TimeReq");
			String Ingredients = bundle.getString("Ingredients");
			String Procedure = bundle.getString("Procedure");
			
			
			
			
			insert_record(Recipe_Name,Serves,Level,TimeReq,Ingredients,Procedure);
			tv1.setText("Action performed successfully.\n\n Would you like to add another recipe or lookup recipes?\n");
			
		}
		
	}
	private void insert_record(String name, String serves,String level,String timereq,String ingredients,String procedure)
	{
		
		DataHandler dh = new DataHandler(this); 
	
		dh.openDataBase();
 		SQLiteDatabase db1 = dh.getWritableDatabase();
 		
 
 				
 			 ContentValues cv1 = new ContentValues();
 	 		 

 		 	cv1.put("name",name); 
 		 	cv1.put("serves",serves);
 		 	cv1.put("level",level);
 		 	cv1.put("time_req",timereq);
 			cv1.put("ingredients",ingredients);
 			cv1.put("procedure",procedure);
 			
 			val = db1.insert("RecpOrg", "name", cv1); 
 
 			db1.close();  
 		
	} 
	
	public void onClickAddAnotherRecipe(View button)
	{
		Intent IntentAdd = new Intent();
    	IntentAdd.setClass(this,AddRecipe.class);
    	startActivity(IntentAdd); 
	}
	
	public void onClickLookup(View v)
	{
		Intent IntentLookup = new Intent();
    	IntentLookup.setClass(this,Lookup.class);
    	startActivity(IntentLookup);	
	}
}
