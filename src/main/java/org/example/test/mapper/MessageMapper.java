package org.example.test.mapper;

import org.example.test.config.SpringMapperConfig;
import org.example.test.dto.MessageDto;
import org.example.test.model.Message;
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
