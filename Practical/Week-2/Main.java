import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        ArticleRepository repository = new ArticleRepository();

        String[] files = {"a1.txt", "a2.txt", "a3.txt"};

        int id = 101;

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
        System.out.println("      TEXTHACK QUERY PROCESSOR");
        System.out.println("=====================================");

        System.out.print("Enter keyword to search : ");
        String keyword = sc.nextLine();

        System.out.println();
        System.out.println("Matching Articles");
        System.out.println();

        boolean found = false;

        for (Article article : repository.articles) {

            if (article.title.toLowerCase().contains(keyword.toLowerCase())
                    || article.content.toLowerCase().contains(keyword.toLowerCase())) {

                article.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching articles found.");
        }

        sc.close();
    }
}