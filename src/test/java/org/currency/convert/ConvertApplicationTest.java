package org.currency.convert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConvertApplicationTest {

    static {
        System.setProperty("FIXER_API_KEY", "dummykey");
    }

    @Test
    public void contextLoads() throws Exception {
        // sanity check 
    }
}