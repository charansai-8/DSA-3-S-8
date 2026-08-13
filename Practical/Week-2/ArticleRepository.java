import java.util.ArrayList;

public class ArticleRepository {

    ArrayList<Article> articles = new ArrayList<>();

    public void addArticle(Article article) {
        articles.add(article);
    }

    public void displayRepository() {
        for (Article article : articles) {
            article.display();
        }
    }
}