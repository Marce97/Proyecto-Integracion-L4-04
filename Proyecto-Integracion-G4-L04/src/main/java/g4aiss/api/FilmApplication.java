package g4aiss.api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import g4aiss.api.resources.BillboardResource;
import g4aiss.api.resources.FilmResource;

public class FilmApplication extends Application{
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();

	public FilmApplication() {

		singletons.add(BillboardResource.getInstance());
		singletons.add(FilmResource.getInstance());
	}

	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
