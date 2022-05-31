package hr.tvz.grgat.hardwareapp.security.service;

import hr.tvz.grgat.hardwareapp.security.command.LoginCommand;
import hr.tvz.grgat.hardwareapp.security.domain.User;
import hr.tvz.grgat.hardwareapp.security.dto.LoginDTO;
import hr.tvz.grgat.hardwareapp.security.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthenticationServiceImpl(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    /**
     * Extract the validated username from the input command object and find an entry in the user database corresponding
     * to the entered username. Then, extract the validated password from the command object and compare it to the password
     * in the database entry. If the passwords don't match, return an empty optional. If the passwords match, create a
     * jwt (java web token) corresponding to the user that's logging in.
     * @param command
     * @return
     */
    @Override
    public Optional<LoginDTO> login(LoginCommand command) {
        Optional<User> user = userRepository.findByUsername(command.getUsername());

        if (user.isEmpty() || !isMatchingPassword(command.getPassword(), user.get().getPassword())) {
            return Optional.empty();
        }

        return Optional.of(
                new LoginDTO(jwtService.createJwt(user.get()))
        );
    }

    /**
     * Checks if the password entered by the user corresponds to the password extracted from the database
     * @param rawPassword
     * @param encryptedPassword
     * @return
     */
    private boolean isMatchingPassword(String rawPassword, String encryptedPassword) {
        // TODO - implementirati provjeru odgovara li lozinka, koju je unio korisnik, enkriptiranoj lozinki u bazi
        System.out.println("[AuthenticationServiceImpl] Entered isMatchingPassword");
        if (BCrypt.checkpw(rawPassword, encryptedPassword)) {
            System.out.println("[AuthenticationServiceImpl] Password match");
            return true;
        } else {
            System.out.println("[AuthenticationServiceImpl] Password not a match");
            return false;
        }
        //throw new UnsupportedOperationException();
    }
}
