package com.example.lavdrimshala_asignment1.test;

import junit.framework.Assert;

import com.example.lavdrimshala_asignment1.R;
import com.example.lavdrimshala_asignment1.Results;

import android.app.Activity;
import android.database.Cursor;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultsTest extends ActivityInstrumentationTestCase2<Results> {

	Activity objActivity;
	public ResultsTest()
	{
		super("com.example.lavdrimshala_asignment1",Results.class);
	}
	protected void setUp() throws Exception {
		super.setUp();
		objActivity=getActivity();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetresults() {        
		((Results)objActivity).getresults();
		RatingBar r1 = (RatingBar)objActivity.findViewById(com.example.lavdrimshala_asignment1.R.id.RatingBar_Question1Res);
		Cursor cr1 = ((Results)objActivity).db.rawQuery("Select AVG(Q1) from tblResults",null);
		cr1.moveToFirst();
		float fromDB = cr1.getFloat(0);
		int expected = (int)Math.round(fromDB);
		Assert.assertEquals((float)expected, r1.getRating());
	}

	public void testCountrecords() {
		String actual = ((Results)objActivity).countrecords();
		Cursor cr1 = ((Results)objActivity).db.rawQuery("Select Count() AS Count From tblResults",null);
		cr1.moveToFirst();
		String expected = cr1.getString(0);
		
		Assert.assertEquals(expected, actual);
	}

	public void testSettext() {
		objActivity.runOnUiThread(new Runnable() 
		{          
			public void run() 
			{              
				((Results)objActivity).settext(); 
				
			}      
		});
		getInstrumentation().waitForIdleSync();
		TextView t1 = (TextView)objActivity.findViewById(com.example.lavdrimshala_asignment1.R.id.textView_ResultsHead);
		String actual = t1.getText().toString().substring(0, 8);
		String expected = "Feedback";
		
		Assert.assertEquals(expected, actual);
	}

}
