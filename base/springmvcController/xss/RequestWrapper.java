package com.grgbanking.aptoto.filter.xss;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestWrapper  extends HttpServletRequestWrapper{

	private String apostrophe = "&#39;";//,
	  
	  public RequestWrapper(HttpServletRequest paramHttpServletRequest)
	  {
	    super(paramHttpServletRequest);
	  }
	  
	  public RequestWrapper(HttpServletRequest paramHttpServletRequest, String paramString)
	  {
	    super(paramHttpServletRequest);
	    this.apostrophe = paramString;
	  }
	  
	  public String[] getParameterValues(String paramString)
	  {
	    String[] arrayOfString1 = super.getParameterValues(paramString);
	    if (arrayOfString1 == null) {
	      return null;
	    }
	    int i = arrayOfString1.length;
	    String[] arrayOfString2 = new String[i];
	    for (int j = 0; j < i; j++) {
	      arrayOfString2[j] = cleanXSS(arrayOfString1[j]);
	    }
	    return arrayOfString2;
	  }
	  
	  public String getParameter(String paramString)
	  {
	    String str = super.getParameter(paramString);
	    if (str == null) {
	      return null;
	    }
	    return cleanXSS(str);
	  }
	  
	  public String getHeader(String paramString)
	  {
	    String str = super.getHeader(paramString);
	    if (str == null) {
	      return null;
	    }
	    return cleanXSS(str);
	  }
	  
	  private String cleanXSS(String paramString)
	  {
	    if (paramString == null) {
	      return "";
	    }
	    String str = paramString;
	    str = str.replaceAll("", "");
	    Pattern localPattern = Pattern.compile("<script>(.*?)</script>", 2);
	    str = localPattern.matcher(str).replaceAll("");
	    localPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\'(.*?)\\'", 42);
	    str = localPattern.matcher(str).replaceAll("");
	    localPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", 42);
	    str = localPattern.matcher(str).replaceAll("");
	    localPattern = Pattern.compile("</script>", 2);
	    str = localPattern.matcher(str).replaceAll("");
	    localPattern = Pattern.compile("<script(.*?)>", 42);
	    str = localPattern.matcher(str).replaceAll("");
	    localPattern = Pattern.compile("eval\\((.*?)\\)", 42);
	    str = localPattern.matcher(str).replaceAll("");
	    localPattern = Pattern.compile("expression\\((.*?)\\)", 42);
	    str = localPattern.matcher(str).replaceAll("");
	    localPattern = Pattern.compile("javascript:", 2);
	    str = localPattern.matcher(str).replaceAll("");
	    localPattern = Pattern.compile("vbscript:", 2);
	    str = localPattern.matcher(str).replaceAll("");
	    localPattern = Pattern.compile("onload(.*?)=", 42);
	    str = localPattern.matcher(str).replaceAll("");
	    str = str.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
	    str = str.replaceAll("'", this.apostrophe);
	    str = str.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	    str.replaceAll("javascript", "");
	    return str;
	  }

}
