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
import android.content.Intent;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

public class AddRecipe extends Activity {
	
	String level[] = new String[]{"Easy","Medium","Difficult"};
	String Selectedlevel ="easy";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        
        Spinner spinner = new Spinner(this);
		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,level);
	
		spinner = (Spinner) findViewById(R.id.sLevel);
		spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item );
		spinner.setAdapter(spinnerArrayAdapter);
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		
			private String countrySelection;
		public void onItemSelected(AdapterView<?> country0, View view, int pos, long l) {         
			// Your code here    
			countrySelection = country0.getItemAtPosition(pos).toString();
			Selectedlevel = countrySelection;
			Toast.makeText(country0.getContext(), "The country is " + countrySelection, Toast.LENGTH_LONG).show();  
			}
		public void onNothingSelected(AdapterView<?> adapterView) { 
			return;  
			}
		});  
		}


public void onClickSaveRecipe(View Button)
{
	AutoCompleteTextView RecipeName = (AutoCompleteTextView) findViewById(R.id.ACTRecipeName);
	AutoCompleteTextView Serves = (AutoCompleteTextView) findViewById(R.id.ACTVServes);
	AutoCompleteTextView TimeReq = (AutoCompleteTextView) findViewById(R.id.ACTVTimeReq);
	AutoCompleteTextView Ingredients = (AutoCompleteTextView) findViewById(R.id.ACTVIngredients);
	AutoCompleteTextView Procedure = (AutoCompleteTextView) findViewById(R.id.ACTProcedure);
	
	Intent IntentSave = new Intent();
	IntentSave.setClass(this, RecipeSaved.class);
	
	IntentSave.putExtra("Recipe_Name", RecipeName.getText().toString());
	IntentSave.putExtra("Serves", Serves.getText().toString());
	IntentSave.putExtra("Level",Selectedlevel);
	IntentSave.putExtra("TimeReq", TimeReq.getText().toString());
	IntentSave.putExtra("Ingredients", Ingredients.getText().toString());
	IntentSave.putExtra("Procedure", Procedure.getText().toString());
	startActivity(IntentSave);
	
}


public void onClickLook(View button)
{
	Intent IntentAdd1 = new Intent();
	IntentAdd1.setClass(this,Lookup.class);
	startActivity(IntentAdd1); 
}

}