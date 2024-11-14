package com.millktea.api.domain.user.controller;

import com.millktea.api.domain.user.dto.SaveUserReq;
import com.millktea.api.domain.user.dto.SaveUserRes;
import com.millktea.api.domain.user.mapper.UserMapper;
import com.millktea.api.domain.user.service.UserService;
import com.millktea.core.domain.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/user")
@RestController
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SaveUserRes save(@RequestBody @Valid SaveUserReq req) {
        User entityFrom = userMapper.toEntityFrom(req);
        User saved = userService.save(req.getBusinessNo(), entityFrom);
        return userMapper.toDtoFrom(saved);
    }

}
