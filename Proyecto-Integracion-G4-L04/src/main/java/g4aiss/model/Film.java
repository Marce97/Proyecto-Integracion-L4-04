package g4aiss.model;

import java.time.Duration;
import java.time.LocalTime;

public class Film {
	
	private String title;
	private String director;
	private Duration duration;
	private String genre;
	private LocalTime release;
	
	public Film() {
		super();
	}

	public Film(String title, String director, Duration duration, String genre, LocalTime release) {
		super();
		this.title = title;
		this.director = director;
		this.duration = duration;
		this.genre = genre;
		this.release = release;
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

	public LocalTime getRelease() {
		return release;
	}

	public void setRelease(LocalTime release) {
		this.release = release;
	}
	
	
	
	

}
