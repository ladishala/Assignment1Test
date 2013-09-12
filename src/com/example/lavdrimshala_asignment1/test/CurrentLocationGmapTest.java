package com.example.lavdrimshala_asignment1.test;

import java.util.Timer;
import java.util.TimerTask;

import junit.framework.Assert;

import com.example.lavdrimshala_asignment1.CurrentLocationGmap;

import android.app.Activity;
import android.location.Location;
import android.os.Handler;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

public class CurrentLocationGmapTest extends
		ActivityInstrumentationTestCase2<CurrentLocationGmap> {
	
	Activity objActivity;
	float variable;
	public CurrentLocationGmapTest()
	{
		super("com.example.lavdrimshala_asignment1",CurrentLocationGmap.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		objActivity=getActivity();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	public void testsetUpLocationClientIfNeeded() {
		((CurrentLocationGmap)objActivity).setUpLocationClientIfNeeded();
		boolean res = false;
		if(((CurrentLocationGmap)objActivity).mLocationClient != null)
		{
			res=true;
		}
		Assert.assertTrue(res);
	}
	public void testsetUpMapIfNeeded() {

		((CurrentLocationGmap)objActivity).setUpMapIfNeeded();
		boolean res=false;
		if(((CurrentLocationGmap)objActivity).mMap != null)
		{
				res=true;	
		}
		Assert.assertTrue(res);
	}

}
