package API;

import Lists.LinkedList;

public class Local {
    protected int id;
    protected String name;
    protected int energy;
    protected Player player;
    protected double latitude;
    protected double longitude;
    protected LinkedList<ConnectorControl> connectorControl = new LinkedList<ConnectorControl>();
    protected LinkedList<PortalControl> portalControl = new LinkedList<PortalControl>();

    public Local(int id, String name, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }
    
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getEnergy() {
        return this.energy;
    }

    public String getName() {
        return this.name;
    }

    public double getLongitude(){
        return longitude;
    }

    public double getLatitude(){
        return latitude;
    }

    public void setLongitude(double longitude){
        this.longitude = longitude;
    }

    public void setLatitude(double latitude){
        this.latitude = latitude;
    }

    public void setName(String name) {
        this.name = name;
    }
}
