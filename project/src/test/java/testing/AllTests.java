package testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ProfTestCase1.class, ProfTestCase2.class, ProfTestCase3.class,
		ProfTestCase4.class, TimeSlotsTestCase1.class,
		TimeSlotsTestCase2.class, TimeSlotsTestCase3.class,
		TimeSlotsTestCase4.class, TimeSlotsTestCase5.class,
		TimeSlotsTestCase6.class })
public class AllTests {

}
