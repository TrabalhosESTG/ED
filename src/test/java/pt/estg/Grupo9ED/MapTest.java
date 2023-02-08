package pt.estg.Grupo9ED;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import API.*;
import Exceptions.InvalidValue;
public class MapTest {

    @Test
    public void testAddLocal() {
        Map map = new Map();
        Local local = new Local(0, "Local", "null", 1, 1, 1, 0);
        map.addLocal(local);
        assertEquals(1, map.getCount());
    }

    @Test
    void testEditLocal() {
        Map map = new Map();
        Local local = new Local(0, "Portal", "null", 1, 1, 1, 0);
        map.addLocal(local);
        try {
            map.editLocal(local, 2, 2, 3);
        } catch (InvalidValue invalidValue) {
            invalidValue.printStackTrace();
        }
        assertEquals(2, local.getLatitude());
    }

    @Test
    void testRemoveLocal() {
        Map map = new Map();
        Local local = new Local(0, "Local", "null", 1, 1, 1, 0);
        map.addLocal(local);
        map.removeLocal(local);
        assertEquals(0, map.getCount());
    }

    @Test
    void testAddLocalConnection() {
        Map map = new Map();
        Local local1 = new Local(0, "Local", "null", 1, 1, 1, 0);
        Local local2 = new Local(1, "Local", "null", 1, 1, 1, 0);
        map.addLocal(local1);
        map.addLocal(local2);
        map.addLocalConnection(local1, local2, 1);
        assertEquals(1, local1.getLocalControl().size());
    }

    @Test
    void testRemoveLocalConnection() {
        Map map = new Map();
        Local local1 = new Local(0, "Local", "null", 1, 1, 1, 0);
        Local local2 = new Local(1, "Local", "null", 1, 1, 1, 0);
        map.addLocal(local1);
        map.addLocal(local2);
        map.addLocalConnection(local1, local2, 1);
        map.removeLocalConnection(local1, local2);
        assertEquals(0, local1.getLocalControl().size());
    }

    @Test
    void testGetAllLocals() {
        Map map = new Map();
        Local local1 = new Local(0, "Local", "null", 1, 1, 1, 0);
        Local local2 = new Local(1, "Local", "null", 1, 1, 1, 0);
        Local[] locals = new Local[2];
        locals[0] = local1;
        locals[1] = local2;
        map.addLocal(local1);
        map.addLocal(local2);
        assertEquals(locals, map.getAllLocals());
    }

    @Test
    void testFindLocalById() {
        Map map = new Map();
        Local local1 = new Local(0, "Local", "null", 1, 1, 1, 0);
        Local local2 = new Local(1, "Local", "null", 1, 1, 1, 0);
        map.addLocal(local1);
        map.addLocal(local2);
        assertEquals(local2, map.findLocalById(1));
    }

    @Test
    void testFindIndexById() {
        Map map = new Map();
        Local local1 = new Local(0, "Local", "null", 1, 1, 1, 0);
        Local local2 = new Local(1, "Local", "null", 1, 1, 1, 0);
        map.addLocal(local1);
        map.addLocal(local2);
        assertEquals(1, map.findIndexById(1));
    }

    @Test
    void testOrderByIdConnectors() {
        Map map = new Map();
        Local local1 = new Local(0, "Connector", "null", 1, 1, 1, 0);
        Local local2 = new Local(1, "Local", "null", 1, 1, 1, 0);
        Local[] locals = new Local[2];
        locals[0] = local1;
        map.addLocal(local1);
        map.addLocal(local2);
        assertEquals(locals, map.orderLocalsById("Connector"));
    }

    @Test
    void testOrderByIdPortals() {
        Map map = new Map();
        Local local1 = new Local(0, "Portal", "null", 1, 1, 1, 0);
        Local local2 = new Local(1, "Local", "null", 1, 1, 1, 0);
        Local[] locals = new Local[2];
        locals[0] = local1;
        map.addLocal(local1);
        map.addLocal(local2);
        assertEquals(locals, map.orderLocalsById("Portal"));
    }

    @Test
    void testFindLocalByLatELon() {
        Map map = new Map();
        Local local1 = new Local(0, "Local", "null", 1, 1, 1, 0);
        Local local2 = new Local(1, "Local", "null", 2, 2, 1, 0);
        map.addLocal(local1);
        map.addLocal(local2);
        assertEquals(local1, map.findLocalByLatELon(1, 1));
    }
}
