package fr.nkosmos.resourcer.data.language;

import lombok.Data;

@Data
public class I18nListing {
	
	/** The listing's project name */
	private final String project;
	
	/** The languages this listings supports */
	private final String[] languages;
	
	/** The translation files */
	private final String[] files;

}
