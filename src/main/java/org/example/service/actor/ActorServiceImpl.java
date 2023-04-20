package org.example.service.actor;

import org.example.dto.ActorDto;
import org.example.mapper.ActorMapper;
import org.example.mapper.BaseMapper;
import org.example.model.Actor;
import org.example.repository.ActorRepository;
import org.example.repository.BaseRepository;
import org.example.service.BaseService;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ActorServiceImpl extends BaseService<Actor,ActorDto> {
    ActorRepository actorRepository;
    ActorMapper actorMapper;
    public ActorServiceImpl(ActorRepository actorRepository, ActorMapper actorMapper) {
        super(actorRepository, actorMapper);
        this.actorRepository = actorRepository;
        this.actorMapper = actorMapper;
    }


    @Override
    protected Class<Actor> getEntityClass() {
        return Actor.class;
    }

    @Override
    protected Class<ActorDto> getDtoClass() {
        return ActorDto.class;
    }

}
