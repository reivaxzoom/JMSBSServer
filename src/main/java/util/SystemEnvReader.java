package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class SystemEnvReader
{
  private Map<String, String> env;
  
  public SystemEnvReader()
  {
    this.env = System.getenv();
  }
  
  public String readConfigPath()
  {
    return (String)this.env.get("CONECTOR_CONFIG_PATH");
  }
  
  public Properties getResourceAsStream()
    throws IOException
  {
    InputStream is = getClass().getResourceAsStream("/conector.properties");
    Properties properties = new Properties();
    properties.load(is);
    return properties;
  }
  
  public Properties getFileAsStream()
    throws IOException
  {
    String fileName = String.format("%s/conector.properties", new Object[] { readConfigPath() });
    InputStream is = new FileInputStream(fileName);
    Properties properties = new Properties();
    properties.load(is);
    return properties;
  }
}
