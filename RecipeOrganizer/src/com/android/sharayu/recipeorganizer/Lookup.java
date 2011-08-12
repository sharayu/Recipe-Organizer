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

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

public class Lookup extends Activity implements OnClickListener {

	int x=0;
	protected String Selectedlevel;
	String st= new String();
	public static final String TABLE_NAME = "RecpOrg";
	public void onCreate(Bundle SavedInstance){
		super.onCreate(SavedInstance);
		setContentView(R.layout.lookup);
		
		st=getRecpNames();
		
		 View button1Click = findViewById(R.id.bGrocList);
	        button1Click.setOnClickListener(this);
	        View button2Click = findViewById(R.id.bEditRecp);
	        button2Click.setOnClickListener(this); 
	        View button3Click = findViewById(R.id.bDeleteRecp);
	        button3Click.setOnClickListener(this);
	        View button4Click = findViewById(R.id.bShowRecp);
	        button4Click.setOnClickListener(this); 
		}
		 
	public String getRecpNames()
	{

		DataHandler dh = new DataHandler(this); 

 		SQLiteDatabase db1 = dh.getReadableDatabase();
 		Cursor cursor = db1.query(TABLE_NAME,new String[]{"name"}, null, null, null, null,null );
 		
 		int x=0;
 		String st1[]= new String[30];
 	 		for(int i=0;i<=29;i++){
	 		st1[i] = "";
	 		}

 		
 		if(cursor.moveToFirst())
 		{
 			do	
 			{			
 				st1[x] = cursor.getString(0);
 				x=x+1;
 			}while(cursor.moveToNext());
 		}
 	
 			
 		if(cursor != null && !cursor.isClosed())
 		{
 			cursor.close();
 		}
 		cursor.close();
 		

 		Spinner spinner = new Spinner(this);
 		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,st1);
 		
 		
 		spinner = (Spinner) findViewById(R.id.spinner1);
 		spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item );
 		spinner.setAdapter(spinnerArrayAdapter);
 		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
 			private String countrySelection;
 			public void onItemSelected(AdapterView<?> country0, View view, int pos, long l) {         
 				countrySelection = country0.getItemAtPosition(pos).toString();
 				Selectedlevel = countrySelection;
 				Toast.makeText(country0.getContext(), "The country is " + countrySelection, Toast.LENGTH_LONG).show();  
 				}
 			public void onNothingSelected(AdapterView<?> adapterView) { 
 				return;  
 				}
 			});  
 			
 		 
		return Selectedlevel;
	}
	
	
	public void getRecipe(String table_name)
	{
		
		DataHandler dh = new DataHandler(this); 
		SQLiteDatabase db1 = dh.getReadableDatabase();
		String sql = "select name,ingredients,procedure from RecpOrg where name =" + table_name;
		Cursor cursor = db1.rawQuery(sql, null);
		
		int x1=0;
		String string1 = new String();
		if(cursor.moveToFirst())
 		{
 			do	
 			{			
 				string1= "Name:        " +cursor.getString(0)+ "\n\n"
 				       + "Ingredients: " + cursor.getString(1)+ "\n\n"
 				       + "Procedure:   " + cursor.getString(2);
 				
 				x1=x1+1;
 			}while(cursor.moveToNext());
 		}
 		
	 	
 		if(cursor != null && !cursor.isClosed())
 		{
 			cursor.close();
 		}
 		cursor.close(); 
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
        case R.id.bGrocList:
            Intent i = new Intent(this, GenGrocList.class);  
            i.putExtra("RecipeName",Selectedlevel);
            startActivity(i);
        break;
        case R.id.bEditRecp:
            Intent i1 = new Intent(this, EditRecp.class); 
            i1.putExtra("RecipeName", Selectedlevel);
            startActivity(i1);
        break;
        case R.id.bDeleteRecp:
            Intent i2 = new Intent(this, DelRecp.class); 
            i2.putExtra("RecipeName", Selectedlevel);
            startActivity(i2);
        break; 
        case R.id.bShowRecp:
            Intent i3 = new Intent(this, AddRecipe.class); 
            i3.putExtra("RecipeName", Selectedlevel);
            startActivity(i3);
        break; 
    }
	
	}
}
