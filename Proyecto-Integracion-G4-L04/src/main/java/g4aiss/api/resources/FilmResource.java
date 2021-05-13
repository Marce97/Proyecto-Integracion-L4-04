package g4aiss.api.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import g4aiss.model.Film;
import g4aiss.model.repository.BillboardRepository;
import g4aiss.model.repository.MapBillboardRepository;

//prueba commit
@Path("/films")
public class FilmResource {

	public static FilmResource _instance=null;
	BillboardRepository repository;	
	
	private FilmResource(){
		repository = MapBillboardRepository.getInstance();
	}
	
	public static FilmResource getInstance()
	{
		if(_instance==null)
			_instance=new FilmResource();
		return _instance; 
	}
	
	@GET
	@Produces("application/json")
	public Collection<Film> getAll(@QueryParam("order") String order, @QueryParam("q") String q,
			@QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit){
		
		List<Film> result = new ArrayList<Film>();
		
		for(Film song: repository.getAllFilms()) {
			if(q == null || song.getTitle().contains(q) || song.getDirector().contains(q) || song.getGenre().contains(q)) {
				result.add(song);
			}
		}
		
		if(order != null) {
			
			if(order.equals("director")) {
				Collections.sort(result, new ComparatorDirectorFilm());
			}
			else if(order.equals("-director")) {
				Collections.sort(result, new ComparatorDirectorFilmReversed());
			}
			
			else if(order.equals("duration")) {
				Collections.sort(result, new ComparatorDurationFilm());
			}
			else if(order.equals("-duration")) {
				Collections.sort(result, new ComparatorDurationFilmReversed());
			}
			
			else if(order.equals("genre")) {
				Collections.sort(result, new ComparatorGenreFilm());
			}
			else if(order.equals("-genre")) {
				Collections.sort(result, new ComparatorGenreFilmReversed());
			}
			
			else if(order.equals("release")) {
				Collections.sort(result, new ComparatorReleaseFilm());
			}
			else if(order.equals("-release")) {
				Collections.sort(result, new ComparatorReleaseFilmReversed());
			}
			
			else {
				throw new BadRequestException("Parámetro de entrada incorrercto");
			}
			
			if(offset==null) {
				if(limit==null) {
					return result;
				}
				else {
					return getAll(order, q, 0, limit);
				}
			}
			else {
				if(limit==null) {
					return getAll(order, q, offset, result.size());
				}
				else {
					return result.subList(offset, limit);
				}
			}
		}
		
		return result;
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Film get(@PathParam("id") String filmId)
	{
		Film f = repository.getFilm(filmId);
		
		if(f == null) {
			throw new NotFoundException("The film with id="+ filmId +" was not found");	
		}
		
		return f;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addSong(@Context UriInfo uriInfo, Film film) {
		if(film.getTitle() == null || "".equals(film.getTitle())) {
			throw new BadRequestException("The name of the film must not be null");
		}
		
		repository.addFilm(film);
		
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(film.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(film);			
		return resp.build();
	}
	
	@PUT
	@Consumes("application/json")
	public Response updateSong(Film film) {
		Film oldfilm = repository.getFilm(film.getId());
		if (oldfilm == null) {
			throw new NotFoundException("The playlist with id="+ film.getId() +" was not found");			
		}
		
		// Update name
		if (film.getTitle()!=null)
			oldfilm.setTitle(film.getTitle());
		
		if(film.getDirector()!=null)
			oldfilm.setDirector(film.getDirector());
		
		if(film.getDuration()!=null)
			oldfilm.setDuration(film.getDuration());
		
		if(film.getGenre()!=null)
			oldfilm.setGenre(film.getGenre());
		
		if(film.getRelease()!=null)
			oldfilm.setRelease(film.getRelease());
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeFilm(@PathParam("id") String filmId) {
		Film toberemoved=repository.getFilm(filmId);
        if (toberemoved == null)
            throw new NotFoundException("The film with id="+ filmId +" was not found");
        else
            repository.deleteFilm(filmId);

        return Response.noContent().build();
	}

}
