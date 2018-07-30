package org.currency.convert;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ConvertController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class ConvertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    static {
        System.setProperty("FIXER_API_KEY", "dummykey");
    }

    @Test
    public void shouldReturnOne() throws Exception {
        // "expect the worst and you won't be dissapointed"
        mockMvc.perform(get("/convert?source=EUR&target=EUR&amount=1")).andDo(print()).andExpect(status().isOk())
                .andExpect(
                        content().json("{\"sourceSymbol\":\"EUR\",\"targetSymbol\":\"EUR\",\"value\":1}", false))
                .andDo(document("convert"));
    }
}