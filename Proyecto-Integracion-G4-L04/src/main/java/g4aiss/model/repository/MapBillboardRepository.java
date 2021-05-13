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
		NinjaTurtles.setTitle("The Ninja Turtles");
		NinjaTurtles.setDirector("Wilfredo");
		NinjaTurtles.setDuration(Duration.ofMinutes(190));
		NinjaTurtles.setGenre("Acción");
		NinjaTurtles.setRelease(LocalDate.of(15, 10, 2021));
		addFilm(NinjaTurtles);
		
		Film GodzillaVsKong=new Film();
		GodzillaVsKong.setTitle("Godzilla vs Kong");
		GodzillaVsKong.setDirector("Leonardo");
		GodzillaVsKong.setDuration(Duration.ofMinutes(220));
		GodzillaVsKong.setGenre("Acción");
		GodzillaVsKong.setRelease(LocalDate.of(6, 4, 2021));
		addFilm(GodzillaVsKong);
		
		Film NinjaTurtles=new Film();
		NinjaTurtles.setTitle("The Ninja Turtles");
		NinjaTurtles.setDirector("Wilfredo");
		NinjaTurtles.setDuration(Duration.ofMinutes(190));
		NinjaTurtles.setGenre("Acción");
		NinjaTurtles.setRelease(LocalDate.of(15, 10, 2021));
		addFilm(NinjaTurtles);
		
		Film NinjaTurtles=new Film();
		NinjaTurtles.setTitle("The Ninja Turtles");
		NinjaTurtles.setDirector("Wilfredo");
		NinjaTurtles.setDuration(Duration.ofMinutes(190));
		NinjaTurtles.setGenre("Acción");
		NinjaTurtles.setRelease(LocalDate.of(15, 10, 2021));
		addFilm(NinjaTurtles);
		
		Film NinjaTurtles=new Film();
		NinjaTurtles.setTitle("The Ninja Turtles");
		NinjaTurtles.setDirector("Wilfredo");
		NinjaTurtles.setDuration(Duration.ofMinutes(190));
		NinjaTurtles.setGenre("Acción");
		NinjaTurtles.setRelease(LocalDate.of(15, 10, 2021));
		addFilm(NinjaTurtles);
		
		// Create playlists
		Playlist japlaylist=new Playlist();
		japlaylist.setName("AISSPlayList");
		japlaylist.setDescription("AISS PlayList");
		addPlaylist(japlaylist);
		
		Playlist playlist = new Playlist();
		playlist.setName("Favourites");
		playlist.setDescription("A sample playlist");
		addPlaylist(playlist);
		
		// Add songs to playlists
		addSong(japlaylist.getId(), rollingInTheDeep.getId());
		addSong(japlaylist.getId(), one.getId());
		addSong(japlaylist.getId(), smellLikeTeenSpirit.getId());
		addSong(japlaylist.getId(), losingMyReligion.getId());
		
		addSong(playlist.getId(), losingMyReligion.getId());
		addSong(playlist.getId(), gotye.getId());
	}
	
	// Playlist related operations
	@Override
	public void addPlaylist(Playlist p) {
		String id = "p" + index++;	
		p.setId(id);
		playlistMap.put(id,p);
	}
	
	@Override
	public Collection<Playlist> getAllPlaylists() {
			return playlistMap.values();
	}

	@Override
	public Playlist getPlaylist(String id) {
		return playlistMap.get(id);
	}
	
	@Override
	public void updatePlaylist(Playlist p) {
		playlistMap.put(p.getId(),p);
	}

	@Override
	public void deletePlaylist(String id) {	
		playlistMap.remove(id);
	}
	

	@Override
	public void addSong(String playlistId, String songId) {
		Playlist playlist = getPlaylist(playlistId);
		playlist.addSong(songMap.get(songId));
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
