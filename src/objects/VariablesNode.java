package objects;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class VariablesNode {
    Node node;

    public VariablesNode(Node node_)
    {
        this.node = node_;
    }

    public List<VariableNode> getVariable()
    {
        ArrayList<VariableNode> methods = new ArrayList<VariableNode>();
        NodeList methodNodes = this.node.getChildNodes();

        for (int i = 0; i < methodNodes.getLength(); i++)
        {
            Node _node = methodNodes.item(i);
            if (_node.getNodeType() == Node.ELEMENT_NODE)
            {
            	VariableNode variableNode = new VariableNode(_node);
                methods.add(variableNode);
            }
        }

        return methods;
    }
    
    public String getName() throws Exception {
    	return node.getAttributes().getNamedItem("name").getNodeValue();
    }
}