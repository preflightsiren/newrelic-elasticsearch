package me.snov.newrelic.elasticsearch.services;

import me.snov.newrelic.elasticsearch.parsers.NodesStatsParser;
import me.snov.newrelic.elasticsearch.responses.NodesStats;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class NodesStatsParserTest {

    private NodesStatsParser nodesStatsParser;

    /**
     * Sets up the test fixture.
     * (Called before every test case method.)
     */
    @Before
    public void setUp() {
        nodesStatsParser = new NodesStatsParser();
    }

    private NodesStats parseJson(String path) throws IOException {
        InputStream stream = getClass().getResourceAsStream(path);
        assertNotNull(String.format("Resource %s exists", path), stream);
        return nodesStatsParser.parse(stream);
    }

    @Test
    public void testV134() throws Exception {
        NodesStats nodesStats = parseJson("/resources/nodes_stats_1.3.4.json");
        assertEquals(3, nodesStats.nodes.size());
        assertEquals(200l, nodesStats.nodes.get("lNFk2gshR5GVDPmRrnDyoA")
            .jvm.gc.collectors.young.collection_time_in_millis.longValue());
    }
}
