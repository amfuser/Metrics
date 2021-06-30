package co.finecode.metrics.language;

public class LanguageFileExt {

    private final String JAVA_EXTENSION = ".java";
    private final String PHP_EXTENSION = ".php";

    private final String PHP_LANGUAGE = "php";
    private final String JAVA_LANGUAGE = "java";

    /**
     * Returns a file extension type based on language name
     *
     * @param language
     * @return
     */

    public String getLanguageExtension(String language) {
        if(language.toLowerCase().equals(JAVA_LANGUAGE))
            return JAVA_EXTENSION;
        else if(language.toLowerCase().equals(PHP_LANGUAGE))
            return PHP_EXTENSION;

        return null;
    }
 }
