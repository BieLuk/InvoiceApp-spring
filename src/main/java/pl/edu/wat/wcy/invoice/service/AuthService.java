package pl.edu.wat.wcy.invoice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.edu.wat.wcy.invoice.Config.Security.JwtTokenProvider;
import pl.edu.wat.wcy.invoice.dto.UserSimpleDTO;
import pl.edu.wat.wcy.invoice.exception.AppException;
import pl.edu.wat.wcy.invoice.model.Role;
import pl.edu.wat.wcy.invoice.model.RoleName;
import pl.edu.wat.wcy.invoice.model.User;
import pl.edu.wat.wcy.invoice.response.ApiResponse;
import pl.edu.wat.wcy.invoice.response.JwtAuthenticationResponse;
import pl.edu.wat.wcy.invoice.dto.UserLoginDTO;
import pl.edu.wat.wcy.invoice.dto.UserSignUpDTO;
import pl.edu.wat.wcy.invoice.repository.RoleRepository;
import pl.edu.wat.wcy.invoice.repository.UserRepository;

import java.net.URI;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {

    final AuthenticationManager authenticationManager;
    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final PasswordEncoder passwordEncoder;
    final JwtTokenProvider tokenProvider;
    private ModelMapper modelMapper = new ModelMapper();


    public JwtAuthenticationResponse authenticateUser(UserLoginDTO userLoginDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDTO.getUsernameOrEmail(),
                        userLoginDTO.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        User user = userRepository.findByUsernameOrEmail(userLoginDTO.getUsernameOrEmail(), userLoginDTO.getUsernameOrEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User does not exist"));
        UserSimpleDTO userDTO = modelMapper.map(user, UserSimpleDTO.class);
        return new JwtAuthenticationResponse(jwt, userDTO);
//        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, userDTO));
    }

    public ResponseEntity<?> registerUser(UserSignUpDTO userSignUpDTO) {
        if(userRepository.existsByUsername(userSignUpDTO.getUsername())) {
            return new ResponseEntity<>(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(userSignUpDTO.getEmail())) {
            return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(userSignUpDTO.getName(), userSignUpDTO.getUsername(),
                userSignUpDTO.getEmail(), userSignUpDTO.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }




}
