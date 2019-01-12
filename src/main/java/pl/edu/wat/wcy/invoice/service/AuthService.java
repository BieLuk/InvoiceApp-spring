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

import javax.validation.Valid;
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


    public JwtAuthenticationResponse authenticateUser(@Valid UserLoginDTO userLoginDTO) {

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
    }

    public ApiResponse registerUser(@Valid UserSignUpDTO userSignUpDTO) {
        if(userRepository.existsByUsername(userSignUpDTO.getUsername())) {
            return new ApiResponse(false, "Podana nazwa użytkownika jest zajęta");
        }

        if(userRepository.existsByEmail(userSignUpDTO.getEmail())) {
            return new ApiResponse(false, "Podany adres email jest zajęty");
        }

        User user = new User(userSignUpDTO.getName(), userSignUpDTO.getUsername(),
                userSignUpDTO.getEmail(), userSignUpDTO.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        userRepository.save(user);
        return new ApiResponse(true, "Użytkownik zarejestrowany pomyślnie");
    }





}
