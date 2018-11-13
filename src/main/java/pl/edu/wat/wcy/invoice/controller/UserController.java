package pl.edu.wat.wcy.invoice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.invoice.dto.UserDTO;
import pl.edu.wat.wcy.invoice.model.User;
import pl.edu.wat.wcy.invoice.response.ObjectReference;
import pl.edu.wat.wcy.invoice.response.SingleResponse;
import pl.edu.wat.wcy.invoice.service.UserService;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/{id}")
    public SingleResponse<UserDTO> getUser(@PathVariable("id") Long id){
        return new SingleResponse<>(userService.getUser(id));
    }

    @PostMapping
    public SingleResponse<ObjectReference> createUser(@RequestBody UserDTO userDTO){
        return new SingleResponse<>(userService.createUser(userDTO));
    }

}
