package com.spingboot.demo.service.interfaces;

import com.spingboot.demo.domain.User;
import com.spingboot.demo.domain.dto.UserResponseDto;
import java.util.List;

public interface UserService extends GenericService<User> {

    List<UserResponseDto> findMostActive(int quantity);
}
