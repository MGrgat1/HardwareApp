package hr.tvz.grgat.hardwareapp.security.service;

import hr.tvz.grgat.hardwareapp.security.command.LoginCommand;
import hr.tvz.grgat.hardwareapp.security.dto.LoginDTO;

import java.util.Optional;

public interface AuthenticationService {

    Optional<LoginDTO> login(LoginCommand command);

}
