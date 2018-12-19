package pl.edu.wat.wcy.invoice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.invoice.dto.UserDTO;
import pl.edu.wat.wcy.invoice.model.User;
import pl.edu.wat.wcy.invoice.repository.UserRepository;
import pl.edu.wat.wcy.invoice.response.ObjectReference;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private ModelMapper modelMapper = new ModelMapper();
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDTO getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist id: " + userId));
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;
    }

    public ObjectReference createUser(UserDTO userDTO) {
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        User user = modelMapper.map(userDTO, User.class);
        userRepository.save(user);
        return new ObjectReference(user.getId());
    }

    public UserDTO updateUser(UserDTO user) {
        User userDs = userRepository.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found id: " + user.getId()));
        modelMapper.map(user, userDs);

        userRepository.save(userDs);
        return user;
    }

}
