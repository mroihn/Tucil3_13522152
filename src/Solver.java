import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Solver {
    private Map<String, List<String>> wordMap;

    public List<String> readWordsFromFile(String fileName) throws FileNotFoundException {
        List<String> words = new ArrayList<>();
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String word = scanner.nextLine().trim();
            if (!word.isEmpty()) {
                words.add(word);
            }
        }
        scanner.close();
        return words;
    }

    // Method untuk memilih kata secara acak dari list kata
    public String pickRandomWord(List<String> words) {
        Random random = new Random();
        int randomIndex = random.nextInt(words.size());
        return words.get(randomIndex);
    }

    public boolean isNeighbor(String word1, String word2) {
        int diff = 0;
        word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();

        if (word1.length() != word2.length()) {
            return false;
        }

        for (int i = 0; i < word1.length(); i++)
        {
            if (word1.charAt(i) != word2.charAt(i))
            {
                diff++;
            }
        }
        if (diff == 1) {
            return true;
        }
        return false;
    }

    public void buildGraph(List<String> words) {
        wordMap = new HashMap<String, List<String>>();
        for (String word : words) {
            System.out.println(word);
            List<String> neighbors = new ArrayList<String>();

            for (String word2 : words) {

                if (isNeighbor(word, word2)) {
                    neighbors.add(word2);
                }
                wordMap.put(word, neighbors);
            }

        }
    }

    public void saveMapToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Map.Entry<String, List<String>> entry : wordMap.entrySet()) {
                String key = entry.getKey();
                List<String> values = entry.getValue();
                if (values.size() > 0) {
                    // Tulis kunci
                    writer.println(key);

                    writer.println(values.size());

                    // Tulis nilai-nilai sebagai string yang dipisahkan koma
                    for (String value : values) {
                        writer.print(value + " ");
                    }

                    // Tambahkan baris baru setelah setiap entri
                    writer.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Map<String, List<String>> readHashMapFromFile(String filename) {
        wordMap = new HashMap<>();
        System.out.print("Load graph....");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Memisahkan baris menjadi kunci dan nilai-nilai
                // String[] parts = line.split(":");
                String key = line.toLowerCase();
                // System.out.println(key);
                line = reader.readLine();
                line = reader.readLine();
                String[] valuesArray = line.toLowerCase().split(" ");

                // Menambahkan nilai-nilai ke dalam list
                List<String> values = new ArrayList<>();
                Collections.addAll(values, valuesArray);

                // Menambahkan kunci dan nilai-nilai ke dalam HashMap
                wordMap.put(key, values);
            }
            // printHashMap(wordMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        return wordMap;
    }

    public void printHashMap(Map<String, List<String>> hashMap) {
        // Iterasi melalui setiap entri dalam HashMap
        for (Map.Entry<String, List<String>> entry : hashMap.entrySet()) {
            String key = entry.getKey(); // Mendapatkan kunci
            List<String> values = entry.getValue(); // Mendapatkan nilai

            // Mencetak kunci
            System.out.println(key + ":");

            // Mencetak nilai-nilai
            for (String value : values) {
                System.out.print(value + " ");
            }

            // Pindah ke baris baru untuk entri berikutnya
            System.out.println();
        }
    }

    public void printPath(Node target) {
		List<Node> path = new ArrayList<Node>();
		for (Node node = target; node != null; node = node.getParent()) {
			path.add(node);
			// System.out.println(node.getValue());
		}

		Collections.reverse(path);
		System.out.println("Path :");
		for (Node n : path) {
			System.out.print(n.getValue() + " ");
		}
		System.out.println();
		System.out.println("Cost :"+String.valueOf(target.getCost()));

	}
}
