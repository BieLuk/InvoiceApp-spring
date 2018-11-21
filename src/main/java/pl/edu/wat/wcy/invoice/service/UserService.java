package pl.edu.wat.wcy.invoice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.invoice.dto.SimpleUserDTO;
import pl.edu.wat.wcy.invoice.dto.UserDTO;
import pl.edu.wat.wcy.invoice.model.User;
import pl.edu.wat.wcy.invoice.repository.UserRepository;
import pl.edu.wat.wcy.invoice.response.ObjectReference;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public UserDTO getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exisst id = " + userId));
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;
    }

    public ObjectReference createUser(SimpleUserDTO simpleUserDTO) {
        User user = modelMapper.map(simpleUserDTO, User.class);
        userRepository.save(user);
        return new ObjectReference(user.getId());
    }

}
