package fr.nkosmos.resourcer.loader;

import java.io.IOException;

public interface ILoader<T> {

	T load() throws IOException;
	
}
