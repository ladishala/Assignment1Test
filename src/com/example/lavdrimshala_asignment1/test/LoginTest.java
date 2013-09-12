package com.example.lavdrimshala_asignment1.test;

import junit.framework.Assert;

import com.example.lavdrimshala_asignment1.Login;
import com.example.lavdrimshala_asignment1.Results;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

public class LoginTest extends ActivityInstrumentationTestCase2<Login> {

	Activity objActivity;
	public LoginTest()
	{
		super("com.example.lavdrimshala_asignment1",Login.class);
	}
	protected void setUp() throws Exception {
		super.setUp();
		objActivity=getActivity();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testToSHA1() {
		String expected = ((Login)objActivity).toSHA1("1234");
		String actual = "7110eda4d09e62aa5e4a390b0a572acd2c220";
		Assert.assertEquals(expected, actual);
	}

	public void testLogin() {
		final EditText e1 = (EditText)objActivity.findViewById(com.example.lavdrimshala_asignment1.R.id.editText_Login);
		
		final Button b1 = (Button)objActivity.findViewById(com.example.lavdrimshala_asignment1.R.id.button1);
		objActivity.runOnUiThread(new Runnable() 
		{          
			public void run() 
			{   
				e1.setText("1234");
				b1.performClick(); 
				
			}      
		});
		getInstrumentation().waitForIdleSync();
		Assert.assertEquals(e1.getText().toString(),"");
	}

}
