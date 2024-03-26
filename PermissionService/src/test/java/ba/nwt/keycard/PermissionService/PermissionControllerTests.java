package ba.nwt.keycard.PermissionService;

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
import ba.nwt.keycard.PermissionService.controllers.*;
import ba.nwt.keycard.PermissionService.models.*;
import ba.nwt.keycard.PermissionService.services.*;

@SpringBootTest
public class PermissionControllerTests {

    @Autowired
    private PermissionController permissionController;

    @MockBean
    private PermissionService permissionService;

    @MockBean
    private KeycardPermissionService keycardPermissionService;

    @Test
    void testGetAllPermissions() {
        List<Permission> permissions = new ArrayList<>();
        permissions.add(new Permission());
        permissions.add(new Permission());

        when(permissionService.getAllPermissions()).thenReturn(permissions);

        ResponseEntity<List<Permission>> response = permissionController.getAllPermissions();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetPermissionById() {
        Permission permission = new Permission();
        permission.setId(1);

        when(permissionService.getPermissionById(1)).thenReturn(permission);

        ResponseEntity<Permission> response = (ResponseEntity<Permission>) permissionController.getPermissionById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getId());
    }

    @Test
    void testGetPermissionsByRoomId() {
        List<Permission> permissions = new ArrayList<>();
        permissions.add(new Permission());
        permissions.add(new Permission());

        when(permissionService.getPermissionsByRoomId(1)).thenReturn(permissions);

        ResponseEntity<List<Permission>> response = (ResponseEntity<List<Permission>>) permissionController.getPermissionsByRoomId(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetPermissionsByFloorId() {
        List<Permission> permissions = new ArrayList<>();
        permissions.add(new Permission());
        permissions.add(new Permission());

        when(permissionService.getPermissionsByFloorId(1)).thenReturn(permissions);

        ResponseEntity<List<Permission>> response = (ResponseEntity<List<Permission>>) permissionController.getPermissionsByFloorId(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetPermissionsByBuildingId() {
        List<Permission> permissions = new ArrayList<>();
        permissions.add(new Permission());
        permissions.add(new Permission());

        when(permissionService.getPermissionsByBuildingId(1)).thenReturn(permissions);

        ResponseEntity<List<Permission>> response = (ResponseEntity<List<Permission>>) permissionController.getPermissionsByBuildingId(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testCreatePermission() {
        Permission permission = new Permission();
        permission.setId(1);
        permission.setRole(Role.valueOf("ADMIN"));
        permission.setRoomId(Integer.valueOf("1"));

        when(permissionService.createPermission(any())).thenReturn(permission);

        ResponseEntity<Permission> response = (ResponseEntity<Permission>) permissionController.createPermission(permission);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getId());
    }

    @Test
    void testGetKeycardsByPermissionId() {
        List<Keycard> keycards = new ArrayList<>();
        keycards.add(new Keycard());
        keycards.add(new Keycard());

        when(keycardPermissionService.getKeycardsByPermissionId(1)).thenReturn(keycards);

        ResponseEntity<List<Keycard>> response = (ResponseEntity<List<Keycard>>) permissionController.getKeycardsByPermissionId(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }
}
