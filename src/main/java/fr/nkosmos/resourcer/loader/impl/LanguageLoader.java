package fr.nkosmos.resourcer.loader.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import fr.nkosmos.resourcer.data.language.I18nListing;
import fr.nkosmos.resourcer.data.language.Language;
import fr.nkosmos.resourcer.loader.ILoader;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@SuppressWarnings("unchecked")
@RequiredArgsConstructor
@ToString
public class LanguageLoader implements ILoader<Language[]> {

	private final static String BASE_PATH = "i18n/";
	private final static Gson gson = new Gson();
	
	private final static Logger logger = LogManager.getLogger("LanguageLoader");
	
	private final ClassLoader classLoader;
	
	@Override
	public Language[] load() throws IOException {
		Enumeration<URL> languageListings = classLoader.getResources(BASE_PATH + "i18n.listing");
		
		Map<String, Language> languages = new HashMap<>();
		
		while(languageListings.hasMoreElements()) {
			I18nListing listing = gson.fromJson(new InputStreamReader(languageListings.nextElement().openStream()), I18nListing.class);
			
			for(String langId : listing.getLanguages()) {
				String langPath = BASE_PATH + langId + "/";
				if(languages.containsKey(langId)) {
					Language lang = languages.get(langId);
					
					for(String file : listing.getFiles()) {
						String filePath = langPath + file;
						lang.getTranslationValues().putAll(this.flattenMap(gson.fromJson(new InputStreamReader(classLoader.getResourceAsStream(filePath)), Map.class), ""));
					}
				} else {
					Language.LanguageBuilder builder = Language.builder();
					builder.languageId(langId);
					
					Map<String, Object> translationValues = new HashMap<>();
					for(String file : listing.getFiles()) {
						String filePath = langPath + file;
						translationValues.putAll(this.flattenMap(gson.fromJson(new InputStreamReader(classLoader.getResourceAsStream(filePath)), Map.class), ""));
					}
					
					builder.translationValues(translationValues);
					
					// TODO: Proper flag textures
					//builder.flagTexture(new Texture("/assets/resourcer/textures/icons/flags/" + langId + ".png"));
					
					languages.put(langId, builder.build());
				}
			}
		}
		
		Language[] languageArr = languages.values().toArray(new Language[languages.values().size()]);
		
		List<String> keys = new ArrayList<>();
		Arrays.stream(languageArr).forEach(l -> {
			l.getTranslationValues().keySet().stream().filter(s -> !keys.contains(s)).forEach(keys::add);
		});
		Arrays.stream(languageArr).forEach(l -> {
			keys.stream().filter(k -> !l.getTranslationValues().containsKey(k)).forEach(k -> {
				logger.warn("Missing translation key \"{}\" for language \"{}\"!", k, l.getLanguageId());
			});
		});
		
		return languageArr;
	}
	
	private Map<String, Object> flattenMap(Map<String, Object> data, String baseKeyString) {
		Map<String, Object> newData = new HashMap<>();
		for(String key : data.keySet()) {
			Object value = data.get(key);
			String newKey = baseKeyString + (baseKeyString.isEmpty() ? "" : ".") + key;
			
			if(value instanceof Map) {
				newData.putAll(flattenMap((Map<String, Object>)value, newKey));
			} else {
				newData.put(newKey, value);
			}
		}
		return newData;
	}
}
