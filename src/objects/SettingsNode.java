package objects;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SettingsNode
{
    Node node;

    public SettingsNode(Node node_)
    {
        this.node = node_;
    }

    public List<VariablesNode> getVariables()
    {
        ArrayList<VariablesNode> variables = new ArrayList<VariablesNode>();
        NodeList classNodes = this.node.getChildNodes();

        int size = classNodes.getLength();
        for (int i = 0; i < size; i++) {
            Node node_ = classNodes.item(i);

            if (node_.getNodeType() == Node.ELEMENT_NODE)
            {
            	VariablesNode variablesNode = new VariablesNode(node_);
            	variables.add(variablesNode);
            }
        }

        return variables;
    }
}