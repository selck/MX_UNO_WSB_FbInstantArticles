package mx.com.amx.unotv.instantarticles.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import mx.com.amx.unotv.instantarticles.dto.ContentDTO;
import mx.com.amx.unotv.instantarticles.dto.ParametrosDTO;
import mx.com.amx.unotv.instantarticles.dto.RedSocialEmbedPost;
import mx.com.amx.unotv.instantarticles.llamadas.ws.LlamadasWS;

import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class UtilInstantArticles {
	
	private final static Logger logger = Logger.getLogger(UtilInstantArticles.class);
	
	public static void main(String [] args){
		/*System.out.println("=========== Inicia push instant articles============");
		
		String url = "https://graph.facebook.com/v2.8/148786388488561/instant_articles";
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		final String token="EAAFZCdfJk0BsBACAZBciK90ZBNa5AmcV0VcSQCubzQrf4BRJyiqiJpiuIIwmAhvgyoSLyPx1gZAQZBV4ujyK04zGa5VJj68r4A82RQFJYFrC0qhLWpd5b2pksIQEtPBwmNWMRJL4fsM0toxd4OR8WAKhHfMKSDn8ZD";
		urlParameters.add(new BasicNameValuePair("access_token", token));
		urlParameters.add(new BasicNameValuePair("html_source", "<html></html>"));
		urlParameters.add(new BasicNameValuePair("published", "false"));
		urlParameters.add(new BasicNameValuePair("development_mode", "true"));

		try {
			
			post.setEntity(new UrlEncodedFormEntity(urlParameters));

			HttpResponse response = client.execute(post);

			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + post.getEntity());
			System.out.println("Response Code : " +response.getStatusLine().getStatusCode());

			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}

			System.out.println(result.toString());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		try {
			/*DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
			DateFormat df1 = new SimpleDateFormat("YYYY-M-dd'T'hh:mm");
			String string1 = "2001-07-04T12:08:56.235-0700";
			Date result1 = df1.parse(string1);
			System.out.println(result1);*/
			
			String s="pingüino áéíóú ¡hola! ¿Cómo esta el partido del Barça ?";
			System.out.println(StringEscapeUtils.escapeHtml(s));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean createFolders(String carpetaContenido) {
		boolean success = false;
		try {						
			File carpetas = new File(carpetaContenido) ;
			if(!carpetas.exists()) {   
				success = carpetas.mkdirs();					
			} else 
				success = true;							
		} catch (Exception e) {
			success = false;
			logger.error("Ocurrio error al crear las carpetas: ", e);
		} 
		return success;
	}
	public static boolean writeHTML(String rutaHMTL, String HTML) {
		boolean success = false;
		try {
			FileWriter fichero = null;
	        PrintWriter pw = null;
	        try {
				fichero = new FileWriter(rutaHMTL);				
				pw = new PrintWriter(fichero);							
				pw.println(HTML);
				pw.close();
				success = true;
			} catch(Exception e){			
				logger.error("Error writeHTML " + rutaHMTL + ": ", e);
				success = false;
			}finally{
				try{                    			              
					if(null!= fichero)
						fichero.close();
				}catch (Exception e2){
					success = false;
					logger.error("Error al cerrar el file: ", e2);
				}
			}	
		} catch(Exception e) {
			success = false;
			logger.error("Fallo al crear el HTML: ", e);
		}		
		return success;
	}
	public static ParametrosDTO obtenerPropiedades(String properties) {
		ParametrosDTO parametrosDTO = new ParametrosDTO();		 
		try {	    		
			Properties propsTmp = new Properties();
			propsTmp.load(UtilInstantArticles.class.getResourceAsStream( "/general.properties" ));
			String ambiente = propsTmp.getProperty("ambiente");
			String rutaProperties = propsTmp.getProperty(properties.replace("ambiente", ambiente));
			Properties props = new Properties();
			
			props.load(new FileInputStream(new File(rutaProperties)));
			parametrosDTO.setURL_WS(propsTmp.getProperty(ambiente+".URL_WS"));
			parametrosDTO.setDominio(props.getProperty("dominio"));
			parametrosDTO.setAmbiente(ambiente);
			parametrosDTO.setAccess_token(props.getProperty("access_token"));
			parametrosDTO.setUrl_insert_instant_articles(props.getProperty("url_insert_instant_articles"));
			parametrosDTO.setUrl_delete_instant_articles(props.getProperty("url_delete_instant_articles"));
			parametrosDTO.setpCode_noticias(props.getProperty("pCode_noticias"));
		} catch (Exception ex) {
			parametrosDTO = new ParametrosDTO();
			logger.error("No se encontro el Archivo de propiedades: ", ex);			
		}
		return parametrosDTO;
    }
	
	public static String reemplazaPlantilla(String HTML, ContentDTO contentDTO, ParametrosDTO parametrosDTO) 
	{
		try {			
			HTML = HTML.replace("$WCM_URL_PAGE$", parametrosDTO.getDominio()+"/"+contentDTO.getFcTipoSeccion() + "/" + contentDTO.getFcSeccion()+"/"+ contentDTO.getFcIdCategoria()+"/detalle/" +contentDTO.getFcNombre());
		} catch (Exception e) {
			HTML = HTML.replace("$WCM_URL_PAGE$", "");
			logger.error("Error al remplazar $WCM_URL_PAGE$");
		}
		
		try {
			HTML = HTML.replace("$WCM_TITLE_CONTENIDO$",StringEscapeUtils.escapeHtml(contentDTO.getFcTitulo().trim()));
		} catch(Exception e) {
			HTML = HTML.replace("$WCM_TITLE_CONTENIDO$", "");
			logger.error("Error al remplazar $WCM_TITLE_CONTENIDO$");
		}
		
		try {
			HTML = HTML.replace("$WCM_DESCRIPCION_CONTENIDO$", UtilInstantArticles.htmlEncode(contentDTO.getFcDescripcion().trim()));
		} catch(Exception e) {
			HTML = HTML.replace("$WCM_DESCRIPCION_CONTENIDO$", "");
			logger.error("Error al remplazar $WCM_DESCRIPCION_CONTENIDO$");
		}
		try {
			HTML = HTML.replace("$WCM_IMAGE$", parametrosDTO.getDominio()+contentDTO.getFcImgPrincipal());
		} catch (Exception e) {
			HTML = HTML.replace("$WCM_IMAGE$", "");
			logger.error("Error al remplazar $WCM_IMAGE$");
		}
		try {
			HTML = HTML.replace("$WCM_ID_CATEGORIA$", contentDTO.getFcIdCategoria().trim());
		} catch(Exception e) {
			HTML = HTML.replace("$WCM_ID_CATEGORIA$", "");
			logger.error("Error al remplazar $WCM_ID_CATEGORIA$");
		}
		try {
			TimeZone tz = TimeZone.getTimeZone("UTC");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
			df.setTimeZone(tz);
			HTML = HTML.replace("$WCM_FECHA_FB$", df.format(contentDTO.getFdFechaPublicacion()));
		} catch (Exception e) {
			HTML = HTML.replace("$WCM_FECHA_FB$", "");
			logger.error("Error al remplazar $WCM_FECHA_FB$");
		}
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			HTML = HTML.replace("$WCM_FECHA$", format.format(contentDTO.getFdFechaPublicacion()));
		} catch (Exception e) {
			HTML = HTML.replace("$WCM_FECHA$", "");
			logger.error("Error al remplazar $WCM_FECHA$");
		}
		try {
			HTML = HTML.replace("$WCM_HORA$", contentDTO.getFcHora());
		} catch (Exception e) {
			HTML = HTML.replace("$WCM_HORA$", "");
			logger.error("Error al remplazar $WCM_HORA$");
		}
		try {
			String autor = contentDTO.getFcEscribio() == null? "": contentDTO.getFcEscribio();
			HTML = HTML.replace("$WCM_AUTOR$", StringEscapeUtils.escapeHtml(autor).trim());
		} catch (Exception e) {
			HTML = HTML.replace("$WCM_AUTOR$", "");
			logger.error("Error al remplazar $WCM_AUTOR$");
		}
		try {
			String lugar = contentDTO.getFcLugar() == null? "": contentDTO.getFcLugar();
			HTML = HTML.replace("$WCM_LUGAR$", StringEscapeUtils.escapeHtml(lugar));
		} catch (Exception e) {
			HTML = HTML.replace("$WCM_LUGAR$", "");
			logger.error("Error al remplazar $WCM_LUGAR$");
		}
		try {
			String nombreCategoria = contentDTO.getFcNombreCategoria() == null? "": contentDTO.getFcNombreCategoria();
			HTML = HTML.replace("$WCM_CATEGORIA$", StringEscapeUtils.escapeHtml(nombreCategoria));
		} catch (Exception e) {
			HTML = HTML.replace("$WCM_CATEGORIA$", "");
			logger.error("Error al remplazar $WCM_CATEGORIA$");
		}
		try {
			String fuente = contentDTO.getFcFuente() == null? "": contentDTO.getFcFuente();
			HTML = HTML.replace("$WCM_FUENTE$", StringEscapeUtils.escapeHtml(fuente));
		} catch (Exception e) {
			HTML = HTML.replace("$WCM_FUENTE$", "");
			logger.error("Error al remplazar $WCM_FUENTE$");
		}
				
		try{
			StringBuffer mediaImage = new StringBuffer("");
			String galeria = contentDTO.getClGaleriaImagenes() == null?"":contentDTO.getClGaleriaImagenes();
			if(!galeria.trim().equals("")){
			String listSRC[]=StringUtils.substringsBetween(galeria,"src=\"", "\">");
			String listDesc[]=StringUtils.substringsBetween(galeria,"<p>","<u>");
			String listPie[]=StringUtils.substringsBetween(galeria,"<u>","</u>");
				if(listSRC.length == listDesc.length && listSRC.length == listPie.length){
					for (int i = 0; i < listSRC.length; i++) {
						mediaImage.append("<figure data-feedback=\"fb:likes, fb:comments\"> \n");
						mediaImage.append("<img src=\""+parametrosDTO.getDominio()+listSRC[i]+"\"> \n");
						mediaImage.append("<figcaption> \n");
						mediaImage.append("<h1>"+StringEscapeUtils.escapeHtml(listDesc[i])+" </h1> \n");
						mediaImage.append("<cite>"+StringEscapeUtils.escapeHtml(listPie[i])+"</cite> \n");
						mediaImage.append("</figcaption> \n");
						mediaImage.append("</figure> \n");
					}
				}
			}
			HTML = HTML.replace("$WCM_GALERIA$", mediaImage);
		}catch(Exception e){
			HTML = HTML.replace("$WCM_GALERIA$", "");
			logger.error("Error al remplazar $WCM_GALERIA$");
		}
		
		
		try {
			StringBuffer mediaContent = new StringBuffer();
			String IdVideoYouTube = contentDTO.getFcIdVideoYouTube() == null? "":contentDTO.getFcIdVideoYouTube().trim();  
			String IdVideoOoyala = contentDTO.getFcIdVideoOoyala() == null? "" : contentDTO.getFcIdVideoOoyala().trim();
			String IdPlayerVideoOoyala = contentDTO.getFcIdPlayerOoyala() == null? "" : contentDTO.getFcIdPlayerOoyala().trim();
			
			if(!IdVideoYouTube.trim().equals("")){
				
				mediaContent.append(" <figure class=\"op-interactive\"> \n");
				mediaContent.append(" <iframe id=\"ytplayer\" type=\"text/html\" width=\"640\" height=\"360\" src=\"https://www.youtube.com/embed/"+IdVideoYouTube+"\" frameborder=\"0\" allowfullscreen></iframe> \n");
				mediaContent.append(" </figure> \n");
				
			}else if(!IdVideoOoyala.trim().equals("") && !IdPlayerVideoOoyala.trim().equals("")){
				
				String pCode=parametrosDTO.getpCode_noticias();
				String ec=contentDTO.getFcIdVideoOoyala(); //embedCode	The Ooyala embed code (asset or content ID) for the desired video.
				String pbid=contentDTO.getFcIdPlayerOoyala(); //pbid --- A "player branding ID" (or simply "player ID") for a player you have defined in Ooyala Backlot
			    
				mediaContent.append(" <figure class=\"op-interactive\"> \n");
				mediaContent.append("         <iframe width=\"480\" height=\"320\" src=\"http://www.unotv.com/ooyala/iframe.html#ec="+ec+"&amp;pbid="+pbid+"&amp;pcode="+pCode+"\"></iframe> \n");
				mediaContent.append(" </figure> \n");
				
			}
			HTML = HTML.replace("$WCM_VIDEO$", mediaContent.toString());
		} catch (Exception e) {
			HTML = HTML.replace("$WCM_VIDEO$", "");
			logger.error("Error al reemplazar el $WCM_VIDEO$");
		}
		
		try {
			HTML = HTML.replace("$WCM_RTF_CONTENIDO$", cambiaCaracteres(getEmbedPost(contentDTO.getClRtfContenido())));
		} catch (Exception e) {
			HTML = HTML.replace("$WCM_RTF_CONTENIDO$", "");
			logger.error("Error al remplazar $WCM_RTF_CONTENIDO$");
		}
		
		try{
			//logger.debug("Creando las relacionadas al vuelo...");
			
			LlamadasWS llamadasWS= new LlamadasWS(parametrosDTO.getURL_WS());
			List<ContentDTO> listRelacionadas=llamadasWS.getRelacionadasbyIdCategoria(contentDTO);
			if(listRelacionadas!=null && listRelacionadas.size()>0){
				StringBuffer items=new StringBuffer();				
				for (int i = 0; i < 3; i++) {
					
					ContentDTO contentDTORelacionada = listRelacionadas.get(i);
					items.append(" <li><a href=\"http://www.unotv.com"+contentDTORelacionada.getFcUrl()+"\"></a></li> \n");
				}
				
				HTML = HTML.replace("$WCM_LIST_RELACIONADAS$", items);      
			}else{
				
				logger.debug("No hay notas relacionadas: ");
				HTML = HTML.replace("$WCM_LIST_RELACIONADAS$", "");			
			}
		} catch (Exception e) {
			HTML = HTML.replace("$WCM_LIST_RELACIONADAS$", "");
			logger.error("Error al remplazar $WCM_LIST_RELACIONADAS$",e);
		}
		
		return HTML;
	}
	private static String limpiaRedSocial(String inicioBusqueda, String finBusqueda, String id_red_social, String rtfContenido){
		try {
			String [] arrayBetween=StringUtils.substringsBetween(rtfContenido, inicioBusqueda, finBusqueda);
			ArrayList<String> cadenasAReemplazarFirst=new ArrayList<String>();

			for (String cadena : arrayBetween) {
				 if(cadena.contains("["+id_red_social)){
					 cadenasAReemplazarFirst.add(inicioBusqueda + cadena + finBusqueda + "|"+cadena);
				 };
			 }
			 for (String string : cadenasAReemplazarFirst) {
				 rtfContenido=rtfContenido.replace(string.split("\\|")[0], string.split("\\|")[1]);
			}
		} catch (Exception e) {
			logger.error("Error limpiaRedSocial: ",e);
			return rtfContenido;
		}
		return rtfContenido;
	}
	
	private static String getEmbedPost(String RTFContenido){
		try {

			String ini="<p dir=\"ltr\" style=\"text-align: justify;\">";
			String ini2="<p dir=\"ltr\">";
			String fin="</p>";
			
			RTFContenido=limpiaRedSocial(ini, fin, "instagram", RTFContenido);
			RTFContenido=limpiaRedSocial(ini, fin, "twitter", RTFContenido);
			RTFContenido=limpiaRedSocial(ini, fin, "facebook", RTFContenido);
			RTFContenido=limpiaRedSocial(ini, fin, "giphy", RTFContenido);
			
			RTFContenido=limpiaRedSocial(ini2, fin, "instagram", RTFContenido);
			RTFContenido=limpiaRedSocial(ini2, fin, "twitter", RTFContenido);
			RTFContenido=limpiaRedSocial(ini2, fin, "facebook", RTFContenido);
			RTFContenido=limpiaRedSocial(ini2, fin, "giphy", RTFContenido);
			
			String rtfContenido=RTFContenido;
			
			String url, cadenaAReemplazar;
			StringBuffer embedCode;
			HashMap<String,ArrayList<RedSocialEmbedPost>> MapAReemplazar = new HashMap<String,ArrayList<RedSocialEmbedPost>>();
			int num_post_embebidos;
			int contador;
			if(rtfContenido.contains("[instagram")){
				//logger.info("Embed Code instagram");
				ArrayList<RedSocialEmbedPost> listRedSocialEmbedInstagram=new ArrayList<RedSocialEmbedPost>();
				num_post_embebidos=rtfContenido.split("\\[instagram=").length-1;
				contador=1;
				do{
					RedSocialEmbedPost embebedPost=new RedSocialEmbedPost();
					String cadenas=devuelveCadenasPost("instagram", rtfContenido);
					cadenaAReemplazar=cadenas.split("\\|")[0];
					url=cadenas.split("\\|")[1];
					rtfContenido=rtfContenido.replace(cadenaAReemplazar, "");
					embedCode=new StringBuffer();
					embedCode.append("");
					embedCode.append("<figure class=\"op-interactive\">\n");
					embedCode.append("<iframe>\n");
					embedCode.append("<blockquote class=\"instagram-media\" data-instgrm-captioned data-instgrm-version=\"6\">\n");
					embedCode.append("<div>\n");
					embedCode.append("<p><a href=\""+url+"\"></a></p>\n");
					embedCode.append("</div>\n");
					embedCode.append("</blockquote>\n");
					embedCode.append("<script async defer src=\"//platform.instagram.com/en_US/embeds.js\"></script>\n");
					embedCode.append("</iframe>\n");
					embedCode.append("</figure>\n");
					
					embebedPost.setCadena_que_sera_reemplazada(cadenaAReemplazar);
					embebedPost.setRed_social("instagram");
					embebedPost.setCodigo_embebido(embedCode.toString());
					
					listRedSocialEmbedInstagram.add(embebedPost);
					contador ++;
				}while(contador <= num_post_embebidos);
				
				MapAReemplazar.put("instagram", listRedSocialEmbedInstagram);
			}
			if(rtfContenido.contains("[twitter")){
				//logger.info("Embed Code twitter");
				ArrayList<RedSocialEmbedPost> listRedSocialEmbedTwitter=new ArrayList<RedSocialEmbedPost>();
				num_post_embebidos=rtfContenido.split("\\[twitter=").length-1;
				contador=1;
				do{
					RedSocialEmbedPost embebedPost=new RedSocialEmbedPost();
					String cadenas=devuelveCadenasPost("twitter", rtfContenido);
					cadenaAReemplazar=cadenas.split("\\|")[0];
					url=cadenas.split("\\|")[1];
					rtfContenido=rtfContenido.replace(cadenaAReemplazar, "");
					embedCode=new StringBuffer();
							
					embedCode.append(" <figure class=\"op-interactive\">\n");
					embedCode.append(" <iframe>\n");
					embedCode.append(" <blockquote class=\"twitter-tweet\" data-width=\"500\" lang=\"es\"><a href=\""+url+"\"></a></blockquote>\n");
					embedCode.append(" <script type=\"text/javascript\" async defer src=\"//platform.twitter.com/widgets.js\" id=\"twitter-wjs\"></script>\n");
					embedCode.append(" </iframe>\n");
					embedCode.append(" </figure>\n");
					
					embebedPost.setCadena_que_sera_reemplazada(cadenaAReemplazar);
					embebedPost.setRed_social("twitter");
					embebedPost.setCodigo_embebido(embedCode.toString());
					
					listRedSocialEmbedTwitter.add(embebedPost);
					contador ++;
				}while(contador <= num_post_embebidos);
				
				MapAReemplazar.put("twitter", listRedSocialEmbedTwitter);
			
			}
			if(rtfContenido.contains("[facebook")){
				//logger.info("Embed Code facebook");
				ArrayList<RedSocialEmbedPost> listRedSocialEmbedFacebook=new ArrayList<RedSocialEmbedPost>();
				num_post_embebidos=rtfContenido.split("\\[facebook=").length-1;
				contador=1;
				do{
					RedSocialEmbedPost embebedPost=new RedSocialEmbedPost();
					String cadenas=devuelveCadenasPost("facebook", rtfContenido);
					cadenaAReemplazar=cadenas.split("\\|")[0];
					url=cadenas.split("\\|")[1];
					rtfContenido=rtfContenido.replace(cadenaAReemplazar, "");
					embedCode=new StringBuffer();
					
					embedCode.append(" <figure class=\"op-interactive\"> \n");
					embedCode.append(" <iframe> \n");
					embedCode.append("   <div id=\"fb-root\"></div> \n");
					embedCode.append("   <script> \n");
					embedCode.append(" 	(function(d, s, id) { \n");
					embedCode.append(" 	var js, fjs = d.getElementsByTagName(s)[0]; \n");
					embedCode.append(" 	if (d.getElementById(id)) return; \n");
					embedCode.append(" 	js = d.createElement(s); js.id = id; \n");
					embedCode.append(" 	js.src = \"//connect.facebook.net/es_LA/sdk.js#xfbml=1&version=v2.7&appId=523246701133682\"; \n");
					embedCode.append(" 	fjs.parentNode.insertBefore(js, fjs); \n");
					embedCode.append(" 	}(document, 'script', 'facebook-jssdk')); \n");
					embedCode.append("   </script> \n");
					embedCode.append("   <div class=\"facebook-post\"> \n");
					embedCode.append(" 		<div class=\"fb-post\" data-href=\""+url+"\" data-show-text=\"true\" data-width=\"400\" dir=\"ltr\"></div>");
					embedCode.append("   </div> \n");
					embedCode.append(" </iframe> \n");
					embedCode.append(" </figure> \n");
					
					embebedPost.setCadena_que_sera_reemplazada(cadenaAReemplazar);
					embebedPost.setRed_social("facebook");
					embebedPost.setCodigo_embebido(embedCode.toString());
					
					listRedSocialEmbedFacebook.add(embebedPost);
					contador++;;
				}while(contador <= num_post_embebidos);
				
				MapAReemplazar.put("facebook", listRedSocialEmbedFacebook);
			}
			if(rtfContenido.contains("[giphy")){
				//logger.info("Embed Code giphy");
				ArrayList<RedSocialEmbedPost> listRedSocialEmbedGiphy=new ArrayList<RedSocialEmbedPost>();
				num_post_embebidos=rtfContenido.split("\\[giphy=").length-1;
				contador=1;
				do{
					RedSocialEmbedPost embebedPost=new RedSocialEmbedPost();
					String cadenas=devuelveCadenasPost("giphy", rtfContenido);
					//cadenas giphy: [giphy=http://giphy.com/gifs/sassy-batman-ZuM7gif8TCvqU,http://i.giphy.com/rgg2PJ6VJTyPC.gif=giphy]|http://giphy.com/gifs/sassy-batman-ZuM7gif8TCvqU,http://i.giphy.com/rgg2PJ6VJTyPC.gif
					//cadenas giphy: [giphy=http://giphy.com/gifs/superman-funny-wdh1SvEn0E06I,http://i.giphy.com/wdh1SvEn0E06I.gif=giphy]|http://giphy.com/gifs/superman-funny-wdh1SvEn0E06I,http://i.giphy.com/wdh1SvEn0E06I.gif

					cadenaAReemplazar=cadenas.split("\\|")[0];
					url=cadenas.split("\\|")[1];
					rtfContenido=rtfContenido.replace(cadenaAReemplazar, "");
					embedCode=new StringBuffer();
					embedCode=new StringBuffer();
					
					embedCode.append(" <figure data-feedback=\"fb:likes, fb:comments\"> \n");
					embedCode.append(" <img src=\""+url.split("\\,")[1]+"\"> \n");
					embedCode.append(" 	<figcaption class=\"op-vertical-below\"> \n");
					embedCode.append(" 	  <h1>V&iacute;a Giphy</h1> \n");
					embedCode.append(" 	</figcaption> \n");
					embedCode.append(" </figure> \n");
					
					embebedPost.setCadena_que_sera_reemplazada(cadenaAReemplazar);
					embebedPost.setRed_social("giphy");
					embebedPost.setCodigo_embebido(embedCode.toString());
					
					listRedSocialEmbedGiphy.add(embebedPost);
					contador ++;
				}while(contador <= num_post_embebidos);
				
				MapAReemplazar.put("giphy", listRedSocialEmbedGiphy);
			}
			
			
			if(!MapAReemplazar.isEmpty()){
				Iterator<String> iterator_red_social = MapAReemplazar.keySet().iterator();
				String red_social="", codigo_embebido="", cadena_que_sera_reemplazada="";
				while(iterator_red_social.hasNext()){
					red_social = iterator_red_social.next();
			        if(red_social.equalsIgnoreCase("twitter") || red_social.equalsIgnoreCase("facebook") || red_social.equalsIgnoreCase("instagram") 
			        		|| red_social.equalsIgnoreCase("giphy")){
			        	ArrayList<RedSocialEmbedPost> listEmbebidos=MapAReemplazar.get(red_social);
			        	for (RedSocialEmbedPost redSocialEmbedPost : listEmbebidos) {
				        	cadena_que_sera_reemplazada=redSocialEmbedPost.getCadena_que_sera_reemplazada();
				        	codigo_embebido=redSocialEmbedPost.getCodigo_embebido();
				        	RTFContenido=RTFContenido.replace(cadena_que_sera_reemplazada, codigo_embebido);
						}
			        	
			        }
			    } 
			}
			
			try {
				RTFContenido = RTFContenido.replace("h3", "h2");
			} catch (Exception e) {
				logger.error("Error reemplazando h3");
			}
			try {
				RTFContenido = RTFContenido.replace("h4", "h2");
			} catch (Exception e) {
				logger.error("Error reemplazando h4");
			}
			return RTFContenido;
		} catch (Exception e) {
			logger.error("Error getEmbedPost: ",e);
			return RTFContenido;
		}
	}
	
	private static String devuelveCadenasPost(String id_red_social, String rtfContenido){
		String url="", cadenaAReemplazar="", salida="";
		try {
			cadenaAReemplazar=rtfContenido.substring(rtfContenido.indexOf("["+id_red_social+"="), rtfContenido.indexOf("="+id_red_social+"]"))+"="+id_red_social+"]";
			url=cadenaAReemplazar.replace("["+id_red_social+"=", "").replace("="+id_red_social+"]", "");
			salida=cadenaAReemplazar+"|"+url;
		} catch (Exception e) {
			logger.error("Error devuelveCadenasPost: "+e.getMessage()+" -- "+e.getLocalizedMessage());
			return "|";
		}
		return salida;
	}
	
	private static String htmlEncode(final String string) {
		  final StringBuffer stringBuffer = new StringBuffer();
		  for (int i = 0; i < string.length(); i++) {
		    final Character character = string.charAt(i);
		    if (CharUtils.isAscii(character)) {
		      // Encode common HTML equivalent characters
		      stringBuffer.append(
		    		  org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(character.toString()));
		    } else {
		      // Why isn't this done in escapeHtml4()?
		      stringBuffer.append(
		          String.format("&#x%x;",
		              Character.codePointAt(string, i)));
		    }
		  }
		  return stringBuffer.toString();
	}
	
	private static String cambiaCaracteres(String texto) {
		
		texto = texto.replaceAll("á", "&#225;");
        texto = texto.replaceAll("é", "&#233;");
        texto = texto.replaceAll("í", "&#237;");
        texto = texto.replaceAll("ó", "&#243;");
        texto = texto.replaceAll("ú", "&#250;");  
        texto = texto.replaceAll("Á", "&#193;");
        texto = texto.replaceAll("É", "&#201;");
        texto = texto.replaceAll("Í", "&#205;");
        texto = texto.replaceAll("Ó", "&#211;");
        texto = texto.replaceAll("Ú", "&#218;");
        texto = texto.replaceAll("ñ", "&#241;");
        texto = texto.replaceAll("Ñ", "&#209;");
        
        texto = texto.replaceAll("ª", "&#170;");          
        texto = texto.replaceAll("ä", "&#228;");
        texto = texto.replaceAll("ë", "&#235;");
        texto = texto.replaceAll("ï", "&#239;");
        texto = texto.replaceAll("ö", "&#246;");
        texto = texto.replaceAll("ü", "&#252;");    
        texto = texto.replaceAll("Ä", "&#196;");
        texto = texto.replaceAll("Ë", "&#203;");
        texto = texto.replaceAll("Ï", "&#207;");
        texto = texto.replaceAll("Ö", "&#214;");
        texto = texto.replaceAll("Ü", "&#220;");
        
        texto = texto.replaceAll("¿", "&#191;");
        texto = texto.replaceAll("“", "&#8220;");        
        texto = texto.replaceAll("”", "&#8221;");
        texto = texto.replaceAll("‘", "&#8216;");
        texto = texto.replaceAll("’", "&#8217;");
        texto = texto.replaceAll("¡", "&#161;");
        texto = texto.replaceAll("¿", "&#191;");
        texto = texto.replaceAll("°", "&#176;");
        
        texto = texto.replaceAll("Ç", "&#199;");
        texto = texto.replaceAll("ç", "&#231;");
        
        texto = texto.replaceAll("–", "&#8211;");
        texto = texto.replaceAll("—", "&#8212;"); 
        
		return texto;
	}
	
	
}
