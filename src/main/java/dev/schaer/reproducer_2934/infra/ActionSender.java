package dev.schaer.reproducer_2934.infra;

import dev.schaer.reproducer_2934.model.ActionBase;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Collection;

import static dev.schaer.reproducer_2934.infra.ScsBindingNames.BINDING_OUT_NAME;

@Component
@RequiredArgsConstructor
public class ActionSender {
    private final StreamBridge streamBridge;

    public void sendActions(Collection<ActionBase> actions) {
        Message<ActionBase[]> message = createMessage(actions);
        streamBridge.send(BINDING_OUT_NAME, message);
    }

    private Message<ActionBase[]> createMessage(Collection<ActionBase> actions) {
        ActionBase[] array = actions.toArray((ActionBase[]) Array.newInstance(ActionBase.class, 0));
        return MessageBuilder.withPayload(array) //
                .setHeader("kafka_messageKey", extractKey(actions)) //
                .build();
    }

    private Long extractKey(Collection<ActionBase> actions) {
        return actions.stream().map(ActionBase::getCaseId).findFirst().orElseThrow();
    }
}
