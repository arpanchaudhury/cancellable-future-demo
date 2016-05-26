package extensions

import scala.concurrent._
import scala.concurrent.duration.Duration

trait CancellableFuture[+T] {
  val cancel: () => Unit
  val future: Future[T]

  def cancelAfter(duration: Duration)(implicit executor: ExecutionContext): Unit =
    Future(Thread.sleep(duration.toMillis)).map(_ => cancel())
}

object CancellableFuture {

  def apply[T](body: => T)(implicit executor: ExecutionContext) =
    new CancellableFuture[T] {
      val (future, cancel) = {
        var thread: Thread = null
        (Future{thread = Thread.currentThread(); body}, () => thread.interrupt())
      }
    }
}
