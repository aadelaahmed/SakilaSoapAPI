package org.example.service.actor;

import org.example.dto.ActorDto;

import java.util.List;

public interface ActorService {
        List<ActorDto> getAllActors();
        ActorDto getActorById(Integer id);
        ActorDto createActor(ActorDto actor);
        void deleteActor(Integer id);
        ActorDto updateActor(Integer id, ActorDto actorDetails);
}
