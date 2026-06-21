document.addEventListener("DOMContentLoaded", () => {
    const forms = document.querySelectorAll("form[data-registration-role]");
    const loginPanel = document.querySelector("[data-login-panel]");

    updateLoginPanel(loginPanel);

    forms.forEach((form) => {
        const role = form.getAttribute("data-registration-role");
        const storageKey = `quickhelp.registration.${role}`;
        const statusBanner = form.querySelector("[data-registration-status]");
        const gatedLogin = document.querySelector(`[data-registration-login]`);

        prefillDraft(form, storageKey);
        toggleVisibility(gatedLogin, hasSavedDraft(storageKey));

        form.addEventListener("submit", (event) => {
            event.preventDefault();

            const formData = new FormData(form);
            const draft = Object.fromEntries(formData.entries());
            localStorage.setItem(storageKey, JSON.stringify(draft));

            if (statusBanner) {
                statusBanner.textContent = "Initial details saved on this device. Continue with Google when you are ready.";
                statusBanner.classList.add("visible");
            }

            toggleVisibility(gatedLogin, true);
            updateLoginPanel(loginPanel);
        });
    });
});

function prefillDraft(form, storageKey) {
    const draft = localStorage.getItem(storageKey);
    if (!draft) {
        return;
    }

    try {
        const parsedDraft = JSON.parse(draft);
        Object.entries(parsedDraft).forEach(([name, value]) => {
            const field = form.elements.namedItem(name);
            if (field) {
                field.value = value;
            }
        });
    } catch (error) {
        console.warn("Unable to load saved registration draft.", error);
    }
}

function hasSavedDraft(storageKey) {
    return Boolean(localStorage.getItem(storageKey));
}

function updateLoginPanel(loginPanel) {
    if (!loginPanel) {
        return;
    }

    const hasAnyRegistration = hasSavedDraft("quickhelp.registration.recruiter")
        || hasSavedDraft("quickhelp.registration.helper");
    toggleVisibility(loginPanel, hasAnyRegistration);
}

function toggleVisibility(element, isVisible) {
    if (!element) {
        return;
    }

    element.classList.toggle("visible", isVisible);
}
