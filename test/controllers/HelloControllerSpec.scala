package controllers

import org.scalatestplus.play.PlaySpec
import play.api.mvc.Result
import play.api.test.FakeRequest
import play.api.test.Helpers._

import scala.concurrent.Future

class HelloControllerSpec extends PlaySpec {

  def controller = new HelloController(stubControllerComponents())

  "get" should {
    """If there is some query parameter, this returns "Hello, namae!".""" in {
      val name = "namae"
      val result: Future[Result] = controller.get(Some(name))(FakeRequest())
      assert(status(result) === 200)
      assert(contentAsString(result) === s"Hello, namae!")
    }


    """If there is no parameter, this returns "Please give a name as a query parameter named "name"." """ in {
      val result: Future[Result] = controller.get(None)(FakeRequest())
      assert(status(result) === 200)
      assert(contentAsString(result) === """Please give a name as a query parameter named "name".""")
    }
  }
}
