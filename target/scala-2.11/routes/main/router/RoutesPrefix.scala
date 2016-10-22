
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Bonga/Desktop/commentsapi/conf/routes
// @DATE:Wed Sep 28 18:00:20 CAT 2016


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
