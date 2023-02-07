package API;

public class LocalControl {
    protected Local portal;
    protected double weight;

    public LocalControl(Local portal, double weight) {
        this.portal = portal;
        this.weight = weight;
    }

    public Local getLocal() {
        return portal;
    }

    public double getWeight() {
        return weight;
    }
    
}
