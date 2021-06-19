package com.tui.proof.controller;

import com.tui.proof.controller.api.MessageApi;
import com.tui.proof.pubsub.message.Message;
import com.tui.proof.pubsub.observer.ProcessedMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Message REST API
 */
@Slf4j
@RestController
@RequestMapping(path = "${api.v1.messages}")
@RequiredArgsConstructor
public class MessageController implements MessageApi {

    private final ProcessedMessageService processedMessageService;

    /**
     * Get message from processed messages storage by identity
     *
     * @param uuid identity
     * @return found message
     */
    @GetMapping("/{uuid}")
    public ResponseEntity<Message> getMessage(@PathVariable UUID uuid) {
        log.info("Get message by uuid query: {}", uuid);
        return ResponseEntity.ok(processedMessageService.get(uuid));
    }
}
