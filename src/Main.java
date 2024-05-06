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
        String fileName = "../src/wordlist/word.txt";
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
                
                if (startWord.length() == endWord.length()) { // Cek input bener atau nggak
                    valid = true;
                }

                if (!valid) {
                    System.out.println("Panjang startword dan endword harus sama, ulangi masukkan !!!");
                }

                if (!words.contains(startWord.toLowerCase()) || !words.contains(endWord.toLowerCase())) { // Cek kata ada di kamus atau nggak
                    valid = false;
                    System.out.println("Kata tidak ada di dictionary, ulangi masukkan !!!");
                }
            } 

        } catch (FileNotFoundException e) {
            System.err.println("File '" + fileName + "' tidak ditemukan.");
        }

        // solver.buildGraph(words); // Dipakek untuk ngebuat graph

        Map<String, List<String>> wordMap = solver.readHashMapFromFile("../src/wordlist/graph.txt"); // Baca graph dari file
        
        // solver.saveMapToFile("../src/wordlist/graph.txt"); // Dipakek untuk nyimpen graph yang udh dibuat ke file

        System.out.println("Pilih Algoritma:");
        System.out.println("1. UCS");
        System.out.println("2. Greedy Best First Search");
        System.out.println("3. A*");
        String pilihan = scanner.nextLine();

        Node start = new Node(startWord.toLowerCase()); // Buat Node startword
        start.setCost(0);
        List<String> child = wordMap.get(startWord.toLowerCase());
        start.setChild(child);

        Node end = new Node(endWord.toLowerCase()); // Buat Node endword
        end.setCost(0);
        child = wordMap.get(endWord.toLowerCase());
        end.setChild(child);

        if (pilihan.equals("1")) {
            UCS ucs = new UCS();
           
            System.out.println("Finding Shortest Path....");
            long startTime = System.currentTimeMillis();

            ucs.solveUCS(start, end, wordMap); // Selesain pakek UCS

            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            System.out.println("Execution time: " + executionTime + " ms");
        }
        else if (pilihan.equals("2")) {
            GBFS gbfs = new GBFS();
            
            System.out.println("Finding Shortest Path....");
            long startTime = System.currentTimeMillis();

            gbfs.solveGBFS(start, end, wordMap); // Selesain pakek GBFS

            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            System.out.println("Execution time: " + executionTime + " ms");
        }
        else if (pilihan.equals("3")) {
            AS as = new AS();
            
            System.out.println("Finding Shortest Path....");
            long startTime = System.currentTimeMillis();

            as.solveAS(start, end, wordMap); // Selesain pakek ASTAR

            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            System.out.println("Execution time: " + executionTime + " ms");
        }

    }

}