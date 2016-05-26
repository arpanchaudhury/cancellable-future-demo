package controllers

import com.google.inject.{Inject, Singleton}
import play.api.mvc.{Action, Controller}
import services.LongRunner

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

@Singleton
class ApplicationController @Inject()(longRunner: LongRunner) extends Controller {
  def normalFuture = Action {
    request =>
      val withFuture = longRunner.runWithFuture("normal future")
      val result = Await.result(withFuture, 10.seconds)
      Ok(result)
  }

  def cancellableFuture = Action {
    request =>
      val withCancellableFuture = longRunner.runWithCancellableFuture("cancellable future")
      withCancellableFuture.cancelAfter(10.seconds)
      val result = Await.result(withCancellableFuture.future, 10.seconds)
      Ok(result)
  }
}
