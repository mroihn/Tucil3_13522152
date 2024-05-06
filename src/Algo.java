import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Algo {
    public boolean cekVisited(List<Node> visited, Node nodeCek) {
		for (Node n : visited) {
			if (n.getValue() == nodeCek.getValue()) {
				return true;
			}
		}
		return false;

	}
	
	public boolean inQ(PriorityQueue<Node> pq, Node nodeCek) {
		PriorityQueue<Node> copyPq = new PriorityQueue<>(pq);
		while (!copyPq.isEmpty()) {
			Node node = copyPq.poll();
			if (node.getValue() == nodeCek.getValue()) {
				return true;
			}
		}
		return false;
	}
	
    public void delFromQ(PriorityQueue<Node> pq, Node nodeCek) {
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.getValue() == nodeCek.getValue()) {
                pq.remove(node);
            }
        }
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
