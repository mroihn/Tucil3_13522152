import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class AS extends Algo{
    public void solveAS(Node start, Node end, Map<String, List<String>> wordMap) {
		// int pathCost = 0;
		PriorityQueue<Node> pq = new PriorityQueue<Node>(
				new Comparator<Node>() {
					public int compare(Node i, Node j) {
						if (i.getCost() > j.getCost()) {
							return 1;                           // Comperator yg ngebuat node yang memiliki cost terendah memiliki prioritas tertinggi
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
			nodeCnt++;
			Node curr = pq.poll();
			visited.add(curr);

			if (curr.getValue().equals(end.getValue())) { 
				end = curr;
				found = true;
			}

			for (Node n : curr.getChilds()) {

				// Kalo node belum dilihat dan gak ada di queue, tambahin node ke queue
				if (!cekVisited(visited, n) && !inQ(pq, n)) {
					// System.out.println("currCost : " + curr.getCost() + " word " + n.getValue());
					n.setCost(curr.getCost() + 1 + costFromGoal(n.getValue(), end.getValue()));
					n.setParent(curr);
					List<String> child = wordMap.get(n.getValue());
					n.setChild(child);
					pq.add(n);

				}

				// Kalo node ada di queue tapi cost node yang ada di queue lebih besar dibanding yang dicek sekarang, tambahin node ke queue
				else if (inQ(pq, n) && ((curr.getCost() + 1 + costFromGoal(n.getValue(), end.getValue())) < n.getCost())) {
					// System.out.println("currCost : " + curr.getCost() + " previousCost " + n.getCost());
					n.setCost(curr.getCost() + 1 + costFromGoal(n.getValue(), end.getValue()));
					n.setParent(curr);
					List<String> child = wordMap.get(n.getValue());
					n.setChild(child);
					delFromQ(pq, n);
					pq.add(n);
				}
			}
		}
		if (found) {
		    printPath(end);
        }
        else {
            System.out.println("Path Not Found");
        }
	}
}
