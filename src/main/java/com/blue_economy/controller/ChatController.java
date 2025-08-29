package com.blue_economy.controller;

;
import com.blue_economy.dto.ChatRequest;

import com.blue_economy.model.Chat;

import com.blue_economy.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/messages")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping
    public List<Chat> list() {
        return chatService.getAll();
    }

    @PostMapping
    public ResponseEntity<Chat> create(@Valid @RequestBody ChatRequest req) {
       Chat saved = chatService.saveUserMessage(req);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        chatService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        chatService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
