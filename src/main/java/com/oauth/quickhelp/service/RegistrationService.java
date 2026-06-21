package com.oauth.quickhelp.service;

import com.oauth.quickhelp.dto.RegistrationDraft;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {

    private static final String REGISTRATION_DRAFT_SESSION_KEY = "REGISTRATION_DRAFT";

    public void saveRegistrationDraftToSession(RegistrationDraft draft,
                                               HttpSession session) {
        session.setAttribute(REGISTRATION_DRAFT_SESSION_KEY, draft);
    }

    public Optional<RegistrationDraft> getRegistrationDraftFromSession(HttpSession session) {
        Object draft = session.getAttribute(REGISTRATION_DRAFT_SESSION_KEY);
        if (draft instanceof RegistrationDraft registrationDraft) {
            return Optional.of(registrationDraft);
        }

        return Optional.empty();
    }

    public void clearRegistrationDraftFromSession(HttpSession session) {
        session.removeAttribute(REGISTRATION_DRAFT_SESSION_KEY);
    }
}
