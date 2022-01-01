package resources;

import java.io.IOException;

public interface Resource {
	
	public void load () throws IOException;
	public boolean isLoaded ();
	
}
