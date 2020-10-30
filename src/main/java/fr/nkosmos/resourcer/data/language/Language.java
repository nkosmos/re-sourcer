package fr.nkosmos.resourcer.data.language;

import java.util.Map;

import fr.nkosmos.resourcer.data.texture.Texture;
import lombok.Builder;
import lombok.Data;

public @Data @Builder class Language {

	/** The language's id */
	private final String languageId;
	
	/** The language's translation map */
	private final Map<String, Object> translationValues;
	
	/** The language's flag texture */
	private final Texture flagTexture;
	
}
