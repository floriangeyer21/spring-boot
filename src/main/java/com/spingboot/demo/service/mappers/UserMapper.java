package com.spingboot.demo.service.mappers;

import com.spingboot.demo.domain.User;
import com.spingboot.demo.domain.dto.ReviewDto;
import com.spingboot.demo.domain.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private static final String DEFAULT_PASSWORD = "1111";

    public User mapReviewDtoToUser(ReviewDto dto) {
        return User.builder()
                .profileName(dto.getProfileName())
                .internalUserId(dto.getUserId())
                .password(DEFAULT_PASSWORD)
                .build();
    }

    public UserResponseDto mapUserToResponseDto(User user) {
        return UserResponseDto.builder()
                .profileName(user.getProfileName())
                .build();
    }
}
