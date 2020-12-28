package controllers

import play.api.i18n.{I18nSupport, Messages}
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

import javax.inject.{Inject, Singleton}

/**
 * 多言語対応のためにI18nSupportを実装する。
 * conf/messages.${lang}にそれぞれメッセージのための変数を書く。
 * conf/application.confのplay.i18n.langsリストに対応言語（${lang}）を追記しておく
 * https://www.playframework.com/documentation/2.8.x/MessagesMigration26#I18N-API-Migration
 */
@Singleton
class HelloController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {

  def get(name: Option[String]) = Action {
    implicit request: Request[AnyContent] =>
      Ok {
        name
          .map(s => Messages("hello", s))
          .getOrElse(Messages("noQuery4Get"))
      }
  }

  def plus(a: Option[Int], b: Option[Int]) = Action {
    implicit request: Request[AnyContent] =>
      Ok {
        if (a.isEmpty || b.isEmpty) {
          Messages("noQuery4Plus")
        }
        else {
          (a.get + b.get).toString
        }
      }
  }

}
