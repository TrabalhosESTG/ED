package pt.estg.Grupo9ED;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import API.Local;
import API.LocalControl;
public class LocalControlTest {
    
    @Test
    void testGetLocal() {
        LocalControl local = new LocalControl(new Local(0, null, null, 0, 0, 0, 0), 12);
        assertEquals(0, local.getLocal().getId());
    }

    @Test
    void testGetWeight() {
        LocalControl local = new LocalControl(new Local(0, null, null, 0, 0, 0, 0), 12);
        assertEquals(12, local.getWeight());
    }
}
