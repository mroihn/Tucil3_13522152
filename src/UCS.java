import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class UCS {
	public void solveUCS(Node start, Node end, Map<String, List<String>> wordMap) {
		// int pathCost = 0;
		PriorityQueue<Node> pq = new PriorityQueue<Node>(
				new Comparator<Node>() {
					public int compare(Node i, Node j) {
						if (i.getCost() > j.getCost()) {
							return 1;
						} else if (i.getCost() < j.getCost()) {
							return -1;
						} else {
							return 0;
						}
					}
				});

		List<Node> visited = new ArrayList<Node>();
		boolean found = false;

		pq.add(start);

		while (!pq.isEmpty() && (found == false)) {
			// printPq(pq);
			Node curr = pq.poll();
			visited.add(curr);

			if (curr.getValue().equals(end.getValue())) {
				end = curr;
				found = true;
			}

			for (Node n : curr.getChilds()) {

				if (!cekVisited(visited, n) && !inQ(pq, n)) {
					// System.out.println("currCost : " + curr.getCost() + " word " + n.getValue());
					n.setCost(curr.getCost() + 1);
					n.setParent(curr);
					List<String> child = wordMap.get(n.getValue());
					n.setChild(child);
					pq.add(n);

				}

				else if (inQ(pq, n) && ((curr.getCost() + 1) < n.getCost())) {
					System.out.println("currCost : " + curr.getCost() + " previousCost " + n.getCost());
					n.setCost(curr.getCost() + 1);
					n.setParent(curr);
					List<String> child = wordMap.get(n.getValue());
					n.setChild(child);
					delFromQ(pq, n);
					pq.add(n);
				}
			}
		}
		// Node p = end.getParent();
		// System.out.println("bapak west : "+p.getValue());
		Solver s = new Solver();
		s.printPath(end);
	}
	
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
	
	public void printPq(PriorityQueue<Node> pq) {
        PriorityQueue<Node> copyPq = new PriorityQueue<>(pq);
        while (!copyPq.isEmpty()) {
            Node node = copyPq.poll();
            System.out.println("Word: " + node.getValue() + ", Path Cost: " + node.getCost());
        }
    }
}