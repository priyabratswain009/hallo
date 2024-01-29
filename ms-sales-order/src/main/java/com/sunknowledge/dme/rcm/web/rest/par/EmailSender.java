package com.sunknowledge.dme.rcm.web.rest.par;

import java.util.Properties;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunknowledge.dme.rcm.service.par.EmailSenderService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/par")
public class EmailSender {
	
	@Autowired
	EmailSenderService emailSenderService;
	
	@PostMapping("/sendParMail")
	public Mono<String> sendParMail() throws MessagingException{
        
		return emailSenderService.sendParMail();
	}

}
