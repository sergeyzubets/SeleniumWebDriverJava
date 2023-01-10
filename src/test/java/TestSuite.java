import org.junit.platform.suite.api.*;

@Suite
@SelectPackages({"parser", "shop"})
@SuiteDisplayName("Shop Test Suite")
@IncludeClassNamePatterns(".*Test")
//@IncludeTags("Smoke")
//@ExcludeTags("Smoke")
public class TestSuite {

}
