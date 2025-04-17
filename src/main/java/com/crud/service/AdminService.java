package com.crud.service;

import com.crud.dto.ResponseDto;
import com.crud.entity.User;
import com.crud.enums.UserRole;
import com.crud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public List<ResponseDto> trackAllUsers() {
        logger.info("Fetching all users with roles USER1 and USER2");
        List<User> users = userRepository.findByRoleIn(List.of(UserRole.USER1, UserRole.USER2));
        logger.info("Fetched {} users with roles USER1 and USER2", users.size());

        return users.stream()
                .map(user -> mapper.map(user, ResponseDto.class))
                .toList();
    }
}
