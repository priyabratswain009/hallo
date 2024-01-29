package com.sunknowledge.dme.rcm.service.par;

import java.util.Properties;

import javax.mail.MessagingException;

import reactor.core.publisher.Mono;

public interface EmailSenderService {

	Mono<String> sendParMail() throws MessagingException;
	
}
