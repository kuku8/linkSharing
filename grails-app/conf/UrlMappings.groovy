class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
       "/"(controller:"login",view:"index",action:"index")

       // "/"(view:"/index")
      //  "500"(view:'/error')
	    "500"(view: '/Exception')
        "404"(view: '/NotFound')
    }
}
