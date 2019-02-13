package yalongz.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;



@Controller
public class ConfigurationHelper {
	protected Logger logger = LoggerFactory.getLogger(getClass());
 
    private ResourceBundleMessageSource messageSource;

    public String getProperty(String key) {
        try {
            String msg = messageSource.getMessage(key, null, Constants.DEFAULT_LOCALE);
            return msg != null ? msg.trim() : msg;
        }
        catch (NoSuchMessageException e) {
        	logger.error("Message of key " + key + " NOT found!", e);
            return key;
        }
    }

    public String getProperty(String key, Object[] arg) {
        try {
            String msg = messageSource.getMessage(key, arg, Constants.DEFAULT_LOCALE);
            return msg != null ? msg.trim() : msg;
        }
        catch (NoSuchMessageException e) {
        	logger.error("Message of key " + key + " NOT found!", e);
            return key;
        }
    }

    public String getProperty(String key, String defaultValue) {
        try {
            String msg = messageSource.getMessage(key, null, Constants.DEFAULT_LOCALE);
            return msg != null ? msg.trim() : msg;
        }
        catch (NoSuchMessageException e) {
            return defaultValue;
        }
    }

    public String getProperty(String key, Object[] arg, String defaultValue) {
        try {
            String msg = messageSource.getMessage(key, arg, Constants.DEFAULT_LOCALE);
            return msg != null ? msg.trim() : msg;
        }
        catch (NoSuchMessageException e) {
            return defaultValue;
        }
    }

    public int getIntProperty(String key, int defaultValue) {
        try {
            String s = messageSource.getMessage(key, null, Constants.DEFAULT_LOCALE);
            s = (s != null ? s.trim() : s);
            return Integer.parseInt(s);
        }
        catch (NoSuchMessageException e) {
            return defaultValue;
        }
        catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public int getIntProperty(String key, Object[] arg, int defaultValue) {
        try {
            String s = messageSource.getMessage(key, arg, Constants.DEFAULT_LOCALE);
            s = (s != null ? s.trim() : s);
            return Integer.parseInt(s);
        }
        catch (NoSuchMessageException e) {
            return defaultValue;
        }
        catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public long getLongProperty(String key, long defaultValue) {
        try {
            String s = messageSource.getMessage(key, null, Constants.DEFAULT_LOCALE);
            s = (s != null ? s.trim() : s);
            return Long.parseLong(s);
        }
        catch (NoSuchMessageException e) {
            return defaultValue;
        }
        catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public long getLongProperty(String key, Object[] arg, long defaultValue) {
        try {
            String s = messageSource.getMessage(key, arg, Constants.DEFAULT_LOCALE);
            s = (s != null ? s.trim() : s);
            return Long.parseLong(s);
        }
        catch (NoSuchMessageException e) {
            return defaultValue;
        }
        catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    // -------------------------------------- properties setter
    public void setMessageSource(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
