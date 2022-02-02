package spring.utils;

import spring.model.dto.UserDto;
import spring.model.entity.User;

import java.util.List;

public interface UserConverter {

    /**
     * Convert User to UserDto
     *
     * @param user entity from database
     * @return UserDto filled with fields
     */
    UserDto toUserDto(User user);

    /**
     * Convert List of User to List of UserDto
     *
     * @param users list of entity from database
     * @return list of UserDto filled with fields
     */
    List<UserDto> toUserDtos(List<User> users);

    /**
     * Convert UserDto to User
     *
     * @param userDto dto
     * @return User filled with fields
     */
    User toUser(UserDto userDto);

    /**
     * Convert List of UserDto to List of User
     *
     * @param userDtos list of dto
     * @return list of User filled with fields
     */
    List<User> toUsers(List<UserDto> userDtos);

}
