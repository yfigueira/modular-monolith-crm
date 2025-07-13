package org.example.usermodule.exception;

import java.util.UUID;

public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }

  public static ResourceNotFoundException notFound(Class<?> clazz, UUID id) {
    return new ResourceNotFoundException("%s not found :: %s".formatted(clazz.getSimpleName(), id));
  }

  public static class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
      super(message);
    }
  }
}
