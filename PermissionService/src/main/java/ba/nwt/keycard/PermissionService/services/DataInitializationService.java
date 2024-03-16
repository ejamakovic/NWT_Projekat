@Service
@Transactional
public class DataInitializationService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private KeycardRepository keycardRepository;

    @Autowired
    private PermissionKeycardRepository permissionKeycardRepository;

    public void initializeData() {
        // Dodavanje nekoliko unosa u tabelu "permissions"
        Permission permission1 = new Permission();
        permission1.setRole(Role.ADMIN);
        permission1.setRoomId(1);
        permission1.setFloorId(1);
        permission1.setBuildingId(1);
        permissionRepository.save(permission1);

        Permission permission2 = new Permission();
        permission2.setRole(Role.USER);
        permission2.setRoomId(2);
        permission2.setFloorId(2);
        permission2.setBuildingId(2);
        permissionRepository.save(permission2);

        // Dodavanje nekoliko unosa u tabelu "keycards"
        Keycard keycard1 = new Keycard();
        keycardRepository.save(keycard1);

        Keycard keycard2 = new Keycard();
        keycardRepository.save(keycard2);

        // Povezivanje ključnih kartica sa dozvolama
        PermissionKeycard permissionKeycard1 = new PermissionKeycard();
        permissionKeycard1.setPermission(permission1);
        permissionKeycard1.setKeycard(keycard1);
        permissionKeycardRepository.save(permissionKeycard1);

        PermissionKeycard permissionKeycard2 = new PermissionKeycard();
        permissionKeycard2.setPermission(permission2);
        permissionKeycard2.setKeycard(keycard2);
        permissionKeycardRepository.save(permissionKeycard2);
    }
}
