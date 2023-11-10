package com.sunknowledge.dme.rcm.messagebus.consumer;

import org.apache.avro.specific.SpecificRecordBase;

import java.util.List;

public interface MessageConsumer<T extends SpecificRecordBase> {

    void receive(List<T> messages, List<String> keys, List<Integer> partitions, List<Long> offsets);

}
