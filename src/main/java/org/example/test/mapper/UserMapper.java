package org.example.test.mapper;

import org.example.test.config.SpringMapperConfig;
import org.example.test.dto.AuthenticationRequest;
import org.example.test.dto.UserDto;
import org.example.test.model.Role;
import org.example.test.model.Status;
import org.example.test.model.User;
import org.example.test.repository.RoleRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Mapper(config = SpringMapperConfig.class)
public abstract class UserMapper {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    @Mapping(target = "role", expression = "java(getRole())")
    public abstract User map(UserDto userDto);

    @Mapping(target = "role", source = "user.role.status")
    public abstract UserDto map(User user);

    @Mapping(target = "userName", source = "userName")
    public abstract AuthenticationRequest toRequest(UserDto userDto);

    public abstract List<UserDto> map(List<User> users);

    /**
     * Не самая лучшая реализация, при каждом добавлении userа лишнее обращение к бд. Как альтернатива можно было просто
     * объявить enum {@link org.example.test.model.Status} как атрибут userа.
     * Из плюсов можно выделить гибкость, при добавлении фич, связанных с сущностью {@link Role}
     */
    @Named("role")
    public Role getRole() {
        return roleRepository.getByStatus(Status.USER);
    }

    @Named("encodePassword")
    public String getEncodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}
