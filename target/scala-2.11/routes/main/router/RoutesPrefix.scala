
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Administrator/IdeaProjects/commentsapi/conf/routes
// @DATE:Sun Nov 27 09:03:26 GMT+02:00 2016


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
