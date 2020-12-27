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

}