package cvut.fit.mdw;

import cvut.fit.mdw.domain.ConsumerQueueWrapper;
import cvut.fit.mdw.domain.QueueWrapper;

import java.util.stream.Stream;

public abstract class AbstractReceiver {

    protected boolean shouldRun = true;
    ConsumerQueueWrapper consumerQueueWrapper;

    protected void receive() throws Exception {
        init();
        System.out.println("AbstractReceiver:" + "Listening on " + consumerQueueWrapper.getQueue().getQueueName());

        try {
            synchronized (this) {
                while (shouldRun) {
                    this.wait(1000);
                }
            }
        } finally {
            close();
            System.out.println("AbstractReceiver: Finished.");
        }
    }

    protected void close() {
        getAllWrappers().forEach(queueWrapper -> {
            try {
                queueWrapper.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    protected abstract void init() throws Exception;

    protected abstract Stream<QueueWrapper> getAllWrappers();
}
