import java.util.ArrayList;

public class ArticleRepository {

    ArrayList<Article> articles = new ArrayList<>();

    public void addArticle(Article article) {
        articles.add(article);
    }

    public void displayRepository() {

        int totalWords = 0;

        System.out.println("======================================");
        System.out.println("      TEXTHACK ARTICLE REPOSITORY");
        System.out.println("======================================");
        System.out.println();

        for (Article article : articles) {
            article.display();
            totalWords += article.wordCount;
        }

        System.out.println("Repository Statistics");
        System.out.println("----------------------");
        System.out.println("Total Articles Loaded : " + articles.size());
        System.out.println("Total Words           : " + totalWords);
    }
}