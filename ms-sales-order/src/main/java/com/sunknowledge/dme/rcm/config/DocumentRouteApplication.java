package com.sunknowledge.dme.rcm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.FormFieldPart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

public class DocumentRouteApplication {
    @Bean
    public RouterFunction<ServerResponse> profileRoute() {
        return RouterFunctions.route(
            RequestPredicates.POST("/api/cmn/uploadCMNDocument")
                .and(RequestPredicates.contentType(MediaType.MULTIPART_FORM_DATA)),
            request -> request.body(BodyExtractors.toMultipartData()).flatMap(parts -> {
                    Map<String, Part> partMap = parts.toSingleValueMap();

                    partMap.forEach((partName, value) -> {
                        System.out.println("Name: {}, value: {}"+ partName+ value);
                    });

                    // Handle file
                    FilePart image = (FilePart) partMap.get("image");
                    System.out.println("File name: {}"+image.filename());
                    // Handle profile
                    FormFieldPart profile = (FormFieldPart) partMap.get("profile");

                    return Mono.empty();
                })
                .then(ServerResponse.ok().bodyValue("Profile updated")));
    }
}
