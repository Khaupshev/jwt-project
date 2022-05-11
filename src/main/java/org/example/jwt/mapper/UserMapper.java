package org.example.jwt.mapper;

import org.example.jwt.config.SpringMapperConfig;
import org.example.jwt.dto.AuthenticationRequest;
import org.example.jwt.dto.UserDto;
import org.example.jwt.model.Role;
import org.example.jwt.model.Status;
import org.example.jwt.model.User;
import org.example.jwt.repository.RoleRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

/**
 * The type User mapper.
 */
@Mapper(config = SpringMapperConfig.class)
public abstract class UserMapper {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Map user.
     *
     * @param userDto
     *         the user dto
     * @return the user
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    @Mapping(target = "role", source = "role",  qualifiedByName = "role")
    public abstract User map(UserDto userDto);

    /**
     * Map user dto.
     *
     * @param user
     *         the user
     * @return the user dto
     */
    @Mapping(target = "role", source = "user.role.status")
    public abstract UserDto map(User user);

    /**
     * To request authentication request.
     *
     * @param userDto
     *         the user dto
     * @return the authentication request
     */
    public abstract AuthenticationRequest toRequest(UserDto userDto);

    /**
     * Map list.
     *
     * @param users
     *         the users
     * @return the list
     */
    public abstract List<UserDto> map(List<User> users);

    /**
     * Gets role.
     *
     * @return the role
     */
    @Named("role")
    public Role getRole(Status status) {
        return roleRepository.getByStatus(status);
    }

    /**
     * Gets encode password.
     *
     * @param password
     *         the password
     * @return the encode password
     */
    @Named("encodePassword")
    public String getEncodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}
