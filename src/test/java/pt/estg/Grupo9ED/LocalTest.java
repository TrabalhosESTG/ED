package pt.estg.Grupo9ED;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import API.Local;
import API.Player;
public class LocalTest {
    
    @Test
    void testGetId() {
        Local local = new Local(0, null, null, 0, 0, 0, 0);
        assertEquals(0, local.getId());
    }

    @Test
    void testGetPlayer() {
        Local local = new Local(0, null, null, 0, 0, 0, 0);
        local.setPlayer(new Player("nome", null, 0));
        assertEquals("nome", local.getPlayer().getName());
    }

    @Test
    void testGetTeam() {
        Local local = new Local(0, null, null, 0, 0, 0, 0, null,"team");
        local.setPlayer(new Player("nome", "team", 0));
        local.conquer();
        assertEquals("team", local.getConquererTeam());
    }

    @Test
    void testGetEnergy() {
        Local local = new Local(0, null, null, 0, 0, 0, 0);
        local.setEnergy(1);
        assertEquals(1, local.getEnergy());
    }

    @Test
    void testGetName() {
        Local local = new Local(0, null, null, 0, 0, 0, 0);
        local.setName("nome");
        assertEquals("nome", local.getName());
    }

    @Test
    void testGetType() {
        Local local = new Local(0, "Portal", null, 0, 0, 0, 0);
        
        assertEquals("Portal", local.getType());
    }

    @Test
    void testGetLongitude() {
        Local local = new Local(0, null, null, 0, 0, 0, 0);
        local.setLongitude(1);
        assertEquals(1, local.getLongitude());
    }

    @Test
    void testGetLatitude() {
        Local local = new Local(0, null, null, 0, 0, 0, 0);
        local.setLatitude(1);
        assertEquals(1, local.getLatitude());
    }

    @Test
    void testLoadPlayerEnergy() {
        Local local = new Local(0, "Portal", null, 0, 0, 1, 0);
        local.setPlayer(new Player("Player", 1, "team", 99.1, 1, 1, 1, 1, 1,1));
        local.loadPlayerEnergy();
        local = new Local(0, "Connector", null, 0, 0, 1, 0);
        local.setPlayer(new Player("Player", 1, "team", 99.1, 1, 1, 1, 1, 1,1));
        local.loadPlayerEnergy();
        assertEquals(2, local.getPlayer().getEnergy());
    }

    @Test
    void testAddLocalControl() {
        Local local = new Local(0, "Portal", null, 0, 0, 1, 0);
        local.addLocalControl(new Local(0, null, null, 0, 0, 0, 0), 12.0);
        assertEquals(1, local.getLocalControl().size());
    }

    @Test
    void testRemoveLocalControl() {
        Local local = new Local(0, "Portal", null, 0, 0, 1, 0);
        Local local1 = new Local(0, null, null, 0, 0, 0, 0);
        local.addLocalControl(local1, 12.0);
        local.removeLocalControl(local1);
        assertEquals(0, local.getLocalControl().size());
    }
    
    @Test
    void testLoadEnergy() {
        Local local = new Local(0, "Portal", null, 0, 0, 1, 1, null, "team");
        local.setMaxEnergy(10);
        local.loadEnergy(10);
        assertEquals(10, local.getEnergy());
    }

    @Test
    void testGetConquererPlayer() {
        Local local = new Local(0, "Portal", null, 0, 0, 1, 1, null, "team");
        local.setPlayer(new Player("Player", 1, "team", 99.1, 1, 1, 1, 1, 1,1));
        local.conquer();
        assertEquals("Player", local.getConquererPlayer());
    }

    @Test
    void testGetMaxEnergy() {
        Local local = new Local(0, "Portal", null, 0, 0, 1, 1, null, "team");
        local.setMaxEnergy(10);
        assertEquals(10, local.getMaxEnergy());
    }

    @Test
    void testDeloadEnergy() {
        Local local = new Local(0, "Portal", null, 0, 0, 1, 1, null, "team");
        local.setMaxEnergy(10);
        local.loadEnergy(10);
        local.deloadEnergy(12);
        assertEquals(0, local.getEnergy());
    }
}
