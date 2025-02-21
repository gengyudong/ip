package geng;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import geng.ui.GengException;

public class GengTest {
    private Geng geng;

    @BeforeEach
    public void setUp() {
        geng = new Geng("data/test_geng.txt");
    }

    @Test
    public void testConstructor() {
        assertNotNull(geng);
    }

    @Test
    public void testGetResponse() {
        try {
            String response = geng.getResponse("list");
            assertNotNull(response);
        } catch (GengException e) {
            fail("Exception should not be thrown");
        }
    }
}
