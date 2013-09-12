package com.example.lavdrimshala_asignment1.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(MainActivityTest.class);
		suite.addTestSuite(LoginTest.class);
		suite.addTestSuite(ResultsTest.class);
		suite.addTestSuite(CurrentLocationGmapTest.class);
		//$JUnit-END$
		return suite;
	}

}
