package g4aiss.model;


public class Film {
	
	//mas id
	
	private String id;
	private String title;
	private String director;
	private Integer duration;
	private String genre;
	private String release;
	
	public Film() {
		super();
	}

	public Film(String title, String director, Integer duration, String genre, String release) {
		this.title = title;
		this.director = director;
		this.duration = duration;
		this.genre = genre;
		this.release = release;
	}
	
	public Film(String id, String title, String director, Integer duration, String genre, String release) {
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

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getRelease() {
		return release;
	}

	public void setRelease(String release) {
		this.release = release;
	}
	
	
	
	

}
