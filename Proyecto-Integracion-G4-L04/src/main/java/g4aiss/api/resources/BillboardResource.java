package g4aiss.api.resources;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import g4aiss.model.Billboard;
import g4aiss.model.Film;
import g4aiss.model.repository.BillboardRepository;
import g4aiss.model.repository.MapBillboardRepository;
	
	@Path("/billboard")
	public class BillboardResource {

		public static BillboardResource _instance=null;
		BillboardRepository repository;
		
		private BillboardResource(){
			repository=MapBillboardRepository.getInstance();
		}
		
		public static BillboardResource getInstance()
		{
			if(_instance==null)
				_instance=new BillboardResource();
			return _instance; 
		}
		
		@GET
		@Produces("application/json")
		public Collection<Billboard> getAll()
		{
			return repository.getAllBillboards();
		}
		
		
		@GET
		@Path("/{id}")
		@Produces("application/json")
		public Billboard get(@PathParam("id") String id)
		{
			Billboard b = repository.getBillboard(id);
			
			if(b == null) {
				throw new NotFoundException("The film with id="+ id +" was not found");	
			}
			
			return b;
		}
		
		@POST
		@Consumes("application/json")
		@Produces("application/json")
		public Response addBillboard(@Context UriInfo uriInfo, Billboard billboard) {
			if(billboard.getName() == null || "".equals(billboard.getName())) {
				throw new BadRequestException("The name of the billboard must not be null");
			}
			
			repository.addBillboard(billboard);
			
			UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
			URI uri = ub.build(billboard.getId());
			ResponseBuilder resp = Response.created(uri);
			resp.entity(billboard);			
			return resp.build();
		}
			
		
		@PUT
		@Consumes("application/json")
		public Response updateBillboard(Billboard billboard) {
			Billboard oldbillboard = repository.getBillboard(billboard.getId());
			if (oldbillboard == null) {
				throw new NotFoundException("The billboard with id="+ billboard.getId() +" was not found");			
			}
			
			
			if (billboard.getFilms()!=null)
				throw new BadRequestException("The films property is not editable.");
			
			// Update name
			if(billboard.getName()!=null)
				oldbillboard.setName(billboard.getName());
			
			if(billboard.getLocation()!=null)
				oldbillboard.setLocation(billboard.getLocation());
			
			return Response.noContent().build();
		}
		
		@DELETE
		@Path("/{id}")
		public Response removeBillboard(@PathParam("id") String id) {
			Billboard toberemoved=repository.getBillboard(id);
	        if (toberemoved == null)
	            throw new NotFoundException("The billboard with id="+ id +" was not found");
	        else
	            repository.deleteBillboard(id);

	        return Response.noContent().build();
		}
		@POST	
		@Path("/{playlistId}/{songId}")
		@Consumes("text/plain")
		@Produces("application/json")
		public Response addFilm(@Context UriInfo uriInfo,@PathParam("billboardId") String billboardId, @PathParam("filmId") String filmId)
		{				
			
			Billboard billboard = repository.getBillboard(billboardId);
			Film film = repository.getFilm(filmId);
			
			if (billboard==null)
				throw new NotFoundException("The billboard with id=" + billboardId + " was not found");
			
			if (film == null)
				throw new NotFoundException("The film with id=" + filmId + " was not found");
			
			if (billboard.getFilm(filmId)!=null)
				throw new BadRequestException("The film is already included in the billboard.");
				
			repository.addFilm(billboardId, filmId);		

			// Builds the response
			UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
			URI uri = ub.build(billboardId);
			ResponseBuilder resp = Response.created(uri);
			resp.entity(billboard);			
			return resp.build();
		}
		
		
		@DELETE
		@Path("/{playlistId}/{songId}")
		public Response removeFilm(@PathParam("billboardId") String billboardId, @PathParam("filmId") String filmId) {
			Billboard billboard = repository.getBillboard(billboardId);
			Film film = repository.getFilm(filmId);
			
			if (billboard==null)
				throw new NotFoundException("The billboard with id=" + billboardId + " was not found");
			
			if (film == null)
				throw new NotFoundException("The film with id=" + filmId + " was not found");
			
			
			repository.removeFilm(billboardId, filmId);		
			
			return Response.noContent().build();
		}
		
	}


