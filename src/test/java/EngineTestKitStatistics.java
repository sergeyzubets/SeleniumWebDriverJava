import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

import org.junit.jupiter.api.Test;
import org.junit.platform.testkit.engine.EngineTestKit;
import org.junit.platform.testkit.engine.Events;
import parser.JsonParserTest;
import shop.*;

class EngineTestKitStatistics {

    @Test
    void getTestStats() {
        Events testEvents = EngineTestKit
                .engine("junit-jupiter")
                .selectors(
                        selectClass(CartTest.class),
                        selectClass(RealItemTest.class),
                        selectClass(VirtualItemTest.class),
                        selectClass(JsonParserTest.class))
                .execute()
                .testEvents();
        testEvents.assertStatistics(stats -> stats
                .skipped(1)
                .started(18)
                .succeeded(18)
                .aborted(0)
                .failed(0));
    }
}
