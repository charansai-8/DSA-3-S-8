import java.io.*;
import java.util.*;

public class Main {

    // Naive Pattern Matching
    static void naiveSearch(String text, String pattern) {

        int count = 0;

        for (int i = 0; i <= text.length() - pattern.length(); i++) {

            int j = 0;

            while (j < pattern.length()
                    && text.charAt(i + j) == pattern.charAt(j)) {
                j++;
            }

            if (j == pattern.length()) {
                System.out.println("Pattern found at position : " + i);
                count++;
            }
        }

        System.out.println("Total occurrences : " + count);
    }

    // Create LPS array for KMP
    static int[] createLPS(String pattern) {

        int[] lps = new int[pattern.length()];

        int len = 0;
        int i = 1;

        while (i < pattern.length()) {

            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {

                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    // KMP Pattern Matching
    static void kmpSearch(String text, String pattern) {

        int[] lps = createLPS(pattern);

        int i = 0;
        int j = 0;
        int count = 0;

        while (i < text.length()) {

            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }

            if (j == pattern.length()) {

                System.out.println("Pattern found at position : " + (i - j));

                count++;

                j = lps[j - 1];

            } else if (i < text.length()
                    && text.charAt(i) != pattern.charAt(j)) {

                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        System.out.println("Total occurrences : " + count);
    }

    public static void main(String[] args) {

        ArticleRepository repository = new ArticleRepository();

        String[] files = {"a1.txt", "a2.txt", "a3.txt"};

        int id = 101;

        // Read articles
        for (String fileName : files) {

            try {

                File file = new File("corpus\\" + fileName);

                BufferedReader br = new BufferedReader(
                    new FileReader(file)
                );

                String title = br.readLine();

                br.readLine();

                StringBuilder content = new StringBuilder();

                String line;

                while ((line = br.readLine()) != null) {
                    content.append(line).append(" ");
                }

                Article article = new Article(
                    id,
                    title,
                    content.toString().trim()
                );

                repository.addArticle(article);

                id++;

                br.close();

            } catch (IOException e) {
                System.out.println("Cannot read file : " + fileName);
            }
        }

        Scanner sc = new Scanner(System.in);

        System.out.println("=====================================");
        System.out.println("       TEXTHACK PATTERN SEARCH");
        System.out.println("=====================================");

        System.out.print("Enter keyword to search : ");
        String pattern = sc.nextLine();

        System.out.println();
        System.out.println("=====================================");
        System.out.println("       NAIVE PATTERN MATCHING");
        System.out.println("=====================================");

        for (Article article : repository.articles) {

            String text = article.title + " " + article.content;

            if (text.toLowerCase().contains(pattern.toLowerCase())) {

                System.out.println();
                System.out.println("Article ID : " + article.id);
                System.out.println("Title     : " + article.title);
                System.out.println();

                naiveSearch(text.toLowerCase(), pattern.toLowerCase());
            }
        }

        System.out.println();
        System.out.println("=====================================");
        System.out.println("       KMP PATTERN MATCHING");
        System.out.println("=====================================");

        for (Article article : repository.articles) {

            String text = article.title + " " + article.content;

            if (text.toLowerCase().contains(pattern.toLowerCase())) {

                System.out.println();
                System.out.println("Article ID : " + article.id);
                System.out.println("Title     : " + article.title);
                System.out.println();

                kmpSearch(text.toLowerCase(), pattern.toLowerCase());
            }
        }

        sc.close();
    }
}