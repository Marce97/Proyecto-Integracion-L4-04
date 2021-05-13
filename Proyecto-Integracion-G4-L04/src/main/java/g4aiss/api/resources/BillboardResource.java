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
		@Path("/{title}")
		@Produces("application/json")
		public Billboard get(@PathParam("title") String filmTitle)
		{
			Billboard b = repository.getBillboard(filmTitle);
			
			if(b == null) {
				throw new NotFoundException("The film with title="+ filmTitle +" was not found");	
			}
			
			return b;
		}
		
		@POST
		@Consumes("application/json")
		@Produces("application/json")
		public Response addFilm(@Context UriInfo uriInfo, Film film) {
			if(film.getTitle() == null || "".equals(song.getTitle())) {
				throw new BadRequestException("The name of the song must not be null");
			}
			
			repository.addSong(song);
			
			UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
			URI uri = ub.build(song.getId());
			ResponseBuilder resp = Response.created(uri);
			resp.entity(song);			
			return resp.build();
		}
			
		
		@PUT
		@Consumes("application/json")
		public Response updateFilm(Film film) {
			Film oldfilm = repository.getFilm(film.get());
			if (oldfilm == null) {
				throw new NotFoundException("The billboard with name="+ film.getId() +" was not found");			
			}
			
			// Update name
			if (song.getTitle()!=null)
				oldsong.setTitle(song.getTitle());
			
			if(song.getArtist()!=null)
				oldsong.setArtist(song.getArtist());
			
			if(song.getAlbum()!=null)
				oldsong.setAlbum(song.getAlbum());
			
			if(song.getYear()!=null)
				oldsong.setYear(song.getYear());
			
			return Response.noContent().build();
		}
		
		@DELETE
		@Path("/{id}")
		public Response removeSong(@PathParam("id") String songId) {
			Song toberemoved=repository.getSong(songId);
	        if (toberemoved == null)
	            throw new NotFoundException("The song with id="+ songId +" was not found");
	        else
	            repository.deleteSong(songId);

	        return Response.noContent().build();
		}
		
	}


