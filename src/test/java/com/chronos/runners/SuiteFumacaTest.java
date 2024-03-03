package com.chronos.runners;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.runner.RunWith;

@Suite
@SelectPackages("com.chronos.tests")
@IncludeTags("Fumaca")
public class SuiteFumacaTest {
}
