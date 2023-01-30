package API;

public class ConnectorControl {
    protected Connector connector;
    protected double weight;

    public ConnectorControl(Connector connector, double weight) {
        this.connector = connector;
        this.weight = weight;
    }

    public Connector getConnector() {
        return connector;
    }

    public double getWeight() {
        return weight;
    }
    
}
