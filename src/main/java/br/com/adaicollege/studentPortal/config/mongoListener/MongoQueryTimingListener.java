package br.com.adaicollege.studentPortal.config.mongoListener;

import com.mongodb.event.CommandFailedEvent;
import com.mongodb.event.CommandListener;
import com.mongodb.event.CommandStartedEvent;
import com.mongodb.event.CommandSucceededEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class MongoQueryTimingListener implements CommandListener {


    private static final Logger log = LoggerFactory.getLogger(MongoQueryTimingListener.class);

    private final ConcurrentMap<Integer, Long> startTimes = new ConcurrentHashMap<>();

    @Override
    public void commandStarted(CommandStartedEvent event) {
        startTimes.put(event.getRequestId(), System.currentTimeMillis());
    }

    @Override
    public void commandSucceeded(CommandSucceededEvent event) {
        Long startTime = startTimes.remove(event.getRequestId());
        if (startTime != null) {
            long duration = System.currentTimeMillis() - startTime;
            log.info(
                    "MongoDB command '{}' on collection '{}' executed in {} ms",
                    event.getCommandName(),
                    event.getResponse().get(event.getCommandName()),
                    duration
            );
        }
    }


    @Override
    public void commandFailed(CommandFailedEvent event) {
        startTimes.remove(event.getRequestId());
        log.error("MongoDB command '{}' failed", event.getCommandName(), event.getThrowable());
    }
}
