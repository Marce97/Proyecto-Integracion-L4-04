package g4aiss.model.repository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import g4aiss.model.Billboard;
import g4aiss.model.Film;


public class MapBillboardRepository implements BillboardRepository{

	Map<String, Billboard> billboardMap;
	Map<String, Film> filmMap;
	private static MapBillboardRepository instance=null;
	private int index=0;			// Index to create playlists and songs' identifiers.
	
	
	public static MapBillboardRepository getInstance() {
		
		if (instance==null) {
			instance = new MapBillboardRepository();
			instance.init();
		}
		
		return instance;
	}
	
	public void init() {
		
		billboardMap = new HashMap<String,Billboard>();
		filmMap = new HashMap<String,Film>();
		
		// Create film
		Film NinjaTurtles=new Film();
		NinjaTurtles.setTitle("Las tortugas ninjas");
		NinjaTurtles.setDirector("Wilfredo");
		NinjaTurtles.setDuration(Duration.ofMinutes(140));
		NinjaTurtles.setGenre("Acción");
		NinjaTurtles.setRelease(LocalDate.of(15, 10, 2021));
		addFilm(NinjaTurtles);
		
		Film GodzillaVsKong=new Film();
		GodzillaVsKong.setTitle("Godzilla vs Kong");
		GodzillaVsKong.setDirector("Leonardo");
		GodzillaVsKong.setDuration(Duration.ofMinutes(130));
		GodzillaVsKong.setGenre("Acción");
		GodzillaVsKong.setRelease(LocalDate.of(6, 4, 2021));
		addFilm(GodzillaVsKong);
		
		Film Titanic=new Film();
		Titanic.setTitle("Titanic");
		Titanic.setDirector("Annabel");
		Titanic.setDuration(Duration.ofMinutes(200));
		Titanic.setGenre("Romance");
		Titanic.setRelease(LocalDate.of(4, 6, 1995));
		addFilm(Titanic);
		
		Film ElSeñorDeLosAnillos=new Film();
		ElSeñorDeLosAnillos.setTitle("El Señor De Los Anillos");
		ElSeñorDeLosAnillos.setDirector("Peter Jackson");
		ElSeñorDeLosAnillos.setDuration(Duration.ofMinutes(210));
		ElSeñorDeLosAnillos.setGenre("Aventura");
		ElSeñorDeLosAnillos.setRelease(LocalDate.of(15, 10, 1997));
		addFilm(ElSeñorDeLosAnillos);
		
		Film Avatar=new Film();
		Avatar.setTitle("Avatar");
		Avatar.setDirector("Isaac");
		Avatar.setDuration(Duration.ofMinutes(210));
		Avatar.setGenre("Fanatasía");
		Avatar.setRelease(LocalDate.of(7, 1, 2004));
		addFilm(Avatar);
		
		// Create Billboard
		Billboard carteleraModerna=new Billboard();
		carteleraModerna.setName("AISSPlayList");
		carteleraModerna.setLocation("AISS PlayList");
		addBillboard(carteleraModerna);
		
		Billboard carteleraVintage = new Billboard();
		carteleraVintage.setName("Cartelera vintage");
		carteleraVintage.setLocation("Nervion");
		addBillboard(carteleraVintage);
		
		// Add songs to Billboard
		addFilm(carteleraModerna.getId(), NinjaTurtles.getId());
		addFilm(carteleraModerna.getId(), GodzillaVsKong.getId());
		addFilm(carteleraVintage.getId(), Titanic.getId());
		addFilm(carteleraVintage.getId(), ElSeñorDeLosAnillos.getId());
		addFilm(carteleraVintage.getId(), Avatar.getId());
	}
	
	// Billboard related operations
	@Override
	public void addBillboard(Billboard b) {
		String id = "b" + index++;	
		b.setId(id);
		billboardMap.put(id,b);
	}
	
	@Override
	public Collection<Billboard> getBillboards() {
			return billboardMap.values();
	}

	@Override
	public Billboard getBillboard(String id) {
		return billboardMap.get(id);
	}
	
	@Override
	public void updateBillboard(Billboard p) {
		billboardMap.put(p.getId(),p);
	}

	@Override
	public void deleteBillboard(String id) {	
		billboardMap.remove(id);
	}
	

	@Override
	public void addBillboard(String billboardId, String filmId) {
		Billboard playlist = getBillboard(billboardId);
		playlist.addFilm(filmMap.get(filmId));
	}

	@Override
	public Collection<Song> getAll(String playlistId) {
		return getPlaylist(playlistId).getSongs();
	}

	@Override
	public void removeSong(String playlistId, String songId) {
		getPlaylist(playlistId).deleteSong(songId);
	}

	
	// Song related operations
	
	@Override
	public void addSong(Song s) {
		String id = "s" + index++;
		s.setId(id);
		songMap.put(id, s);
	}
	
	@Override
	public Collection<Song> getAllSongs() {
			return songMap.values();
	}

	@Override
	public Song getSong(String songId) {
		return songMap.get(songId);
	}

	@Override
	public void updateSong(Song s) {
		Song song = songMap.get(s.getId());
		song.setTitle(s.getTitle());
		song.setAlbum(s.getAlbum());
		song.setArtist(s.getArtist());
		song.setYear(s.getYear());
	}

	@Override
	public void deleteSong(String songId) {
		songMap.remove(songId);
	}
	
}
