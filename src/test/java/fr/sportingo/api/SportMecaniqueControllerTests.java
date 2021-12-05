package fr.sportingo.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class SportMecaniqueControllerTests
{
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetMechanicalsSports() throws Exception
    {
        mockMvc.perform(get("/mechanical-sport"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$[0].label", is("VTT XC")));
    }
}
