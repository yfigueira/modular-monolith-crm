package org.example.leadmodule.exception;

import java.util.UUID;

public class LeadException extends RuntimeException {
    public LeadException(String message) {
        super(message);
    }

    public static ResourceAlreadyExistsException alreadyExists(Class<?> clazz, String conflictValue) {
        return new ResourceAlreadyExistsException("%s already exists :: %s".formatted(clazz.getSimpleName(), conflictValue));
    }

    public static ResourceNotFoundException notFound(Class<?> clazz, UUID id) {
        return new ResourceNotFoundException("%s not found :: %s".formatted(clazz.getSimpleName(), id));
    }

    public static class ResourceAlreadyExistsException extends RuntimeException {
        public ResourceAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
