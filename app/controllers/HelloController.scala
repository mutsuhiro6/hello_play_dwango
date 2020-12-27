package controllers

import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

import javax.inject.{Inject, Singleton}

@Singleton
class HelloController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def get(name: Option[String]) = Action {
    implicit request: Request[AnyContent] =>
      Ok {
        name
          .map(s => s"Hello, $s!")
          .getOrElse("""Please give a name as a query parameter named "name".""")
      }
  }

  def plus(a: Option[Int], b: Option[Int]) = Action {
    implicit request: Request[AnyContent] =>
      Ok {
        if (a.isEmpty || b.isEmpty) {
          "Please give arguments of a and b."
        }
        else {
          (a.get + b.get).toString
        }
      }
  }

}
