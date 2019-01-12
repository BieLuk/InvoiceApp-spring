package pl.edu.wat.wcy.invoice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.invoice.dto.UserDTO;
import pl.edu.wat.wcy.invoice.dto.UserSimpleDTO;
import pl.edu.wat.wcy.invoice.response.ListResponse;
import pl.edu.wat.wcy.invoice.response.ObjectReference;
import pl.edu.wat.wcy.invoice.response.SingleResponse;
import pl.edu.wat.wcy.invoice.service.UserService;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public SingleResponse<UserDTO> getUser(@PathParam("userId") Long userId){
        return new SingleResponse<>(userService.getUser(userId));
    }

    @PostMapping("/sign-up")
    public SingleResponse<ObjectReference> createUser(@RequestBody UserDTO userDTO){
        return new SingleResponse<>(userService.createUser(userDTO));
    }

    @PutMapping
    public SingleResponse<UserDTO> updateUser(@RequestBody UserDTO user) {
        return new SingleResponse<>(userService.updateUser(user));
    }

    @GetMapping(value = "/all")
    public ListResponse<UserSimpleDTO> getAll() {
        return new ListResponse<>(userService.getAllUsers());
    }

    @DeleteMapping(value = "/delete")
    public SingleResponse<Boolean> deleteClient(@PathParam("userId") Long userId) {
        return new SingleResponse<>(userService.deleteUser(userId));
    }

}
