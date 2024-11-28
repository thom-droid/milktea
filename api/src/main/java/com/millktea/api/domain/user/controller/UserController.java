package com.millktea.api.domain.user.controller;

import com.millktea.api.domain.user.dto.CommonUserRes;
import com.millktea.api.domain.user.dto.PatchUserReq;
import com.millktea.api.domain.user.dto.SaveUserReq;
import com.millktea.api.domain.user.mapper.UserMapper;
import com.millktea.api.domain.user.service.UserService;
import com.millktea.core.domain.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//TODO:: 인증 토큰으로 로그인 정보 받아올 필요 있음

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
    public CommonUserRes save(@RequestBody @Valid SaveUserReq req) {
        User entityFrom = userMapper.toEntityFrom(req);
        User saved = userService.save(req.getBusinessNo(), entityFrom);
        return userMapper.toDtoFrom(saved);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping
    public void patch(@RequestBody @Valid SaveUserReq req) {
        User entityFrom = userMapper.toEntityFrom(req);
        userService.patch(entityFrom);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/privileges")
    public CommonUserRes updatePrivileges(@RequestBody @Valid PatchUserReq.Privileges req) {
        User entity = userMapper.toEntityFrom(req);
        User updated = userService.updatePrivileges(req.getBusinessNo(), entity);
        return userMapper.toDtoFrom(updated);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/status")
    public CommonUserRes updateStatus(@RequestBody @Valid PatchUserReq.Status req) {
        User entity = userMapper.toEntityFrom(req);
        User updated = userService.updateStatus(req.getBusinessNo(), entity);
        return userMapper.toDtoFrom(updated);
    }

}
