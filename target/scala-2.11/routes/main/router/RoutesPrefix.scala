
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/hashcode/hashprojects/backend/commentsapi/conf/routes
// @DATE:Sun Nov 20 14:19:52 SAST 2016


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
