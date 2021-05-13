package g4aiss.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Film {
	
	//mas id
	
	private String id;
	private String title;
	private String director;
	private Duration duration;
	private String genre;
	private LocalDate release;
	
	public Film() {
		super();
	}

	public Film(String title, String director, Duration duration, String genre, LocalDate release) {
		this.title = title;
		this.director = director;
		this.duration = duration;
		this.genre = genre;
		this.release = release;
	}
	
	public Film(String id, String title, String director, Duration duration, String genre, LocalDate release) {
		this.id = id;
		this.title = title;
		this.director = director;
		this.duration = duration;
		this.genre = genre;
		this.release = release;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public LocalDate getRelease() {
		return release;
	}

	public void setRelease(LocalDate release) {
		this.release = release;
	}
	
	
	
	

}
