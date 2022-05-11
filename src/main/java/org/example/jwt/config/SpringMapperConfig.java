package org.example.jwt.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.NullValueMappingStrategy;

/**
 * The interface Spring mapper config.
 */
@MapperConfig(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface SpringMapperConfig {
}
