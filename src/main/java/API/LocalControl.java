package API;

/**
*Class that represents a local control, it stores the local and the weight of the control
*@author Guilherme Silva (8210190)
*@author David Francisco (8210088)
*/
public class LocalControl {
    protected Local portal;
    protected double weight;

    /**
    * Constructor for the class LocalControl
    * Initializes the variables with the values passed as arguments
    *
    * @param portal Local of the control
    * @param weight Weight of the control
    */
    public LocalControl(Local portal, double weight) {
        this.portal = portal;
        this.weight = weight;
    }

    /**
    * Obtains the local of the control
    *
    * @return Local of the control
    */
    public Local getLocal() {
        return portal;
    }

    /**
    * Obtains the weight of the control
    *
    * @return Weight of the control
    */
    public double getWeight() {
        return weight;
    }

}
