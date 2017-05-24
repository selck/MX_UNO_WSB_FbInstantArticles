package mx.com.amx.unotv.instantarticles.bo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import mx.com.amx.unotv.instantarticles.dto.ContentDTO;
import mx.com.amx.unotv.instantarticles.dto.ParametrosDTO;
import mx.com.amx.unotv.instantarticles.enumHtml.TipoNotaENUM;
import mx.com.amx.unotv.instantarticles.reponse.dto.IdResponse;
import mx.com.amx.unotv.instantarticles.util.UtilInstantArticles;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
@Qualifier("operacionesInstantArticlesBO")
public class OperacionesInstantArticlesBO {
	
	private static Logger logger= Logger.getLogger(OperacionesInstantArticlesBO.class);
	
	public String insertUpdateArticle2(ContentDTO contentDTO){
		StringBuffer result = new StringBuffer();
		try {
			
			ParametrosDTO parametrosDTO=UtilInstantArticles.obtenerPropiedades("ambiente.resources.properties");
			TipoNotaENUM notaENUM = getTipoNota(contentDTO);
			
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(parametrosDTO.getUrl_insert_instant_articles());
			if(parametrosDTO.getAmbiente().equalsIgnoreCase("produccion")){
				//logger.info("Seteando X-Target");
				post.setHeader("X-Target", "graph.facebook.com");
			}
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			if(parametrosDTO.getAmbiente().equalsIgnoreCase("desarrollo")){
				logger.debug("Creando HTML");
				String carpetaContenido="/var/dev-repos/unotv/mobile_first/fb_instant_articles";
				String rutaHMTL="/var/dev-repos/unotv/mobile_first/fb_instant_articles/"+contentDTO.getFcNombre()+".html";
				boolean flag=UtilInstantArticles.createFolders(carpetaContenido);
				if(flag){
					UtilInstantArticles.writeHTML(rutaHMTL, UtilInstantArticles.reemplazaPlantilla(notaENUM.getHTML(), contentDTO, parametrosDTO));
				}
			}
						
			urlParameters.add(new BasicNameValuePair("access_token", parametrosDTO.getAccess_token()));
			urlParameters.add(new BasicNameValuePair("html_source", UtilInstantArticles.reemplazaPlantilla(notaENUM.getHTML(), contentDTO, parametrosDTO)));
			urlParameters.add(new BasicNameValuePair("published", "true"));
			urlParameters.add(new BasicNameValuePair("development_mode", "false"));
			
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
			
			logger.info("Sending 'POST' request to URL : " + parametrosDTO.getUrl_insert_instant_articles());
			
			HttpResponse response = client.execute(post);
			
			logger.info("Response Code : " +response.getStatusLine().getStatusCode());

			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			
			logger.info("Result: "+result);
			
		} catch (Exception e) {
			logger.error("Error en insertUpdateArticle: ",e);
		}
		
		return 	result.toString();
	}
	public IdResponse insertUpdateArticle(ContentDTO contentDTO){
		IdResponse idResponse=new IdResponse();
		try {
			
			ParametrosDTO parametrosDTO=UtilInstantArticles.obtenerPropiedades("ambiente.resources.properties");
			TipoNotaENUM notaENUM = getTipoNota(contentDTO);
			
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(parametrosDTO.getUrl_insert_instant_articles());
			if(parametrosDTO.getAmbiente().equalsIgnoreCase("produccion")){
				//logger.info("Seteando X-Target");
				post.setHeader("X-Target", "graph.facebook.com");
			}
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			if(parametrosDTO.getAmbiente().equalsIgnoreCase("desarrollo")){
				logger.debug("Creando HTML");
				String carpetaContenido="/var/dev-repos/unotv/mobile_first/fb_instant_articles";
				String rutaHMTL="/var/dev-repos/unotv/mobile_first/fb_instant_articles/"+contentDTO.getFcNombre()+".html";
				boolean flag=UtilInstantArticles.createFolders(carpetaContenido);
				if(flag){
					UtilInstantArticles.writeHTML(rutaHMTL, UtilInstantArticles.reemplazaPlantilla(notaENUM.getHTML(), contentDTO, parametrosDTO));
				}
			}
						
			urlParameters.add(new BasicNameValuePair("access_token", parametrosDTO.getAccess_token()));
			urlParameters.add(new BasicNameValuePair("html_source", UtilInstantArticles.reemplazaPlantilla(notaENUM.getHTML(), contentDTO, parametrosDTO)));
			urlParameters.add(new BasicNameValuePair("published", "true"));
			urlParameters.add(new BasicNameValuePair("development_mode", "false"));
			
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
			
			logger.info("Sending 'POST' request to URL : " + parametrosDTO.getUrl_insert_instant_articles());
			
			HttpResponse response = client.execute(post);
			
			logger.info("Response Code : " +response.getStatusLine().getStatusCode());

			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			
			logger.info("Result: "+result);
			Gson respuestaJson = new Gson();
			idResponse=respuestaJson.fromJson(result.toString(), IdResponse.class);
			logger.info("Id--->"+idResponse.getId());
			
		} catch (Exception e) {
			logger.error("Error en insertUpdateArticle: ",e);
		}
		
		return 	idResponse;
	}
	
	public String deleteArticle(String articleId){
		StringBuffer result = new StringBuffer();
		try {
			//https://graph.facebook.com/v2.8/148786388488561/instant_articles //insert
			//https://graph.facebook.com/v2.8/{article-id}?access_token={access-token}
			ParametrosDTO parametrosDTO=UtilInstantArticles.obtenerPropiedades("ambiente.resources.properties");
			
			//String url = "https://graph.facebook.com/v2.8/148786388488561/instant_articles";
			//String url_delete="https://graph.facebook.com/v2.8/$ARTICLE_ID$?access_token="+access_token;
			String url=parametrosDTO.getUrl_delete_instant_articles().replace("$ARTICLE_ID$", articleId);
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(url);
			if(parametrosDTO.getDominio().equalsIgnoreCase("produccion")){
				get.setHeader("X-Target", "graph.facebook.com");
			}
			HttpResponse response = client.execute(get);

			logger.debug("Sending 'GET' request to URL : " + url);
			logger.debug("Response Code : " +response.getStatusLine().getStatusCode());

			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			
		} catch (Exception e) {
			logger.error("Error en deleteArticle: ",e);
		}
		
		return result.toString();
	}
	private static TipoNotaENUM getTipoNota(ContentDTO dto){
		
		String tipoNota=dto.getFcIdTipoNota();
		TipoNotaENUM notaENUM = null;
		
		if(tipoNota.equalsIgnoreCase("imagen")){
			notaENUM=TipoNotaENUM.ARTICULO;
		}else if(tipoNota.equalsIgnoreCase("video")){
			notaENUM=TipoNotaENUM.VIDEO;
		}else if(tipoNota.equalsIgnoreCase("galeria")){
			notaENUM=TipoNotaENUM.GALERIA;
		}else if(tipoNota.equalsIgnoreCase("infografia")){
			notaENUM=TipoNotaENUM.INFOGRAFIA;
		}else if(tipoNota.equalsIgnoreCase("multimedia")){
			notaENUM=TipoNotaENUM.MULTIMEDIA;
		}
		return notaENUM;
		
	}
}
