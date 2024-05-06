import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String startWord = "";
        String endWord = "";
        List<String> words = new ArrayList<String>();
        String fileName = "word2.txt";
        Solver solver = new Solver();
        try {
            words = solver.readWordsFromFile(fileName);

            if (!words.isEmpty()) {
                System.out.print("Masukkan Startword:");
                startWord = scanner.nextLine();
                System.out.println("Start Word: " + startWord);
                System.out.print("Masukkan Endword:");
                endWord = scanner.nextLine();
                System.out.println("End Word: " + endWord);
                // System.out.println("Panjang list (jumlah elemen): " + words.size());
            } 

        } catch (FileNotFoundException e) {
            System.err.println("File '" + fileName + "' tidak ditemukan.");
        }

        // solver.buildGraph(words);
        Map<String, List<String>> wordMap = solver.readHashMapFromFile("graph2.txt");
        // solver.saveMapToFile("graph2.txt");

        System.out.println("Pilih Algoritma:");
        System.out.println("1. UCS");
        System.out.println("2. Greedy Best First Search");
        System.out.println("3. A*");
        String pilihan = scanner.nextLine();

        if (pilihan.equals("1")) {
            UCS ucs = new UCS();
            Node start = new Node(startWord.toLowerCase());
            start.setCost(0);
            List<String> child = wordMap.get(startWord.toLowerCase());
            start.setChild(child);

            Node end = new Node(endWord.toLowerCase());
            end.setCost(0);
            child = wordMap.get(endWord.toLowerCase());
            end.setChild(child);
            System.out.println("Finding Shortest Path....");
            ucs.solveUCS(start, end, wordMap);
        }
        else if (pilihan.equals("2")) {
            GBFS gbfs = new GBFS();
            Node start = new Node(startWord.toLowerCase());
            start.setCost(0);
            List<String> child = wordMap.get(startWord.toLowerCase());
            start.setChild(child);

            Node end = new Node(endWord.toLowerCase());
            end.setCost(0);
            child = wordMap.get(endWord.toLowerCase());
            end.setChild(child);
            System.out.println("Finding Shortest Path....");
            gbfs.solveGBFS(start, end, wordMap);
        }

    }

}