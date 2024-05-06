import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class GBFS {
    public void solveGBFS(Node start, Node end, Map<String, List<String>> wordMap) {
        Queue<Node> q = new LinkedList<Node>();
        List<Node> visited = new ArrayList<Node>();
        boolean found = false;

        q.add(start);

        while (!q.isEmpty() && (found == false)) {
            Node curr = q.poll();
            visited.add(curr);

            if (curr.getValue().equals(end.getValue())) {
                end = curr;
                found = true;
            }

            Node nearest = findShortest(curr.getChilds(), end);

            if (!cekVisited(visited, nearest)) {
                nearest.setParent(curr);
                List<String> child = wordMap.get(nearest.getValue());
                nearest.setChild(child);
                q.add(nearest);
            }
        }
        if (found) {
		    printPath(end);
        }
        else {
            System.out.println("Path Not Found");
        }
    }
    
    public Node findShortest(List<Node> childs,Node Goal) {
        Node shortest = new Node("kosong");
        for (Node n : childs) {
            if (shortest.getValue().equals("kosong")) {
                shortest = n;
            } else if (costFromGoal(n.getValue(), Goal.getValue()) < costFromGoal(shortest.getValue(),
                    Goal.getValue())) {
                shortest = n;
            }
        }
        return shortest;
    }
    
    public Integer costFromGoal(String word1, String word2) {
        int diff = 0;
        word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();

        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diff++;
            }
        }
        return diff;
    }
    
    public boolean cekVisited(List<Node> visited, Node nodeCek) {
        for (Node n : visited) {
            if (n.getValue() == nodeCek.getValue()) {
                return true;
            }
        }
        return false;

    }
    
    public void printPath(Node target) {
		List<Node> path = new ArrayList<Node>();
		for (Node node = target; node != null; node = node.getParent()) {
			path.add(node);
			// System.out.println(node.getValue());
		}

		Collections.reverse(path);
        System.out.println("Path :");
        int cost = -1;
		for (Node n : path) {
            System.out.print(n.getValue() + " ");
            cost++;
		}
		System.out.println();
		System.out.println("Cost :"+cost);

	}
}
