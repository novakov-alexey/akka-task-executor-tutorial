package tutorial.actor;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import tutorial.model.TaskWithResult;
import tutorial.model.VoidTask;

import java.util.concurrent.Callable;

public class TaskExecutor extends UntypedActor {
  private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

  public void onReceive(Object message) throws Exception {
    if (message instanceof VoidTask) {
      log.info("Got VoidTask = {}", message);
      ((Runnable) message).run();

    } else if (message instanceof TaskWithResult) {
      log.info("Got TaskWithResult = {}", message);
      sender().tell(((Callable) message).call(), self());

    } else {
      unhandled(message);
    }
  }
}
