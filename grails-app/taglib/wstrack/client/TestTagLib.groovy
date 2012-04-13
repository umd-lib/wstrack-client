package wstrack.client

class TestTagLib {
  
  def redirectPage={ attrs->
    def url=attrs.get('url')
     response.sendRedirect("${request.contextPath}"+url)
  }

}
