import java.util.ArrayList;
import java.util.List;

public class Node{

	private String word;
	private Integer cost;
	private List<Node> childs;
	private Node parent;

    public Node(String w) {
        word = w;
        this.childs = new ArrayList<>();
        this.cost = 0;
        this.parent = null;
    }
    
    public void setChild(List<String> words) {
        for (String word : words) {
            Node childNode = new Node(word);
            childNode.setCost(1);
            this.childs.add(childNode); 
        }
    }

    public void setParent(Node p) {
        parent = p;
    }

    public void setCost(Integer c) {
        cost = c;
    }

    public String getValue() {
        return word;
    }

    public Integer getCost() {
        return cost;
    }

    public List<Node> getChilds() {
        return childs;
    }

    public Node getParent() {
        return parent;
    }
}
