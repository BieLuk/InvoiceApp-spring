package pl.edu.wat.wcy.invoice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.invoice.dto.SimpleUserDTO;
import pl.edu.wat.wcy.invoice.dto.UserDTO;
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

    @PostMapping
    public SingleResponse<ObjectReference> createUser(@RequestBody SimpleUserDTO simpleUserDTO){
        return new SingleResponse<>(userService.createUser(simpleUserDTO));
    }

}
