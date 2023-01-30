package API;

public class PortalControl {
    protected Portal portal;
    protected double weight;

    public PortalControl(Portal portal, double weight) {
        this.portal = portal;
        this.weight = weight;
    }

    public Portal getPortal() {
        return portal;
    }

    public double getWeight() {
        return weight;
    }
    
}
