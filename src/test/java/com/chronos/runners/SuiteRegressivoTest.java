package com.chronos.runners;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@Suite
@SelectPackages("com.chronos.tests")
public class SuiteRegressivoTest {
}