package com.example.lavdrimshala_asignment1.test;

import junit.framework.Assert;

import com.example.lavdrimshala_asignment1.MainActivity;

import android.app.Activity;
import android.app.Instrumentation;
import android.database.Cursor;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

	Activity objActivity;
	public MainActivityTest()
	{
	super("com.example.lavdrimshala_asignment1",MainActivity.class);	
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		objActivity=getActivity();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testFillspinner() {	
		((MainActivity)objActivity).fillspinner();
		Spinner s = (Spinner)objActivity.findViewById(com.example.lavdrimshala_asignment1.R.id.spinner1);
		Assert.assertNotNull(s.getItemAtPosition(4));
	}
	
	public void testsavePreferences()
	{
		RatingBar r1 = (RatingBar)objActivity.findViewById(com.example.lavdrimshala_asignment1.R.id.ratingBar_Question1);
		r1.setRating((float)7);
		((MainActivity)objActivity).savePreferences();
		float expected=r1.getRating();
		float actual= objActivity.getPreferences(objActivity.MODE_PRIVATE).getFloat("Question1",(float)5.0);
		Assert.assertEquals(expected,actual);	
	}
	public void testonRatingChanged()
	{
		final RatingBar r1 = (RatingBar)objActivity.findViewById(com.example.lavdrimshala_asignment1.R.id.ratingBar_Question1);
		objActivity.runOnUiThread(new Runnable() 
		{          
			public void run() 
			{              
				r1.setRating((float)10);				
			}      
		});
		getInstrumentation().waitForIdleSync();
		TextView t1 = (TextView)objActivity.findViewById(com.example.lavdrimshala_asignment1.R.id.TextView_Question1result);
		
		String expected = Float.toString(r1.getRating())+"/10";
		String actual = t1.getText().toString();
		
		Assert.assertEquals(expected, actual);
	}
	public void testonSubmit()
	{
		
		final Button b1 = (Button)objActivity.findViewById(com.example.lavdrimshala_asignment1.R.id.button_Submit);
		RatingBar r1 = (RatingBar)objActivity.findViewById(com.example.lavdrimshala_asignment1.R.id.ratingBar_Question1);
		Cursor cr1 = ((MainActivity)objActivity).db.rawQuery("Select Count() AS Count From tblResults", null);	
		cr1.moveToNext();
		int result = cr1.getInt(cr1.getColumnIndex("Count"));
		
		objActivity.runOnUiThread(new Runnable() 
		{          
			public void run() 
			{              
				b1.performClick();  
				
			}      
		});
		getInstrumentation().waitForIdleSync();
		Cursor cr2 = ((MainActivity)objActivity).db.rawQuery("Select Count() AS Count From tblResults", null);
		cr2.moveToNext();
		int resultafter=cr2.getInt(cr2.getColumnIndex("Count"));
		Assert.assertEquals(result+1, resultafter);
		Assert.assertEquals((float)5.0, r1.getRating());
	}
	public void testsetlisteners()
	{
		((MainActivity)objActivity).setlisteners();
		RatingBar r1 = (RatingBar)objActivity.findViewById(com.example.lavdrimshala_asignment1.R.id.ratingBar_Question1);
		Assert.assertEquals(r1.getOnRatingBarChangeListener(),((MainActivity)objActivity));
	}
	public void testdatabasehelp()
	{
		((MainActivity)objActivity).databasehelp();
		Cursor cr1 = ((MainActivity)objActivity).db.rawQuery("Select * From tblRelationship", null);
		Cursor cr2 = ((MainActivity)objActivity).db.rawQuery("Select * From tblResults", null);
		Assert.assertTrue(cr1!=null);
		Assert.assertTrue(cr2!=null);
		Assert.assertTrue(cr2.moveToFirst());
	}
	public void testgetpreferences()
	{
		RatingBar r1 = (RatingBar)objActivity.findViewById(com.example.lavdrimshala_asignment1.R.id.ratingBar_Question1);
		r1.setRating((float)7.0);
		((MainActivity)objActivity).savePreferences();
		r1.setRating((float)9.0);
		objActivity.runOnUiThread(new Runnable() 
		{          
			public void run() 
			{              
				((MainActivity)objActivity).getPreferences(); 
				
			}      
		});
		getInstrumentation().waitForIdleSync();
		Assert.assertEquals((float)7.0,r1.getRating());
	}
	
	
	
	
	

}
