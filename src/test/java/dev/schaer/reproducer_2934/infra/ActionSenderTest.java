package dev.schaer.reproducer_2934.infra;

import dev.schaer.reproducer_2934.config.ScsConverterConfig;
import dev.schaer.reproducer_2934.model.ActionBase;
import dev.schaer.reproducer_2934.model.ActionCancellation;
import dev.schaer.reproducer_2934.model.ActionSpecialStop;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class ActionSenderTest {
    private static final Long CASE_ID = 42L;

    @Autowired
    private OutputDestination output;

    @Autowired
    private ActionSender actionSender;

    /**
     * Test succeeds
     */
    @Test
    void testSendActionsHomogeneous() {
        testSendActions(createActionsHomogeneous());
    }

    /**
     * Test fails with NPE at SimpleFunctionRegistry.java:1179
     */
    @Test
    void testSendActionsHeterogeneous() {
        testSendActions(createActionsHeterogeneous());
    }

    private void testSendActions(Collection<ActionBase> actionsToSend) {
        actionSender.sendActions(actionsToSend);
        Message<?> outputMessage = output.receive(0, ScsBindingNames.BINDING_OUT_NAME);
        assertThat(outputMessage, notNullValue());
        Object payload = outputMessage.getPayload();
        if (payload instanceof ActionBase[] outputActions) {
            Collection<ActionBase> receivedActions = List.of(outputActions);
            assertThat(receivedActions, equalTo(actionsToSend));
        }
        else {
            fail("Payload did not contain Actions, but instead " + payload);
        }
    }

    private Collection<ActionBase> createActionsHomogeneous() {
        return List.of(
                new ActionCancellation(1L, CASE_ID, "Bern"),
                new ActionCancellation(2L, CASE_ID, "Zürich")
        );
    }

    private Collection<ActionBase> createActionsHeterogeneous() {
        return List.of(
                new ActionCancellation(3L, CASE_ID, "Geneva"),
                new ActionSpecialStop(4L, CASE_ID, "Basel", "Commercial Stop")
        );
    }

    @SpringBootApplication
    @Import({ TestChannelBinderConfiguration.class, ScsConverterConfig.class })
    static class TestConfiguration {
    }
}
