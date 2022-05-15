package hr.tvz.grgat.hardwareapp.security.service;

import hr.tvz.grgat.hardwareapp.security.domain.User;

public interface JwtService {

    boolean authenticate(String token);

    String createJwt(User user);

}
