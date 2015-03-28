package objects;

import org.w3c.dom.Node;

public class VariableNode {
    Node node;

    public VariableNode(Node node_)
    {
        this.node = node_;
    }

    public String getName() throws Exception {
        try {
            return this.node.getAttributes().getNamedItem("name").getNodeValue();
        } catch (Exception exception) {
            throw new Exception("Get name error!");
        }
    }
    
    public String getValue () throws Exception {
        try {
    	    return this.node.getTextContent();
        } catch (Exception exception) {
            throw new Exception("Get value error!");
        }
    }

}