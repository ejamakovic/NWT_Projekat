package ba.nwt.keycard.RoomService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BuildingControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Transactional
    void testAddBuilding() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/buildings/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Building1\"}"))
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()))
                .andExpect(status().isOk());
    }

    // Other tests for BuildingController...
}