package pw.pcshop.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pw.pcshop.services.UserService;
import pw.pcshop.viewModels.UserVM;

@RestController
@RequestMapping(path = "api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ApiResult<List<UserVM>> getAll() {
        try {
            return new ApiResult<List<UserVM>>().OK(userService.getAll());
        } catch (RuntimeException e) {
            return new ApiResult<List<UserVM>>().Error(e.getMessage());
        }
    }

    @PostMapping
    public ApiResult<Void> add(UserVM userVM) {
        try {
            userService.add(userVM);
            return new ApiResult<Void>().OK();
        } catch (RuntimeException e) {
            return new ApiResult<Void>().Error(e.getMessage());
        }
    }
}
