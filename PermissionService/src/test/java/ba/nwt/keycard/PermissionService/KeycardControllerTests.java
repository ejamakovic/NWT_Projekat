package ba.nwt.keycard.PermissionService;

import ba.nwt.keycard.PermissionService.controllers.*;
import ba.nwt.keycard.PermissionService.models.*;
import ba.nwt.keycard.PermissionService.services.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class KeycardControllerTests {

    @Autowired
    private KeycardController keycardController;

    @MockBean
    private KeycardService keycardService;

    @MockBean
    private KeycardPermissionService keycardPermissionService;

    @Test
    void testGetAllKeycards() {
        List<Keycard> keycards = new ArrayList<>();
        keycards.add(new Keycard());
        keycards.add(new Keycard());

        when(keycardService.getAllKeycards()).thenReturn(keycards);

        ResponseEntity<List<Keycard>> response = keycardController.getAllKeycards();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetKeycardById() {
        Keycard keycard = new Keycard();
        keycard.setId(1);

        when(keycardService.getKeycardById(1)).thenReturn(keycard);

        ResponseEntity<Keycard> response = (ResponseEntity<Keycard>) keycardController.getKeycardById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getId());
    }

    @Test
    void testUpdateActiveStatus() {
        ResponseEntity<String> response = keycardController.updateActiveStatus(1, true);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Active status updated successfully", response.getBody());

        verify(keycardService, times(1)).updateActiveStatus(1, true);
    }

    @Test
    void testDeleteKeycard() {
        ResponseEntity<String> response = keycardController.deleteKeycard(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Keycard deleted successfully", response.getBody());

        verify(keycardService, times(1)).deleteKeycard(1);
    }

    @Test
    void testGetKeycardPermissions() {
        List<Permission> permissions = new ArrayList<>();
        permissions.add(new Permission());
        permissions.add(new Permission());

        when(keycardPermissionService.getPermissionsByKeycardId(1)).thenReturn(permissions);

        ResponseEntity<List<Permission>> response = (ResponseEntity<List<Permission>>) keycardController.getKeycardPermissions(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testCreateKeycard() {
        Keycard keycard = new Keycard();
        keycard.setId(1);
        keycard.setIsActive(true);

        when(keycardService.createKeycard(any())).thenReturn(keycard);

        ResponseEntity<Keycard> response = (ResponseEntity<Keycard>) keycardController.createKeycard(keycard);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getId());
        assertEquals(true, response.getBody().getIsActive());
    }
}
