package com.hasim.springboot.neo4j.example.service;

import com.hasim.springboot.neo4j.example.dto.PositionDto;
import com.hasim.springboot.neo4j.example.mapper.PositionMapper;
import com.hasim.springboot.neo4j.example.repository.PositionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Hasim Mollah
 */
@Service
@Slf4j
public class PositionService {
    private final PositionRepository positionRepository;
    private final PositionMapper positionMapper;
    PositionService(PositionRepository positionRepository,
                    PositionMapper positionMapper){
        this.positionRepository = positionRepository;
        this.positionMapper = positionMapper;
    }

    public List<PositionDto> byName(String name){
        return positionRepository.findByName(name).stream().map(position->positionMapper.toDto(position)).toList();
    }

    public PositionDto save(PositionDto positionDto){
        return positionMapper.toDto(positionRepository.save(positionMapper.toEntity(positionDto)));
    }

}
