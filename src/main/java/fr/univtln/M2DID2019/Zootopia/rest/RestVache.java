package fr.univtln.M2DID2019.Zootopia.rest;

import fr.univtln.M2DID2019.Zootopia.ejb.GestionVache;
import fr.univtln.M2DID2019.Zootopia.vivants.mammiferes.Vache;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/vache")
@Stateless
public class RestVache {
    @EJB private GestionVache gestionVache;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vache> getVaches() {
        return gestionVache.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public  List<Vache> getVache(@PathParam("id") final String ID) {
        return gestionVache.find(ID);
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteVache(@PathParam("id") final int ID) {
        gestionVache.delete(ID);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("vachec/{nom}")
    public Vache createVache(@PathParam("nom") String nom){
        return gestionVache.create(nom);
    }
}
