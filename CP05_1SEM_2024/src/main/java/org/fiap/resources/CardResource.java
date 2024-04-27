package org.fiap.resources;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.fiap.entities.Card;
import org.fiap.repositories.CardRepository;
import org.fiap.services.CardService;

import java.util.List;

@Path("card")
public class CardResource {

    private CardRepository cardRepository;
    private CardService cardService;

    public CardResource(){
        this.cardRepository = new CardRepository();
        this.cardService = new CardService();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Card> ReadAll() {
        return cardRepository.ReadAllCards();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Create(Card card){
        try{
            cardService.Create(card);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("{codCard}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response UpdateByCodCard(Card card, @PathParam("codCard") String codCard ){
        try{
            cardService.UpdateByCodCard(card, codCard);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @GET
    @Path("{codCard}")
    public Card ReadByCodCard(@PathParam("codCard") String codCard){
        return cardRepository.ReadByCodCard(codCard);
    }

    @DELETE
    @Path("{codCard}")
    public Response DeleteByCodCard( @PathParam("codCard") String codCard ){
        if (cardRepository.ReadByCodCard(codCard) != null) {
            cardRepository.DeleteByCodCard(codCard);
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

}
