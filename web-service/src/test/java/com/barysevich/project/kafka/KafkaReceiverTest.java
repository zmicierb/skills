//package com.barysevich.project.kafka;
//
//import com.barysevich.project.kafka.receiver.Receiver;
//import org.junit.Before;
//import org.junit.ClassRule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.listener.MessageListenerContainer;
//import org.springframework.kafka.test.rule.KafkaEmbedded;
//import org.springframework.kafka.test.utils.ContainerTestUtils;
//import org.springframework.kafka.test.utils.KafkaTestUtils;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@DirtiesContext
//@TestPropertySource(locations="classpath:test.properties")
//public class KafkaReceiverTest {
//    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaReceiverTest.class);
//
//    private static String RECEIVER_TOPIC = "foo.t";
//
//    @Autowired
//    private Receiver receiver;
//
//    private KafkaTemplate<String, String> template;
//
//    @Autowired
//    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;
//
//    @ClassRule
//    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, RECEIVER_TOPIC);
//
//    @Before
//    public void setUp() throws Exception {
//        // set up the Kafka producer properties
//        Map<String, Object> senderProperties =
//                KafkaTestUtils.senderProps(embeddedKafka.getBrokersAsString());
//
//        // create a Kafka producer factory
//        ProducerFactory<String, String> producerFactory =
//                new DefaultKafkaProducerFactory<String, String>(senderProperties);
//
//        // create a Kafka template
//        template = new KafkaTemplate<>(producerFactory);
//        // set the default topic to send to
//        template.setDefaultTopic(RECEIVER_TOPIC);
//
//        // wait until the partitions are assigned
//        for (MessageListenerContainer messageListenerContainer : kafkaListenerEndpointRegistry
//                .getListenerContainers()) {
//            ContainerTestUtils.waitForAssignment(messageListenerContainer,
//                    embeddedKafka.getPartitionsPerTopic());
//        }
//    }
//
//    @Test
//    public void testReceive() throws Exception {
//        // send the message
//        String greeting = "Hello Spring Kafka Receiver!";
//        template.sendDefault(greeting);
//        LOGGER.info("test-sender sent message='{}'", greeting);
//
//        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
//        // check that the message was received
//        assertThat(receiver.getLatch().getCount()).isEqualTo(0);
//    }
//}
