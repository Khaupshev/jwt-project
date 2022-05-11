package org.example.jwt.mapper;

import org.example.jwt.config.SpringMapperConfig;
import org.example.jwt.dto.MessageDto;
import org.example.jwt.model.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The interface Message mapper.
 */
@Mapper(config = SpringMapperConfig.class)
public interface MessageMapper {

    /**
     * Map message.
     *
     * @param message
     *         the message
     * @return the message
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "message", source = "message")
    Message map(String message);

    /**
     * Map list.
     *
     * @param messages
     *         the messages
     * @return the list
     */
    List<Message> map(List<String> messages);

    /**
     * Map message dto.
     *
     * @param messages
     *         the messages
     * @param userName
     *         the user name
     * @return the message dto
     */
    default MessageDto map(List<Message> messages, String userName) {
        return MessageDto
                .builder()
                .messages(
                        messages
                                .stream()
                                .map(Message::getMessage)
                                .collect(Collectors.toList())
                )
                .userName(userName)
                .build();

    }


}
