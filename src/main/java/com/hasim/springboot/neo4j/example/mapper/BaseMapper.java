package com.hasim.springboot.neo4j.example.mapper;

import com.hasim.springboot.neo4j.example.dto.BaseDto;
import com.hasim.springboot.neo4j.example.entity.BaseEntity;
import org.mapstruct.Mapper;


/**
 * @author Hasim Mollah
 */
@Mapper(componentModel = "spring")
public interface BaseMapper {

    BaseDto toDto(BaseEntity entity);

    BaseEntity toEntity(BaseDto dto);
}
