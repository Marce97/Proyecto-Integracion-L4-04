package g4aiss.api.resources;

import java.util.Comparator;

import g4aiss.model.Film;

public class ComparatorDirectorFilm implements Comparator<Film> {

	@Override
	public int compare(Film f1, Film f2) {
		
		return f1.getDirector().compareTo(f2.getDirector());
		
	}

}
//