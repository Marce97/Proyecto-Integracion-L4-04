package g4aiss.api.resources;

import java.util.Comparator;

import g4aiss.model.Film;

public class ComparatorReleaseFilmReversed implements Comparator<Film> {

	@Override
	public int compare(Film f1, Film f2) {

		return f2.getRelease().compareTo(f1.getRelease());
		
	}

}
