package libs;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import objects.SettingsNode;
import objects.VariableNode;
import objects.VariablesNode;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLSettings {

	public final static String VARS_KOEF = "koef";
	public final static String VARS_COUNT = "count";
	public final static String VARS_DEFAULT_USER = "default_user";
	
	private final static String FILE_SRC = "settings.xml";
	private Document doc;
	private Node settingsNode;

	public XMLSettings () throws Exception
    {
        try {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            f.setValidating(false);
            DocumentBuilder builder = f.newDocumentBuilder();
            setDoc(builder.parse(new File(FILE_SRC)));
        } catch (Exception exception) {
            throw new Exception("XML parsing error!");
        }
	}
	
	public List<VariablesNode> getAll()
    {
		return (new SettingsNode(this.getSettingsNode())).getVariables();
	}
	
	public List<VariableNode> getVariablesList(String nameVariables) {
		List<VariableNode> resultList = null;
		List<VariablesNode> list = (new SettingsNode(this.getSettingsNode())).getVariables();
		
		for (int i = 0; i < list.size() && resultList == null; ++i) {
        	VariablesNode variablesNode = list.get(i);
            try {
                if(nameVariables.equals(variablesNode.getName()))
                    resultList = variablesNode.getVariable();
            } catch (Exception ex) { }
        }
		return resultList;
	}
	
	public String getVariable(String nameVariable)
    {
		return this.getVariable(VARS_KOEF, nameVariable);
	}
	
	public String getVariable(String nameVariables, String nameVariable)
    {
		String result = "";
		List<VariableNode> list = this.getVariablesList(nameVariables);
		
		if(list != null && list.size() > 0) {
			for (int j = 0; j < list.size() && result == ""; j++)
            {
            	VariableNode variableNode = list.get(j);
                try {
                    if(nameVariable.equals(variableNode.getName()))
                        result = variableNode.getValue();
                } catch (Exception ex) { }

            }
		}
		return result;
	}
	
	public int getVariableInt(String nameVariable)
    {
		return this.getVariableInt(VARS_KOEF, nameVariable);
	}
	
	public int getVariableInt(String nameVariables, String nameVariable)
    {
		return Integer.parseInt(this.getVariable(nameVariables, nameVariable));
	}
	
	public double getVariableDouble(String nameVariable)
    {
		return this.getVariableDouble(VARS_KOEF, nameVariable);
	}
	
	public double getVariableDouble(String nameVariables, String nameVariable)
    {
		return Double.parseDouble(this.getVariable(nameVariables, nameVariable).replaceAll(",", "."));
	}
	
	public void print()
    {
        Node node = getDoc().getChildNodes().item(0);
        SettingsNode settingsNode = new SettingsNode(node);
        List<VariablesNode> variables = settingsNode.getVariables();
 
        for (VariablesNode classNode: variables) {
            List<VariableNode> variable = classNode.getVariable();
 
            for (VariableNode methodNode: variable) {

            }
        }
 
    }
	
    public Document getDoc()
    {
		return this.doc;
	}

	public void setDoc(Document doc)
    {
		this.settingsNode = doc.getChildNodes().item(0);
		this.doc = doc;
	}
	
    public Node getSettingsNode()
    {
		return this.settingsNode;
	}
    
    public boolean reWriteValue(String nameVariables, String nameVariable, String newValue)
    {
		boolean result = false;
		
		NodeList variables_list =  doc.getElementsByTagName("variables");
		for(int i = 0; i < variables_list.getLength(); ++i)
        {
			Node variables = variables_list.item(i);
			if (variables.getNodeType() == Node.ELEMENT_NODE)
            {
				String name_vars = variables.getAttributes().getNamedItem("name").getNodeValue();
				if(nameVariables.equals(name_vars))
                {
					for (Node variable = variables.getFirstChild(); variable != null; variable = variable.getNextSibling())
                    {
						if (variable.getNodeType() == Node.ELEMENT_NODE)
                        {
							String name_var = variable.getAttributes().getNamedItem("name").getNodeValue();
							if(nameVariable.equals(name_var))
                            {
								variable.setTextContent(newValue);
								result = true;
							}
						}
					}
				}
			}
		}
		
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			StreamResult streamResult = new StreamResult(new File(FILE_SRC));
			transformer.transform(new DOMSource(doc), streamResult);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
