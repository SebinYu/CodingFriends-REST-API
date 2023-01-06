package net.skhu.codingFriends.config.auth;

import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// http://localhost:8080/login => 여기서 동작을 안함 왜냐면 formLogin.disable() goqjfutj
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user userEntity = userRepository.findByUsername(username);
        return new PrincipalDetails(userEntity);
    }
}


