package com.sunknowledge.dme.rcm.domain.event.publisher;

import com.sunknowledge.dme.rcm.domain.event.DomainEvent;

public interface DomainEventPublisher <T extends DomainEvent> {

    void publish(T domainEvent);
}
