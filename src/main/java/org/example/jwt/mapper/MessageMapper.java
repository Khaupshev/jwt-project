package org.example.jwt.mapper;

import org.example.jwt.config.SpringMapperConfig;
import org.example.jwt.dto.MessageDto;
import org.example.jwt.model.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(config = SpringMapperConfig.class)
public interface MessageMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "message", source = "message")
    Message map(String message);

    List<Message> map(List<String> messages);

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
