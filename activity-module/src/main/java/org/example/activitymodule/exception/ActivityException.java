package org.example.activitymodule.exception;

import java.util.UUID;

public class ActivityException extends RuntimeException {
    public ActivityException(String message) {
        super(message);
    }

    public static ResourceNotFoundException notFound(Class<?> clazz, UUID id) {
        return new ResourceNotFoundException("%s not found :: %s".formatted(clazz.getSimpleName(), id));
    }

    public static ActionNotAllowedException actionNotAllowed(Class<?> clazz, String reason) {
        return new ActionNotAllowedException("Action on %s not allowed :: %s".formatted(clazz.getSimpleName(), reason));
    }

    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    public static class ActionNotAllowedException extends RuntimeException {
        public ActionNotAllowedException(String message) {
            super(message);
        }
    }
}
