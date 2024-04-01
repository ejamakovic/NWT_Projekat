package ba.nwt.keycard.RoomService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FloorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAddFloor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/floors")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"floorNumber\":1, \"buildingId\":1}"))
                .andExpect(status().isOk());
    }

    // Other tests for FloorController...
}