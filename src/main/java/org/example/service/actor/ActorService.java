package org.example.service.actor;

import org.example.dto.ActorDto;
import org.example.mapper.ActorMapper;
import org.example.model.Actor;
import org.example.repository.ActorRepository;
import org.example.service.BaseService;

public class ActorService extends BaseService<Actor,ActorDto> {
    ActorRepository actorRepository;
    ActorMapper actorMapper;
    public ActorService(ActorRepository actorRepository, ActorMapper actorMapper) {
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
