package pl.edu.wat.wcy.invoice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.invoice.dto.UserSimpleDTO;
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
                .orElseThrow(() -> new ResourceNotFoundException("User not exist id: " + userId));
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;
    }

    public ObjectReference createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        userRepository.save(user);
        return new ObjectReference(user.getId());
    }

    public UserDTO updateUser(UserDTO user) {
        User userDs = userRepository.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found id: " + user.getId()));

        userDs.setEmail(user.getEmail());
        userDs.setPassword(user.getPassword()); //zahashowac
        userDs.setName(user.getName());
        userDs.setStreet(user.getStreet());
        userDs.setPostcode(user.getPostcode());
        userDs.setCity(user.getCity());
        userDs.setNip(user.getNip());
        userDs.setRegon(user.getRegon());
        userDs.setPhone(user.getPhone());

        userRepository.save(userDs);
        return user;
    }

}
