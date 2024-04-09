This project is a minimal reproducer for
[spring-cloud-stream/issues/2934](https://github.com/spring-cloud/spring-cloud-stream/issues/2934).

The [dev.schaer.reproducer_2934.infra.ActionSenderTest](https://github.com/martyschaer/reproducer_2934/blob/main/src/test/java/dev/schaer/reproducer_2934/infra/ActionSenderTest.java)
shows the broken behaviour.
`ActionSenderTest#testSendActionsHomogeneous()` works as expected, sending/receiving the actions.
`ActionSenderTest#testSendActionsHeterogeneous()` fails, throwing a NullPointerException.
