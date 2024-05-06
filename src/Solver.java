import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

}
