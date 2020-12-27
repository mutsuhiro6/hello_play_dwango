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


    """If there is no parameter, this returns \"Please give a name as a query parameter named "name".\"""" in {
      val result: Future[Result] = controller.get(None)(FakeRequest())
      assert(status(result) === 200)
      assert(contentAsString(result) === """Please give a name as a query parameter named "name".""")
    }
  }

  "plus" should {
    "If the both of a & b parameters, `plus` returns a + b." in {
      val a = Some(1)
      val b = Some(3)
      val aplusb = "4"
      val result = controller.plus(a,b)(FakeRequest())
      assert(status(result) === 200)
      assert(contentAsString(result) === aplusb)
    }
    """If a parameter `a` lacks, `plus` returns "Please give arguments of a and b.".""" in {
      val a = None
      val b = Some(4)
      val result = controller.plus(a,b)(FakeRequest())
      assert(status(result) === 200)
      assert(contentAsString(result) === "Please give arguments of a and b.")
    }
    """If a parameter `b` lacks, `plus` returns "Please give arguments of a and b.".""" in {
      val a = Some(5)
      val b = None
      val result = controller.plus(a,b)(FakeRequest())
      assert(status(result) === 200)
      assert(contentAsString(result) === "Please give arguments of a and b.")
    }
    """If the both of parameters lack, `plus` returns "Please give arguments of a and b.".""" in {
      val a = None
      val b = None
      val result = controller.plus(a,b)(FakeRequest())
      assert(status(result) === 200)
      assert(contentAsString(result) === "Please give arguments of a and b.")
    }

  }
}
