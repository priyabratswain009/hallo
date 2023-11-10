package com.sunknowledge.dme.rcm.messagebus.producer;

import com.sunknowledge.dme.rcm.message.config.data.MessageBusConfigData;
import com.sunknowledge.dme.rcm.message.config.data.MessageProducerConfigData;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MessageProducerConfig<K extends Serializable, V extends SpecificRecordBase> {

    private final MessageBusConfigData messageBusConfigData;
    private final MessageProducerConfigData messageProducerConfigData;

    public MessageProducerConfig(MessageBusConfigData messageBusConfigData,
                                 MessageProducerConfigData messageProducerConfigData) {
        this.messageBusConfigData = messageBusConfigData;
        this.messageProducerConfigData = messageProducerConfigData;
    }

    @Bean
    public Map<String, Object> producerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, messageBusConfigData.getBootstrapServers());
        props.put(messageBusConfigData.getSchemaRegistryUrlKey(), messageBusConfigData.getSchemaRegistryUrl());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, messageProducerConfigData.getKeySerializerClass());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, messageProducerConfigData.getValueSerializerClass());
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, messageProducerConfigData.getBatchSize() *
                messageProducerConfigData.getBatchSizeBoostFactor());
        props.put(ProducerConfig.LINGER_MS_CONFIG, messageProducerConfigData.getLingerMs());
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, messageProducerConfigData.getCompressionType());
        props.put(ProducerConfig.ACKS_CONFIG, messageProducerConfigData.getAcks());
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, messageProducerConfigData.getRequestTimeoutMs());
        props.put(ProducerConfig.RETRIES_CONFIG, messageProducerConfigData.getRetryCount());
        return props;
    }

    @Bean
    public ProducerFactory<K, V> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public KafkaTemplate<K, V> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
