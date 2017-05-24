package mx.com.amx.unotv.instantarticles.enumHtml;

public enum TipoNotaENUM {
	
	ARTICULO,
	GALERIA,
	INFOGRAFIA,
	MULTIMEDIA,
	VIDEO;
	
	
	public String getHTML(){
		String html="";
		switch (this) {
			case ARTICULO:
				html = getHTML_Articulo();
			break;
			case GALERIA:
				html = getHTML_Galeria();
				break;
			case INFOGRAFIA:
				html = getHTML_Infografia();
			break;	
			case MULTIMEDIA:
				html = getHTML_Multimedia();
				break;
			case VIDEO:
				html = getHTML_Video();
			break;
		}
		return html;
	}
	
	
	private String getHTML_Articulo(){
		StringBuffer html=new  StringBuffer();
		html.append(" <!DOCTYPE html>\n");
		html.append(" <html lang=\"en\">\n");
		html.append("   <head>\n");
		html.append("     <meta charset=\"utf-8\">\n");
		html.append("     <title>$WCM_TITLE_CONTENIDO$</title>\n");
		html.append("     <meta property=\"fb:article_style\" content=\"unotv\">\n");
		html.append("     <link rel=\"canonical\" href=\"$WCM_URL_PAGE$/\">\n");
		html.append("     <meta property=\"og:title\" content=\"$WCM_TITLE_CONTENIDO$\">\n");
		html.append("     <meta property=\"og:description\" content=\"$WCM_DESCRIPCION_CONTENIDO$\">\n");
		html.append("     <meta property=\"og:image\" content=\"$WCM_IMAGE$\">\n");
		html.append("     <meta property=\"fb:use_automatic_ad_placement\" content=\"true\"> \n");
		html.append("   </head>\n");
		html.append("   <body>\n");
		html.append("     <article>\n");
		html.append("     <figure class=\"op-tracker\">\n");
		html.append("           <iframe>\n");
		html.append("               <script>\n");
		html.append("             (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){\n");
		html.append("             (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),\n");
		html.append("             m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)\n");
		html.append("             })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');\n");
		html.append("             ga('create', 'UA-54207819-13', 'auto');\n");
		html.append("             ga('require', 'displayfeatures');\n");
		html.append("             ga('set', 'campaignSource', 'Facebook');\n");
		html.append("             ga('set', 'campaignMedium', 'Social Instant Article');\n");
		html.append("             ga('set', 'title', '$WCM_TITLE_CONTENIDO$');\n");
		html.append("             ga('send', 'pageview');\n");
		html.append("           </script>\n");
		html.append("           </iframe>\n");
		html.append("     </figure>\n");
		html.append("     	<header>\n");
		html.append("         <figure data-mode=\"aspect-fit\"><img src=\"$WCM_IMAGE$\"></figure>\n");
		html.append("         <h3 class=\"op-kicker\">$WCM_CATEGORIA$</h3>\n");
		html.append("         <address>$WCM_AUTOR$ | $WCM_LUGAR$ | $WCM_FUENTE$</address>\n");
		html.append("         <time class=\"op-published\" datetime=\"$WCM_FECHA_FB$\">$WCM_FECHA$ $WCM_HORA$</time>\n");
		html.append("     	  <figure class=\"op-ad\">\n");
        html.append("    		 	<iframe width=\"300\" height=\"250\" style=\"border:0; margin:0;\" src=\"https://www.facebook.com/adnw_request?placement=286292948469626_286301091802145&adtype=banner300x250\"></iframe>\n");
		html.append("     	  </figure>\n");
		html.append("       </header>\n");
		html.append(" $WCM_RTF_CONTENIDO$ ");
		/*html.append("       <h1>Articulos Destacados</h1>\n");
		html.append("       <ul class=\"op-related-articles\">\n");
		html.append("         $WCM_LIST_RELACIONADAS$\n");
		html.append("       </ul>\n");*/
		html.append("       <footer>\n");
		html.append("         <aside>Derechos Reservados &copy; Publicidad y Contenido Editorial S.A. de C.V.</aside>\n");
		html.append("       </footer>\n");
		html.append("     </article>\n");
		html.append("   </body>\n");
		html.append(" </html>\n");
		return html.toString();	
	}
	private String getHTML_Galeria(){
		StringBuffer html=new  StringBuffer();
		html.append(" <!DOCTYPE html>\n");
		html.append(" <html lang=\"en\">\n");
		html.append("   <head>\n");
		html.append("     <meta charset=\"utf-8\">\n");
		html.append("     <title>$WCM_TITLE_CONTENIDO$</title>\n");
		html.append("     <meta property=\"fb:article_style\" content=\"unotv\">\n");
		html.append("     <link rel=\"canonical\" href=\"$WCM_URL_PAGE$/\">\n");
		html.append("     <meta property=\"og:title\" content=\"$WCM_TITLE_CONTENIDO$\">\n");
		html.append("     <meta property=\"og:description\" content=\"$WCM_DESCRIPCION_CONTENIDO$\">\n");
		html.append("     <meta property=\"og:image\" content=\"$WCM_IMAGE$\">\n");
		html.append("     <meta property=\"fb:use_automatic_ad_placement\" content=\"true\"> \n");
		html.append("   </head>\n");
		html.append("   <body>\n");
		html.append("     <article>\n");
		html.append("     <figure class=\"op-tracker\">\n");
		html.append("           <iframe>\n");
		html.append("               <script>\n");
		html.append("             (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){\n");
		html.append("             (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),\n");
		html.append("             m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)\n");
		html.append("             })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');\n");
		html.append("             ga('create', 'UA-54207819-13', 'auto');\n");
		html.append("             ga('require', 'displayfeatures');\n");
		html.append("             ga('set', 'campaignSource', 'Facebook');\n");
		html.append("             ga('set', 'campaignMedium', 'Social Instant Article');\n");
		html.append("             ga('set', 'title', '$WCM_TITLE_CONTENIDO$');\n");
		html.append("             ga('send', 'pageview');\n");
		html.append("           </script>\n");
		html.append("           </iframe>\n");
		html.append("     </figure>\n");
		html.append("       <header>\n");
		html.append("         <figure data-mode=\"aspect-fit\"><img src=\"$WCM_IMAGE$\"></figure>\n");
		html.append("         <h3 class=\"op-kicker\">$WCM_CATEGORIA$</h3>\n");
		html.append("         <address>$WCM_AUTOR$ | $WCM_LUGAR$ | $WCM_FUENTE$</address>\n");
		html.append("         <time class=\"op-published\" datetime=\"$WCM_FECHA_FB$\">$WCM_FECHA$ $WCM_HORA$</time>\n");
		html.append("     	  <figure class=\"op-ad\">\n");
        html.append("    		 	<iframe width=\"300\" height=\"250\" style=\"border:0; margin:0;\" src=\"https://www.facebook.com/adnw_request?placement=286292948469626_286301091802145&adtype=banner300x250\"></iframe>\n");
		html.append("     	  </figure>\n");
		html.append("       </header>\n");
		html.append("     $WCM_RTF_CONTENIDO$ ");
		html.append(" 	  $WCM_GALERIA$ ");
		/*html.append("       <h1>Articulos Destacados</h1>\n");
		html.append("       <ul class=\"op-related-articles\">\n");
		html.append("         $WCM_LIST_RELACIONADAS$\n");
		html.append("       </ul>\n");*/
		html.append("       <footer>\n");
		html.append("         <aside>Derechos Reservados &copy; Publicidad y Contenido Editorial S.A. de C.V.</aside>\n");
		html.append("       </footer>\n");
		html.append("     </article>\n");
		html.append("   </body>\n");
		html.append(" </html>\n");
		return html.toString();
	}
	private String getHTML_Infografia(){
		StringBuffer html=new  StringBuffer();
		html.append(" <!DOCTYPE html>\n");
		html.append(" <html lang=\"en\">\n");
		html.append("   <head>\n");
		html.append("     <meta charset=\"utf-8\">\n");
		html.append("     <title>$WCM_TITLE_CONTENIDO$</title>\n");
		html.append("     <meta property=\"fb:article_style\" content=\"unotv\">\n");
		html.append("     <link rel=\"canonical\" href=\"$WCM_URL_PAGE$/\">\n");
		html.append("     <meta property=\"og:title\" content=\"$WCM_TITLE_CONTENIDO$\">\n");
		html.append("     <meta property=\"og:description\" content=\"$WCM_DESCRIPCION_CONTENIDO$\">\n");
		html.append("     <meta property=\"og:image\" content=\"$WCM_IMAGE$\">\n");
		html.append("     <meta property=\"fb:use_automatic_ad_placement\" content=\"true\"> \n");
		html.append("   </head>\n");
		html.append("   <body>\n");
		html.append("     <article>\n");
		html.append("     <figure class=\"op-tracker\">\n");
		html.append("           <iframe>\n");
		html.append("               <script>\n");
		html.append("             (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){\n");
		html.append("             (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),\n");
		html.append("             m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)\n");
		html.append("             })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');\n");
		html.append("             ga('create', 'UA-54207819-13', 'auto');\n");
		html.append("             ga('require', 'displayfeatures');\n");
		html.append("             ga('set', 'campaignSource', 'Facebook');\n");
		html.append("             ga('set', 'campaignMedium', 'Social Instant Article');\n");
		html.append("             ga('set', 'title', '$WCM_TITLE_CONTENIDO$');\n");
		html.append("             ga('send', 'pageview');\n");
		html.append("           </script>\n");
		html.append("           </iframe>\n");
		html.append("     </figure>\n");
		html.append("       <header>\n");
		html.append("         <figure data-mode=\"aspect-fit\"><img src=\"$WCM_IMAGE$\"></figure>\n");
		html.append("         <h3 class=\"op-kicker\">$WCM_CATEGORIA$</h3>\n");
		html.append("         <address>$WCM_AUTOR$ | $WCM_LUGAR$ | $WCM_FUENTE$</address>\n");
		html.append("         <time class=\"op-published\" datetime=\"$WCM_FECHA_FB$\">$WCM_FECHA$ $WCM_HORA$</time>\n");
		html.append("     	  <figure class=\"op-ad\">\n");
        html.append("    		 	<iframe width=\"300\" height=\"250\" style=\"border:0; margin:0;\" src=\"https://www.facebook.com/adnw_request?placement=286292948469626_286301091802145&adtype=banner300x250\"></iframe>\n");
		html.append("     	  </figure>\n");
		html.append("       </header>\n");
		html.append("     $WCM_RTF_CONTENIDO$ ");
		html.append(" 	  $WCM_INFOGRAFIA$ ");
		/*html.append("       <h1>Articulos Destacados</h1>\n");
		html.append("       <ul class=\"op-related-articles\">\n");
		html.append("         $WCM_LIST_RELACIONADAS$\n");
		html.append("       </ul>\n");*/
		html.append("       <footer>\n");
		html.append("         <aside>Derechos Reservados &copy; Publicidad y Contenido Editorial S.A. de C.V.</aside>\n");
		html.append("       </footer>\n");
		html.append("     </article>\n");
		html.append("   </body>\n");
		html.append(" </html>\n");
		return html.toString();
	}
	private String getHTML_Multimedia(){
		StringBuffer html=new  StringBuffer();
		html.append(" <!DOCTYPE html>\n");
		html.append(" <html lang=\"en\">\n");
		html.append("   <head>\n");
		html.append("     <meta charset=\"utf-8\">\n");
		html.append("     <title>$WCM_TITLE_CONTENIDO$</title>\n");
		html.append("     <meta property=\"fb:article_style\" content=\"unotv\">\n");
		html.append("     <link rel=\"canonical\" href=\"$WCM_URL_PAGE$/\">\n");
		html.append("     <meta property=\"og:title\" content=\"$WCM_TITLE_CONTENIDO$\">\n");
		html.append("     <meta property=\"og:description\" content=\"$WCM_DESCRIPCION_CONTENIDO$\">\n");
		html.append("     <meta property=\"og:image\" content=\"$WCM_IMAGE$\">\n");
		html.append("     <meta property=\"fb:use_automatic_ad_placement\" content=\"true\"> \n");
		html.append("   </head>\n");
		html.append("   <body>\n");
		html.append("     <article>\n");
		html.append("     <figure class=\"op-tracker\">\n");
		html.append("           <iframe>\n");
		html.append("               <script>\n");
		html.append("             (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){\n");
		html.append("             (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),\n");
		html.append("             m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)\n");
		html.append("             })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');\n");
		html.append("             ga('create', 'UA-54207819-13', 'auto');\n");
		html.append("             ga('require', 'displayfeatures');\n");
		html.append("             ga('set', 'campaignSource', 'Facebook');\n");
		html.append("             ga('set', 'campaignMedium', 'Social Instant Article');\n");
		html.append("             ga('set', 'title', '$WCM_TITLE_CONTENIDO$');\n");
		html.append("             ga('send', 'pageview');\n");
		html.append("           </script>\n");
		html.append("           </iframe>\n");
		html.append("     </figure>\n");
		html.append("       <header>\n");
		html.append("         <figure data-mode=\"aspect-fit\"><img src=\"$WCM_IMAGE$\"></figure>\n");
		html.append("         <h3 class=\"op-kicker\">$WCM_CATEGORIA$</h3>\n");
		html.append("         <address>$WCM_AUTOR$ | $WCM_LUGAR$ | $WCM_FUENTE$</address>\n");
		html.append("         <time class=\"op-published\" datetime=\"$WCM_FECHA_FB$\">$WCM_FECHA$ $WCM_HORA$</time>\n");
		html.append("     	  <figure class=\"op-ad\">\n");
        html.append("    		 	<iframe width=\"300\" height=\"250\" style=\"border:0; margin:0;\" src=\"https://www.facebook.com/adnw_request?placement=286292948469626_286301091802145&adtype=banner300x250\"></iframe>\n");
		html.append("     	  </figure>\n");
		html.append("       </header>\n");
		html.append(" 	  $WCM_VIDEO$ ");
		html.append("     $WCM_RTF_CONTENIDO$ ");
		html.append(" 	  $WCM_GALERIA$ ");
		/*html.append("       <h1>Articulos Destacados</h1>\n");
		html.append("       <ul class=\"op-related-articles\">\n");
		html.append("         $WCM_LIST_RELACIONADAS$\n");
		html.append("       </ul>\n");*/
		html.append("       <footer>\n");
		html.append("         <aside>Derechos Reservados &copy; Publicidad y Contenido Editorial S.A. de C.V.</aside>\n");
		html.append("       </footer>\n");
		html.append("     </article>\n");
		html.append("   </body>\n");
		html.append(" </html>\n");
		return html.toString();
	}
	private String getHTML_Video(){
		StringBuffer html=new  StringBuffer();
		html.append(" <!DOCTYPE html>\n");
		html.append(" <html lang=\"en\">\n");
		html.append("   <head>\n");
		html.append("     <meta charset=\"utf-8\">\n");
		html.append("     <title>$WCM_TITLE_CONTENIDO$</title>\n");
		html.append("     <meta property=\"fb:article_style\" content=\"unotv\">\n");
		html.append("     <link rel=\"canonical\" href=\"$WCM_URL_PAGE$/\">\n");
		html.append("     <meta property=\"og:title\" content=\"$WCM_TITLE_CONTENIDO$\">\n");
		html.append("     <meta property=\"og:description\" content=\"$WCM_DESCRIPCION_CONTENIDO$\">\n");
		html.append("     <meta property=\"og:image\" content=\"$WCM_IMAGE$\">\n");
		html.append("     <meta property=\"fb:use_automatic_ad_placement\" content=\"true\"> \n");
		html.append("   </head>\n");
		html.append("   <body>\n");
		html.append("     <article>\n");
		html.append("     <figure class=\"op-tracker\">\n");
		html.append("           <iframe>\n");
		html.append("               <script>\n");
		html.append("             (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){\n");
		html.append("             (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),\n");
		html.append("             m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)\n");
		html.append("             })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');\n");
		html.append("             ga('create', 'UA-54207819-13', 'auto');\n");
		html.append("             ga('require', 'displayfeatures');\n");
		html.append("             ga('set', 'campaignSource', 'Facebook');\n");
		html.append("             ga('set', 'campaignMedium', 'Social Instant Article');\n");
		html.append("             ga('set', 'title', '$WCM_TITLE_CONTENIDO$');\n");
		html.append("             ga('send', 'pageview');\n");
		html.append("           </script>\n");
		html.append("           </iframe>\n");
		html.append("     </figure>\n");
		html.append("       <header>\n");
		html.append("         <figure data-mode=\"aspect-fit\"><img src=\"$WCM_IMAGE$\"></figure>\n");
		html.append("         <h3 class=\"op-kicker\">$WCM_CATEGORIA$</h3>\n");
		html.append("         <address>$WCM_AUTOR$ | $WCM_LUGAR$ | $WCM_FUENTE$</address>\n");
		html.append("         <time class=\"op-published\" datetime=\"$WCM_FECHA_FB$\">$WCM_FECHA$ $WCM_HORA$</time>\n");
		html.append("     	  <figure class=\"op-ad\">\n");
        html.append("    		 	<iframe width=\"300\" height=\"250\" style=\"border:0; margin:0;\" src=\"https://www.facebook.com/adnw_request?placement=286292948469626_286301091802145&adtype=banner300x250\"></iframe>\n");
		html.append("     	  </figure>\n");
		html.append("       </header>\n");
		html.append(" 	  $WCM_VIDEO$ ");
		html.append("     $WCM_RTF_CONTENIDO$ ");
		/*html.append("       <h1>Articulos Destacados</h1>\n");
		html.append("       <ul class=\"op-related-articles\">\n");
		html.append("         $WCM_LIST_RELACIONADAS$\n");
		html.append("       </ul>\n"); */
		html.append("       <footer>\n");
		html.append("         <aside>Derechos Reservados &copy; Publicidad y Contenido Editorial S.A. de C.V.</aside>\n");
		html.append("       </footer>\n");
		html.append("     </article>\n");
		html.append("   </body>\n");
		html.append(" </html>\n");
		return html.toString();
	}
}