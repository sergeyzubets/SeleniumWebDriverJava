import org.junit.platform.suite.api.*;

@Suite
@SelectPackages({"com.coherentsolutions.junit.parser", "com.coherentsolutions.junit.shop"})
@SuiteDisplayName("Shop Test Suite")
@IncludeClassNamePatterns(".*Test")
//@IncludeTags("Smoke")
//@ExcludeTags("Smoke")
public class TestSuite {

}
