package test;

import com.QAApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = QAApplication.class)
public class TestQA {

    @Test
    public void save() {
        //插入
    }
}
