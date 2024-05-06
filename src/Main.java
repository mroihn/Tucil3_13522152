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
        String fileName = "./wordlist/word2.txt";
        Solver solver = new Solver();
        try {
            words = solver.readWordsFromFile(fileName);

            boolean valid = false;
            
            while(!valid) {
                System.out.print("Masukkan Startword:");
                startWord = scanner.nextLine();
                System.out.println("Start Word: " + startWord);
                System.out.print("Masukkan Endword:");
                endWord = scanner.nextLine();
                System.out.println("End Word: " + endWord);
                
                if (startWord.length() == endWord.length()) {
                    valid = true;
                }

                if (!valid) {
                    System.out.println("Masukkan salah, ulangi !!!");
                }
            } 

        } catch (FileNotFoundException e) {
            System.err.println("File '" + fileName + "' tidak ditemukan.");
        }

        // solver.buildGraph(words);
        Map<String, List<String>> wordMap = solver.readHashMapFromFile("./wordlist/graph2.txt");
        // solver.saveMapToFile("graph2.txt");

        System.out.println("Pilih Algoritma:");
        System.out.println("1. UCS");
        System.out.println("2. Greedy Best First Search");
        System.out.println("3. A*");
        String pilihan = scanner.nextLine();

        Node start = new Node(startWord.toLowerCase());
        start.setCost(0);
        List<String> child = wordMap.get(startWord.toLowerCase());
        start.setChild(child);

        Node end = new Node(endWord.toLowerCase());
        end.setCost(0);
        child = wordMap.get(endWord.toLowerCase());
        end.setChild(child);

        if (pilihan.equals("1")) {
            UCS ucs = new UCS();
           
            System.out.println("Finding Shortest Path....");
            long startTime = System.currentTimeMillis();

            ucs.solveUCS(start, end, wordMap);

            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            System.out.println("Execution time: " + executionTime + " ms");
        }
        else if (pilihan.equals("2")) {
            GBFS gbfs = new GBFS();
            
            System.out.println("Finding Shortest Path....");
            long startTime = System.currentTimeMillis();

            gbfs.solveGBFS(start, end, wordMap);

            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            System.out.println("Execution time: " + executionTime + " ms");
        }
        else if (pilihan.equals("3")) {
            AS as = new AS();
            
            System.out.println("Finding Shortest Path....");
            long startTime = System.currentTimeMillis();

            as.solveAS(start, end, wordMap);

            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            System.out.println("Execution time: " + executionTime + " ms");
        }

    }

}