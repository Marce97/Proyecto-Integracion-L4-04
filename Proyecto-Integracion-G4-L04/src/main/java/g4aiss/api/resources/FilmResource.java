package g4aiss.api.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.jboss.resteasy.spi.BadRequestException;

import aiss.api.resources.ComparatorAlbumSong;
import aiss.api.resources.ComparatorAlbumSongReversed;
import aiss.api.resources.ComparatorArtistSong;
import aiss.api.resources.ComparatorArtistSongReversed;
import aiss.api.resources.ComparatorYearSong;
import aiss.api.resources.ComparatorYearSongReversed;
import aiss.model.Song;
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
			
			else if(order.equals("genre")) {
				Collections.sort(result, new ComparatorGenreFilm());
			}
			else if(order.equals("-genre")) {
				Collections.sort(result, new ComparatorGenreFilmReversed());
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

}
