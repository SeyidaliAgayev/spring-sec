package com.div.sectask.service;

import com.div.sectask.entity.RegisterDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void signup(RegisterDto registerDto);
}
