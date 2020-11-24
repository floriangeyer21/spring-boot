package com.spingboot.demo.service.impl;

import com.spingboot.demo.domain.RoleName;
import com.spingboot.demo.domain.User;
import com.spingboot.demo.domain.dto.ReviewDto;
import com.spingboot.demo.domain.dto.UserResponseDto;
import com.spingboot.demo.repository.RoleRepository;
import com.spingboot.demo.repository.UserRepository;
import com.spingboot.demo.service.interfaces.UserService;
import com.spingboot.demo.service.mappers.UserMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public User add(ReviewDto dto) {
        return userRepository.save(userMapper.mapReviewDtoToUser(dto));
    }

    @Override
    public List<User> addAll(List<ReviewDto> dtoList) {
        List<User> users = new ArrayList<>();
        for (ReviewDto dto : dtoList) {
            User user = userMapper.mapReviewDtoToUser(dto);
            user.setRoles(Set.of(roleRepository.getRoleByRoleName(RoleName.USER)));
            users.add(user);
        }
        userRepository.saveAll(users);
        return users;
    }

    @Override
    public User get(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<UserResponseDto> findMostActive(int quantity) {
        return userRepository.findMostActive(PageRequest.of(0, quantity));
    }
}
