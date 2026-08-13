import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        File folder = new File("Corpus");

        File[] files = folder.listFiles();

        ArticleRepository repository = new ArticleRepository();

        int id = 101;

        for (File file : files) {

            if (file.isFile()) {

                Scanner sc = new Scanner(file);

                String title = "";

                if (sc.hasNextLine()) {
                    title = sc.nextLine();
                }

                String content = "";

                while (sc.hasNextLine()) {
                    content += sc.nextLine() + "\n";
                }

                sc.close();

                int wordCount = content.trim().split("\\s+").length;

                Article article = new Article(id, title, content, wordCount);

                repository.addArticle(article);

                id++;
            }
        }

        repository.displayRepository();
    }
}