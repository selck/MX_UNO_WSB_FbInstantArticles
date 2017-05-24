package mx.com.amx.unotv.instantarticles.llamadas.ws;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mx.com.amx.unotv.instantarticles.dto.ContentDTO;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class LlamadasWS {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	private RestTemplate restTemplate;
	private String URL_WS_BASE="";
	private HttpHeaders headers = new HttpHeaders();
	
	public LlamadasWS(String urlWS) {
		super();
		restTemplate = new RestTemplate();
		ClientHttpRequestFactory factory = restTemplate.getRequestFactory();

	        if ( factory instanceof SimpleClientHttpRequestFactory)
	        {
	            ((SimpleClientHttpRequestFactory) factory).setConnectTimeout( 50 * 1000 );
	            ((SimpleClientHttpRequestFactory) factory).setReadTimeout( 50 * 1000 );
	        }
	        else if ( factory instanceof HttpComponentsClientHttpRequestFactory)
	        {
	            ((HttpComponentsClientHttpRequestFactory) factory).setReadTimeout( 50 * 1000);
	            ((HttpComponentsClientHttpRequestFactory) factory).setConnectTimeout( 50 * 1000);
	            
	        }
	        restTemplate.setRequestFactory( factory );
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        
			URL_WS_BASE = urlWS;
	}
	
	public List<ContentDTO> getRelacionadasbyIdCategoria(ContentDTO contentDTO) {
		ContentDTO[] arrayContentsRecibidos=null;
		ArrayList<ContentDTO> listRelacionadas=null;
		String metodo="getRelacionadasbyIdCategoria";
		String URL_WS=URL_WS_BASE+metodo;
		try {
			logger.info("URL_WS: "+URL_WS);
			HttpEntity<ContentDTO> entity = new HttpEntity<ContentDTO>( contentDTO );
			arrayContentsRecibidos=restTemplate.postForObject(URL_WS, entity, ContentDTO[].class);
			listRelacionadas=new ArrayList<ContentDTO>(Arrays.asList(arrayContentsRecibidos));
			
		} catch(Exception e) {
			logger.error("Error getRelacionadasbyIdCategoria [llamadasWS]: ",e);
		}		
		return listRelacionadas;	
	}
}
