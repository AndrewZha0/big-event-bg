package com.kenmi.bigevent.api.facade;

import com.kenmi.bigevent.api.dto.UserDTO;
import com.kenmi.bigevent.api.request.UpdateUserRequest;
import com.kenmi.bigevent.api.response.ResultResponse;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Validated
@RequestMapping("/user")
public interface UserFacade {

    @PostMapping("/register")
    ResultResponse<Void> register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password);

    @PostMapping("/login")
    ResultResponse<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password);

    @GetMapping
    ResultResponse<UserDTO> getUser();

    @PutMapping
    ResultResponse<Void> updateUser(@RequestBody @Validated UpdateUserRequest user);

    @PatchMapping("/pwd")
    ResultResponse<Void> updateUserPwd(@RequestBody Map<String, String> params);

    @PatchMapping("/avatar")
    ResultResponse<Void> updateUserPic(String avatarUrl);
}
