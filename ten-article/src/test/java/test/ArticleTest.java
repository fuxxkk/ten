package test;

import com.ArticleApplication;
import com.entity.Article;
import com.service.ArticleService;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import util.IdUtil;

import java.util.Date;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArticleApplication.class)
public class ArticleTest {

    @Autowired
    private ArticleService articleService;

    @Test
    public void saveArticle() throws InterruptedException {
        Random random = new Random();
        Observable.range(1,30).observeOn(Schedulers.io()).forEach(num->{
            String id = IdUtil.getId() + "";
            Article article = new Article(id, "", "1", "title" + num, "content" + num, "image" + num, new Date(), null,
                    random.nextInt(2), random.nextInt(2), 0, 0, 0,
                    random.nextInt(2), "", "", random.nextInt(2) + "");
            articleService.save(article);
        });
        System.out.println("finish!...");
        Thread.sleep(30000);
    }
}
