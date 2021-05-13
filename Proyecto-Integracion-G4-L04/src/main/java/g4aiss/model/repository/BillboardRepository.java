package g4aiss.model.repository;

import java.util.Collection;

import g4aiss.model.Billboard;
import g4aiss.model.Film;

public interface BillboardRepository {
		
		//Films
		public void addFilm(Film f);
		public Collection<Film> getAllFilms();
		public Film getFilm(String filmId);
		public void updateFilm(Film f);
		public void deleteFilm(String filmId);
		
		// Billboards
		public void addBillboard(Billboard b);
		public Collection<Billboard> getBillboards();
		public Billboard getBillboard(String billboardId);
		public void updateBillboard(Billboard b);
		public void deleteBillboard(String billboardId);
		public Collection<Billboard> getAll(String billboardId);
		public void addBillboard(String billboardId, String filmId);
		public void removeBillboard(String billboardId, String filmId);

	}


