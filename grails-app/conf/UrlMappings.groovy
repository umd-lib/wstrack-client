class UrlMappings {

  static mappings = {
    "/$controller/$action/$id?"(controller: 'wstrackClient')
    //			constraints {
    //				// apply constraints here
    //		}

    "/"(view:"/index")
    "500"(view:'/error')
  }
}
