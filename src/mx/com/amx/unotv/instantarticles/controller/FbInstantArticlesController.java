package mx.com.amx.unotv.instantarticles.controller;

import mx.com.amx.unotv.instantarticles.bo.OperacionesInstantArticlesBO;
import mx.com.amx.unotv.instantarticles.dto.ContentDTO;
import mx.com.amx.unotv.instantarticles.reponse.dto.IdResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("fbInstantArticlesController")
public class FbInstantArticlesController {
	
	private static Logger logger=Logger.getLogger(FbInstantArticlesController.class);
	
	private OperacionesInstantArticlesBO operacionesInstantArticlesBO;
	
	@RequestMapping(value={"insertUpdateArticle2"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, headers={"Accept=application/json"})
	@ResponseBody
	public String insertUpdateArticle2 (@RequestBody ContentDTO contentDTO){
		
		logger.info("insertUpdateArticle2 -- Controller");
		String res="";
		try {
			res=operacionesInstantArticlesBO.insertUpdateArticle2(contentDTO);
			
		} catch (Exception e) {
			
			logger.error(" Error insertUpdateArticle2 [Controller] ",e );
		}
		return res;
	}
	@RequestMapping(value={"insertUpdateArticle"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, headers={"Accept=application/json"})
	@ResponseBody
	public IdResponse insertUpdateArticle (@RequestBody ContentDTO contentDTO){
		
		logger.info("insertUpdateArticle -- Controller");
		IdResponse idResponse=null;
		try {
			idResponse=operacionesInstantArticlesBO.insertUpdateArticle(contentDTO);
			
		} catch (Exception e) {
			
			logger.error(" Error insertUpdateArticle [Controller] ",e );
		}
		return idResponse;
	}
	@RequestMapping(value={"deleteArticle"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, headers={"Accept=application/json"})
	@ResponseBody
	public String deleteArticle (@RequestBody String articleId){
		
		logger.info("deleteArticle -- Controller");
		String respuesta="";
		try {
			respuesta=operacionesInstantArticlesBO.deleteArticle(articleId);
			
		} catch (Exception e) {
			
			logger.error(" Error deleteArticle [Controller] ",e );
		}
		return respuesta;
	}
	
	/**
	 * @return the operacionesInstantArticlesBO
	 */
	public OperacionesInstantArticlesBO getOperacionesInstantArticlesBO() {
		return operacionesInstantArticlesBO;
	}

	/**
	 * @param operacionesInstantArticlesBO the operacionesInstantArticlesBO to set
	 */
	@Autowired
	public void setOperacionesInstantArticlesBO(
			OperacionesInstantArticlesBO operacionesInstantArticlesBO) {
		this.operacionesInstantArticlesBO = operacionesInstantArticlesBO;
	}
	
	
	
}
