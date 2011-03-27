package com.bryanjswift.simplenote;

import junit.framework.Test;
import junit.framework.TestSuite;
import android.test.suitebuilder.TestSuiteBuilder;

public class SimplenoteTest extends TestSuite {
	public static Test suite() {
		return new TestSuiteBuilder(SimplenoteTest.class).includeAllPackagesUnderHere().build();
	}

}

