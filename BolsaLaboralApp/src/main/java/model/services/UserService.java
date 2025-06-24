package model.services;

import model.models.User;
import model.repository.UserRepository;
import java.util.Optional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserService {
    private final UserRepository userRepo;
    private ProfileService profileService; // Añade esta referencia

    // Modifica el constructor para recibir ProfileService
    public UserService(UserRepository userRepo, ProfileService profileService) {
        this.userRepo = userRepo;
        this.profileService = profileService;
    }

    public void registerUser(User user) {
        userRepo.saveUser(user);
    }

    // Implementa el método faltante
    public Optional<User> findByUsernameOrEmail(String usernameOrEmail) {
        // Primero busca por username
        Optional<User> user = userRepo.findByUsername(usernameOrEmail);

        // Si no encuentra, busca por email
        if (user.isEmpty()) {
            user = userRepo.findByEmail(usernameOrEmail);
        }

        return user;
    }

    // Implementa la verificación de contraseña
    private boolean passwordMatches(User user, String inputPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedInput = md.digest(inputPassword.getBytes());
            String hashedInputStr = bytesToHex(hashedInput);
            return hashedInputStr.equals(user.getPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al verificar contraseña", e);
        }
    }

    // Método auxiliar para convertir bytes a hexadecimal
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public User authenticate(String usernameOrEmail, String password) {
        User user = findByUsernameOrEmail(usernameOrEmail)
                .orElseThrow(() -> new SecurityException("Usuario no encontrado"));

        if (!passwordMatches(user, password)) {
            throw new SecurityException("Contraseña incorrecta");
        }

        // Cargar perfil usando el ProfileService inyectado
        if (profileService != null) {
            user.setProfile(profileService.getProfileByUser(user.getId()).orElse(null));
        }

        return user;
    }
}