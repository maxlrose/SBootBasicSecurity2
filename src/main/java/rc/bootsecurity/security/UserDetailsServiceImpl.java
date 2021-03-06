package rc.bootsecurity.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rc.bootsecurity.db.UserRepository;
import rc.bootsecurity.model.User;
import rc.bootsecurity.service.MyService;

import java.util.Optional;

@Configuration
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = Optional.ofNullable(userRepository.findByUsername(s)).orElseThrow(() -> new UsernameNotFoundException(s));
        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        return userDetails;
    }
}
