package fr.univtln.M2DID2019.Zootopia.ejb;

import fr.univtln.M2DID2019.Zootopia.dao.Dao;
import fr.univtln.M2DID2019.Zootopia.vivants.Zoo;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Stateless
@LocalBean
@Path("/zoo")
public class GestionZoo {

    @Inject private ZooBean zooBean;
    @Inject private Dao dao;

    @GET
    @Path("/zoof/{nom}")
    @Produces("text/plain")
    public String find(@PathParam("nom") String nom){
        return zooBean.find(dao, nom);
    }

    @POST
    @Path("/zooc/{nom}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
    public Zoo create(@PathParam("nom") String nom) {
        return zooBean.create(dao, nom);
    }

//    @PUT
//    @Path("zoouu/")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
//    public void update() {
//        zooBean.update(dao);
//    }
//
//    @PUT
//    @Path("zoou/{nom}/{id}")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
//    public void updateNom(@PathParam("nom") String nom, @PathParam("id") String ID) {
//        zooBean.updateNom(dao, nom, ID);
//    }

    @DELETE
    @Path("zood/{nom}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
    public void delete(@PathParam("nom") String nom) {
        zooBean.delete(dao, nom);
    }
}
