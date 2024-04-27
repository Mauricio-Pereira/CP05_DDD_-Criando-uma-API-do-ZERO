package org.fiap.resources;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.fiap.entities.Card;
import org.fiap.entities.Colecoes;
import org.fiap.repositories.ColecoesRepository;
import org.fiap.services.ColecoesService;

import java.util.List;

@Path("colecoes")
public class ColecoesResource {

    private ColecoesRepository colecoesRepository;
    private ColecoesService colecoesService;

    public ColecoesResource() {
        this.colecoesRepository = new ColecoesRepository();
        this.colecoesService = new ColecoesService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Colecoes> ReadAll(){
        return colecoesRepository.ReadAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Colecoes ReadByID(@PathParam("id") int id){
        return colecoesRepository.ReadById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Create(Colecoes colecoes){
        try{
            colecoesService.Create(colecoes);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response UpdateByid(Colecoes colecoes, @PathParam("id") int id ){
        try{
            colecoesService.UpdateById(colecoes, id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response DeleteById( @PathParam("id") int id ){
        if (colecoesRepository.ReadById(id) != null) {
            colecoesRepository.DeleteById(id);
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @GET
    @Path("{id}/cards")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Card> getCardsByCollectionId(@PathParam("id") int id){
        return colecoesRepository.getCardsByCollectionId(id);
    }


}
