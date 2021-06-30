package co.finecode.metrics;

public class LanguageFileExt {

    private String php = "php";
    private String java = "java";

    /**
     * Returns a file extension type based on language name
     *
     * @param language
     * @return
     */

    public String getLanguageExtension(String language) {
        if(language.toLowerCase().equals("java"))
            return ".java";
        else if(language.toLowerCase().equals("php"))
            return ".php";

        return null;
    }
 }
