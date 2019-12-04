package fr.univtln.M2DID2019.Zootopia.rest;

import fr.univtln.M2DID2019.Zootopia.ejb.GestionZoo;
import fr.univtln.M2DID2019.Zootopia.vivants.Zoo;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/zoo")
@Stateless
public class RestZoo {
    @EJB private GestionZoo gestionZoo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Zoo> getZoos() {
        return gestionZoo.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Zoo getZoo(@PathParam("id") final String ID) {
        return gestionZoo.find(ID);
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteZoo(@PathParam("id") final String ID) {
        gestionZoo.delete(ID);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("zooc/{nom}")
    public Zoo createZoo(@PathParam("nom") String nom){
        return gestionZoo.create(nom);
    }

//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Zoo updateZoo(){
//        return gestionZoo.update();
//    }
}
