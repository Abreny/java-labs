package pro.abned.labs.concurrent.api.executors;

import java.util.concurrent.Executor;

public class Invoker implements Executor {
    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
