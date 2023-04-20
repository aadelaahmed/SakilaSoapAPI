package org.example.controller.api;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.WebServiceException;
import org.example.controller.response.ResponseMessage;
import org.example.mapper.ActorMapper;
import org.example.service.actor.ActorServiceImpl;
import org.example.dto.ActorDto;
import org.example.repository.ActorRepository;


import java.util.List;
import java.util.Optional;



@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
@WebService(name = "actors")
public class ActorController {
    //TODO -> USE IOC spring container HERE
    private final ActorServiceImpl service = new ActorServiceImpl(
            new ActorRepository(), ActorMapper.INSTANCE
    );

    @WebMethod(operationName = "getAllActors")
    @WebResult(name = "actors")
    public List<ActorDto> getAllActors() {
        try {
            List<ActorDto> actorDtos = service.getAll();
            System.out.println(actorDtos.stream().limit(3));
            return actorDtos;
        } catch (Exception e) {
            throw new WebServiceException("Failed to get actors: " + e.getMessage(), e);
        }
    }

    @WebMethod(operationName = "getActorById")
    @WebResult(name = "actorById")
    public ActorDto getActorBy(@WebParam(name = "id") Integer id) {
        try {
            Optional<ActorDto> optionalActorDto = Optional.ofNullable(service.getById(id));
            if (optionalActorDto.isPresent()) {
                return optionalActorDto.get();
            } else {
                throw new WebServiceException("Actor not found");
            }
        } catch (Exception e) {
            throw new WebServiceException("Failed to get actor by ID: " + e.getMessage(), e);
        }
    }
    @WebMethod(operationName = "addActor")
    public ActorDto addActor(@WebParam(name = "actorDto") ActorDto actorDto) {
        try {
            Optional<ActorDto> optionalActorDto = Optional.ofNullable(service.create(actorDto, actorDto.getId()));
            if (optionalActorDto.isPresent()) {
                return optionalActorDto.get();
            }
            throw new WebServiceException("Can't create actor");
        } catch (Exception e) {
            throw new WebServiceException("Failed to create actor: " + e.getMessage(), e);
        }
    }


    @WebMethod(operationName = "updateActorById")
    public ActorDto updateActor(@WebParam(name = "id") Integer id, @WebParam(name = "actorDto") ActorDto actorDto) {
        try {
            actorDto.setId(id);
            boolean res = service.update(id, actorDto);
            if (res) {
                return actorDto;
            } else {
                throw new WebServiceException("Failed to update actor");
            }
        } catch (Exception e) {
            throw new WebServiceException("Failed to update actor: " + e.getMessage(), e);
        }
    }

    @WebMethod(operationName = "deleteActorById")
    public ResponseMessage deleteById(@WebParam(name = "id") Integer id) {
        try {
            service.deleteById(id);
            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.setMessage("Actor's deleted successfully");
            return responseMessage;
        } catch (Exception e) {
            throw new WebServiceException("Failed to delete actor: " + e.getMessage(), e);
        }
    }
}
