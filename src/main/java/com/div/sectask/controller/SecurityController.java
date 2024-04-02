package com.div.sectask.controller;


import com.div.sectask.entity.RegisterDto;
import com.div.sectask.entity.ResponseDto;
import com.div.sectask.service.impl.UserServiceImpl;
import com.div.sectask.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class SecurityController {
    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userService;
    private final JwtUtil jwtUtil;

    @PostMapping(value = "/login")
    public ResponseEntity<ResponseDto> login(@RequestBody ResponseDto responseDto) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(responseDto.getUsername(), responseDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtil.generateToken(responseDto.getUsername());

        return ResponseEntity.ok(new ResponseDto(responseDto.getUsername(), token));
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody RegisterDto registerDto) {
        userService.signup(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
