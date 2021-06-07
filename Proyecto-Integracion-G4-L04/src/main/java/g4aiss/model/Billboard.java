package g4aiss.model;

import java.util.ArrayList;
import java.util.List;

public class Billboard {

	private String id;
	private String name;
	private String location; 
	private List<Film> films;

	public Billboard() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> f) {
		films = f;
	}

	public Film getFilm(String title) {
		if (films == null)
			return null;

		Film film = null;
		for (Film f : films)
			if (f.getTitle().equals(title)) {
				film = f;
				break;
			}

		return film;
	}

	public void addFilm(Film f) {
		if (films == null)
			films = new ArrayList<Film>();
		films.add(f);
	}

	public void deleteFilm(Film f) {
		films.remove(f);
	}

	public void deleteFilm(String title) {
		Film f = getFilm(id);
		if (f != null)
			films.remove(f);
	}

}
