package com.oauth.quickhelp.controller;

import com.oauth.quickhelp.dto.ApiResponse;
import com.oauth.quickhelp.dto.RegistrationDraft;
import com.oauth.quickhelp.service.RegistrationService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/draft")
    public ResponseEntity<ApiResponse<RegistrationDraft>> saveDraft(
            @Valid @RequestBody RegistrationDraft registrationDraft,
            HttpSession session
    ) {
        registrationService.saveRegistrationDraftToSession(registrationDraft, session);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Registration draft saved.", registrationDraft));
    }

    @GetMapping("/draft")
    public ResponseEntity<ApiResponse<RegistrationDraft>> getDraft(HttpSession session) {
        return registrationService.getRegistrationDraftFromSession(session)
                .map(draft -> ResponseEntity.ok(ApiResponse.success("Registration draft found.", draft)))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Registration draft not found.")));
    }

    @DeleteMapping("/draft")
    public ResponseEntity<ApiResponse<Void>> clearDraft(HttpSession session) {
        registrationService.clearRegistrationDraftFromSession(session);
        return ResponseEntity.ok(ApiResponse.success("Registration draft cleared."));
    }
}
