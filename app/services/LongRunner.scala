package services

import extensions.CancellableFuture
import play.api.Logger

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.Duration

class LongRunner {
  val logger = Logger(this.getClass)

  def body(caller: String) = {
    (1 to 1000).foreach {
      n =>
        logger.info(s"tick tick $n - from $caller")
        Thread.sleep(n)
    }
    "Completed!"
  }

  def runWithFuture(caller: String) = Future(body(caller))

  def runWithCancellableFuture(caller: String) = CancellableFuture(body(caller))
}
