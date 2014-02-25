package rt.textbean.dev;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/*
 * @Author rahultejwani
 */
public class propertyBean {
	private String SourcePath;
	private String LexiconPath;
	private String FeaturePath;
	private String AdverbPath;
	public propertyBean()
	{
		Properties prop = new Properties();
    	InputStream input = null;
     
    	try {
     
    		input = new FileInputStream("./config/config.properties");
     
    		// load a properties file
    		prop.load(input);
    		SourcePath = prop.getProperty("SourcePath");
    		LexiconPath = prop.getProperty("LexiconPath");
    		FeaturePath = prop.getProperty("FeatureFilePath");
    		setAdverbPath(prop.getProperty("AdverbPath"));
    		
     
    	} catch (IOException ex) {
    		ex.printStackTrace();
    	} finally {
    		if (input != null) {
    			try {
    				input.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}
	}
	
	public String getSourcePath()
	{
		return this.SourcePath;
	}

	public String getLexiconPath() {
		return LexiconPath;
	}

	public String getFeaturePath() {
		return FeaturePath;
	}

	public String getAdverbPath() {
		return AdverbPath;
	}

	private void setAdverbPath(String adverbPath) {
		AdverbPath = adverbPath;
	}

	

	
}
