package com.tui.proof.controller;

import com.tui.proof.pubsub.message.Message;
import com.tui.proof.pubsub.observer.ProcessedMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "${api.v1}/messages")
@RequiredArgsConstructor
public class MessageController {

    private final ProcessedMessageService processedMessageService;

    @GetMapping("/{uuid}")
    public ResponseEntity<Message> getFlight(@PathVariable UUID uuid) {
        return ResponseEntity.ok(processedMessageService.get(uuid));
    }
}
