import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class GBFS extends Algo{
    public void solveGBFS(Node start, Node end, Map<String, List<String>> wordMap) {
        Queue<Node> q = new LinkedList<Node>();
        List<Node> visited = new ArrayList<Node>();
        boolean found = false;

        q.add(start);

        while (!q.isEmpty() && (found == false)) {
            nodeCnt++;
            Node curr = q.poll();
            visited.add(curr);
            
            if (curr.getValue().equals(end.getValue())) {
                end = curr;
                found = true;
            }

            Node nearest = findShortest(curr.getChilds(), end);

            // Kalo node belum dilihat dan gak ada di queue, tambahin node ke queue
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
}
