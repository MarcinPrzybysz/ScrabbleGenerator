import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    Dictionary dictionary = new Dictionary();
    ArrayList<String> permutations = new ArrayList<>();

    Main() {
        loadDictionary();
    }

    public void loadDictionary() {
        long startTime = System.nanoTime();


        String filePath = System.getProperty("user.dir") + "\\Dictionary\\dictionary.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                dictionary.insert(line);
            }
        } catch (IOException e) {
            System.out.println("ERROR in loading dictionary");
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;

        System.out.println("Succesfully loaded dictionary! Loaded in: " + duration + "ms");
    }

    public void userInteraction() {


        Scanner scanner = new Scanner(System.in);
        ArrayList<String> permutations;


        System.out.println("Type available scrabble letters:");
        String input = scanner.nextLine();

        permutations = getPermutations("", input);

        System.out.println("Number of permutations: " + permutations.size());


        for (int i = 0; i < permutations.size(); i++) {

            if (dictionary.find(permutations.get(i))) {
                System.out.println(permutations.get(i));
            }
        }
    }

    private ArrayList<String> getPermutations(String prefix, String str) {
        int n = str.length();
        if (n == 0) permutations.add(prefix);
        else {
            for (int i = 0; i < n; i++) {
                if (prefix.length() > 0 && !permutations.contains(prefix)) {
                    permutations.add(prefix);
                }
                getPermutations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
            }
        }
        return permutations;
    }


}

