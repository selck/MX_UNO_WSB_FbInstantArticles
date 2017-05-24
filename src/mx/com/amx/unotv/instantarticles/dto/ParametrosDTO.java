package mx.com.amx.unotv.instantarticles.dto;

import java.io.Serializable;

public class ParametrosDTO implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	private String access_token;
	private String dominio;
	private String ambiente;
	private String URL_WS;
	private String url_insert_instant_articles;
	private String url_delete_instant_articles;
	private String pCode_noticias;
	
	/**
	 * @return the access_token
	 */
	public String getAccess_token() {
		return access_token;
	}
	/**
	 * @param access_token the access_token to set
	 */
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	/**
	 * @return the dominio
	 */
	public String getDominio() {
		return dominio;
	}
	/**
	 * @param dominio the dominio to set
	 */
	public void setDominio(String dominio) {
		this.dominio = dominio;
	}
	/**
	 * @return the ambiente
	 */
	public String getAmbiente() {
		return ambiente;
	}
	/**
	 * @param ambiente the ambiente to set
	 */
	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}
	/**
	 * @return the uRL_WS
	 */
	public String getURL_WS() {
		return URL_WS;
	}
	/**
	 * @param uRL_WS the uRL_WS to set
	 */
	public void setURL_WS(String uRL_WS) {
		URL_WS = uRL_WS;
	}
	/**
	 * @return the url_insert_instant_articles
	 */
	public String getUrl_insert_instant_articles() {
		return url_insert_instant_articles;
	}
	/**
	 * @param url_insert_instant_articles the url_insert_instant_articles to set
	 */
	public void setUrl_insert_instant_articles(String url_insert_instant_articles) {
		this.url_insert_instant_articles = url_insert_instant_articles;
	}
	/**
	 * @return the url_delete_instant_articles
	 */
	public String getUrl_delete_instant_articles() {
		return url_delete_instant_articles;
	}
	/**
	 * @param url_delete_instant_articles the url_delete_instant_articles to set
	 */
	public void setUrl_delete_instant_articles(String url_delete_instant_articles) {
		this.url_delete_instant_articles = url_delete_instant_articles;
	}
	/**
	 * @return the pCode_noticias
	 */
	public String getpCode_noticias() {
		return pCode_noticias;
	}
	/**
	 * @param pCode_noticias the pCode_noticias to set
	 */
	public void setpCode_noticias(String pCode_noticias) {
		this.pCode_noticias = pCode_noticias;
	}
	
	
	
		
}
