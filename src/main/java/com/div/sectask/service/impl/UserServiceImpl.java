package com.div.sectask.service.impl;

import com.div.sectask.entity.RegisterDto;
import com.div.sectask.entity.User;
import com.div.sectask.repository.UserRepository;
import com.div.sectask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void signup(RegisterDto registerDto) {
        String username = registerDto.getUsername();
        Optional<User> existingUser = repository.findUserByUsername(username);
        if (existingUser.isPresent()) {
            throw new UsernameNotFoundException(String.format("User with the email address '%s' already exists.", username));
        }

        String hashedPassword = passwordEncoder.encode(registerDto.getPassword());
        User user = new User(0, registerDto.getUsername(), hashedPassword);
        repository.save(user);
    }
}
