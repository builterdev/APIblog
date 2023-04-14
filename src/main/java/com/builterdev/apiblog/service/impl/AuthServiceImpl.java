package com.builterdev.apiblog.service.impl;

import com.builterdev.apiblog.entity.Role;
import com.builterdev.apiblog.entity.User;
import com.builterdev.apiblog.exception.CustomExceptionBlog;
import com.builterdev.apiblog.exception.ResourceNotFoundException;
import com.builterdev.apiblog.payload.LoginDto;
import com.builterdev.apiblog.payload.RegisterDto;
import com.builterdev.apiblog.repository.RoleRepository;
import com.builterdev.apiblog.repository.UserRepository;
import com.builterdev.apiblog.security.JwtTokenProvider;
import com.builterdev.apiblog.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginDto loginDto) {

         Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;

    }

    @Override
    public String register(RegisterDto registerDto) {

        // add check for username exists in database
        if(userRepository.existsByUsername(registerDto.getUsername())) {
            throw new CustomExceptionBlog(HttpStatus.BAD_REQUEST,"Username is already axists!");
        }

        // add check for email exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())) {
            throw new CustomExceptionBlog(HttpStatus.BAD_REQUEST, "Email is already in use");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();

        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "User registered successfully";

    }

    public String updateRole(String role, String roleToRemove, Long userId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );

        Role findRoleToAdd = roleRepository.findByName(role).orElseThrow(
                () -> new ResourceNotFoundException("Role", "Name", 0)
        );

        Role findRoleToRemove = roleRepository.findByName(roleToRemove).orElseThrow(
                () -> new ResourceNotFoundException("Role", "Name", 0)
        );

        boolean isRemoved = user.getRoles().removeIf(role_search -> role_search.equals(findRoleToRemove));

        if(isRemoved){
            user.getRoles().add(findRoleToAdd);
            userRepository.save(user);
        }
        else {
            throw new CustomExceptionBlog(HttpStatus.BAD_REQUEST, "Role was not deleted, something went wrong!");
        }

        return "Role updated successfully";

    }
}
