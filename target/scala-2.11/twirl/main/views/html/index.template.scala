
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object index_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class index extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /*
 * This template takes a single argument, a String containing a
 * message to display.
 */
  def apply/*5.2*/(message: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.19*/("""

"""),format.raw/*11.4*/("""
"""),_display_(/*12.2*/main("Welcome to Play")/*12.25*/ {_display_(Seq[Any](format.raw/*12.27*/("""

    """),format.raw/*17.8*/("""
    """),_display_(/*18.6*/play20/*18.12*/.welcome(message, style = "Scala")),format.raw/*18.46*/("""

""")))}),format.raw/*20.2*/("""
"""))
      }
    }
  }

  def render(message:String): play.twirl.api.HtmlFormat.Appendable = apply(message)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (message) => apply(message)

  def ref: this.type = this

}


}

/*
 * This template takes a single argument, a String containing a
 * message to display.
 */
object index extends index_Scope0.index
              /*
                  -- GENERATED --
                  DATE: Wed Sep 28 18:00:20 CAT 2016
                  SOURCE: C:/Users/Bonga/Desktop/commentsapi/app/views/index.scala.html
                  HASH: 3edcf73b5040fe3cd5b667ac107ce2fc81d593aa
                  MATRIX: 619->99|731->116|762->318|791->321|823->344|863->346|898->480|931->487|946->493|1001->527|1036->532
                  LINES: 23->5|28->5|30->11|31->12|31->12|31->12|33->17|34->18|34->18|34->18|36->20
                  -- GENERATED --
              */
          