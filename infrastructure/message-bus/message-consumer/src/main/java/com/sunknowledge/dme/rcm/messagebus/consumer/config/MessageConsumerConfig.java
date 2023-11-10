package com.sunknowledge.dme.rcm.messagebus.consumer.config;

import com.sunknowledge.dme.rcm.message.config.data.MessageBusConfigData;
import com.sunknowledge.dme.rcm.message.config.data.MessageConsumerConfigData;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MessageConsumerConfig<K extends Serializable, V extends SpecificRecordBase> {

    private final MessageBusConfigData messageBusConfigData;
    private final MessageConsumerConfigData messageConsumerConfigData;

    public MessageConsumerConfig(MessageBusConfigData messageBusConfigData,
                                 MessageConsumerConfigData messageConsumerConfigData) {
        this.messageBusConfigData = messageBusConfigData;
        this.messageConsumerConfigData = messageConsumerConfigData;
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, messageBusConfigData.getBootstrapServers());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, messageConsumerConfigData.getKeyDeserializer());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, messageConsumerConfigData.getValueDeserializer());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, messageConsumerConfigData.getAutoOffsetReset());
        props.put(messageBusConfigData.getSchemaRegistryUrlKey(), messageBusConfigData.getSchemaRegistryUrl());
        props.put(messageConsumerConfigData.getSpecificAvroReaderKey(), messageConsumerConfigData.getSpecificAvroReader());
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, messageConsumerConfigData.getSessionTimeoutMs());
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, messageConsumerConfigData.getHeartbeatIntervalMs());
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, messageConsumerConfigData.getMaxPollIntervalMs());
        props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG,
                messageConsumerConfigData.getMaxPartitionFetchBytesDefault() *
                        messageConsumerConfigData.getMaxPartitionFetchBytesBoostFactor());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, messageConsumerConfigData.getMaxPollRecords());
        return props;
    }

    @Bean
    public ConsumerFactory<K, V> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<K, V>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<K, V> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setBatchListener(messageConsumerConfigData.getBatchListener());
        factory.setConcurrency(messageConsumerConfigData.getConcurrencyLevel());
        factory.setAutoStartup(messageConsumerConfigData.getAutoStartup());
        factory.getContainerProperties().setPollTimeout(messageConsumerConfigData.getPollTimeoutMs());
        return factory;
    }

}
