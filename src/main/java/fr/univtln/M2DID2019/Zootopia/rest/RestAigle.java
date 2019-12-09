package fr.univtln.M2DID2019.Zootopia.rest;

import fr.univtln.M2DID2019.Zootopia.ejb.GestionAigle;
import fr.univtln.M2DID2019.Zootopia.vivants.oiseaux.Aigle;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/aigle")
@Stateless
public class RestAigle {
    @EJB
    GestionAigle gestionAigle;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Aigle> getAigles() {
        return gestionAigle.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public List<Aigle> getAigle(@PathParam("id") final String ID) {
        return gestionAigle.find(ID);
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteAigle(@PathParam("id") final int ID) {
        gestionAigle.delete(ID);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("aiglec/{nom}")
    public Aigle createAigle(@PathParam("nom") String nom){
        return gestionAigle.create(nom);
    }

//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Aigle updateZoo(){
//        return gestionZoo.update();
//    }
}