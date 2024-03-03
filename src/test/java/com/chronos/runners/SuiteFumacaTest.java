package com.chronos.runners;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.chronos.tests")
@IncludeTags("Fumaca")
public class SuiteFumacaTest {
}
