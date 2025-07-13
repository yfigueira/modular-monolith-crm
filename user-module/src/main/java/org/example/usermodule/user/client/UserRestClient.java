package org.example.usermodule.user.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.usermodule.User;
import org.example.usermodule.user.domain.UserClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
class UserRestClient implements UserClient {

    private final RestClient restClient;
    private final UserMapper mapper;

    @Value("${app.auth-server.url}")
    private String baseUrl;

    @Value("${app.auth-server.realm}")
    private String realm;

    @Value("${app.auth-server.client-id}")
    private String clientId;

    @Value("${app.auth-server.client-secret}")
    private String clientSecret;

    @Override
    public Optional<User> getById(UUID id) {
        var userUrl = "%s/admin/realms/%s/users/%s".formatted(baseUrl, realm, id);

        try {
            var token = getAccessToken();

            var resource = restClient.get()
                    .uri(userUrl)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token.accessToken())
                    .retrieve()
                    .onStatus(
                            HttpStatusCode::is4xxClientError,
                            (request, response) -> {
                                log.warn(
                                        "Failed to retrieve user {} :: Status: {} :: Message: {}",
                                        id,
                                        response.getStatusCode(),
                                        response.getStatusText()
                                );
                                throw new RuntimeException();
                            })
                    .onStatus(
                            HttpStatusCode::is5xxServerError,
                            (request, response) ->
                                    logServerError(response.getStatusCode().toString(), response.getStatusText()))
                    .body(UserResource.class);

            return Optional.of(mapper.toDomain(resource));
        } catch (RuntimeException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> getAll() {
        var usersUrl = "%s/admin/realms/%s/users".formatted(baseUrl, realm);

        try {
            var token = getAccessToken();

            var resources = restClient.get()
                    .uri(usersUrl)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token.accessToken())
                    .retrieve()
                    .onStatus(
                            HttpStatusCode::is4xxClientError,
                            (request, response) ->
                                    log.error(
                                            "Failed to retrieve users :: Status: {} :: Message: {}",
                                            response.getStatusCode(),
                                            response.getStatusText()
                                    )
                    )
                    .onStatus(
                            HttpStatusCode::is5xxServerError,
                            (request, response) ->
                                    logServerError(response.getStatusCode().toString(), response.getStatusText()))
                    .body(new ParameterizedTypeReference<List<UserResource>>() { });

            return resources == null ? List.of() : resources.stream().map(mapper::toDomain).toList();
        } catch (RuntimeException ex) {
            return List.of();
        }
    }

    private Token getAccessToken() {
        var tokenUrl = "%s/realms/%s/protocol/openid-connect/token".formatted(baseUrl, realm);

        var body = new LinkedMultiValueMap<String, String>();
        body.add("grant_type", "client_credentials");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);

        return restClient.post()
                .uri(tokenUrl)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(body)
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        (request, response) ->
                                log.error(
                                        "Failed to retrieve access token :: Status: {} :: Message: {}",
                                        response.getStatusCode(),
                                        response.getStatusText()
                                )
                )
                .onStatus(
                        HttpStatusCode::is5xxServerError,
                        (request, response) ->
                                logServerError(response.getStatusCode().toString(), response.getStatusText())
                )
                .body(Token.class);
    }

    private void logServerError(String statusCode, String message) {
        log.error(
                "Internal error when calling identity service :: Status {} :: Message: {}",
                statusCode,
                message
        );
    }
}
