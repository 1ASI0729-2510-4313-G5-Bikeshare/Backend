package com.bikeshare.backend.notification.interfaces.rest;


import com.bikeshare.backend.notification.domain.model.aggregate.NotificationsType;
import com.bikeshare.backend.notification.domain.model.queries.GetAllNotificationTypesQuery;
import com.bikeshare.backend.notification.domain.model.queries.GetNotificationTypeByIdQuery;
import com.bikeshare.backend.notification.domain.services.NotificationTypesCommandService;
import com.bikeshare.backend.notification.domain.services.NotificationTypesQueryService;
import com.bikeshare.backend.notification.interfaces.rest.resources.CreateNotificationTypeResource;
import com.bikeshare.backend.notification.interfaces.rest.resources.NotificationTypesResource;
import com.bikeshare.backend.notification.interfaces.rest.transform.CreateNotificationTypesCommandFromResourceAssembler;
import com.bikeshare.backend.notification.interfaces.rest.transform.NotificationTypesResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/api/v1/notification-type", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Notification Types", description = "Endpoints")
public class NotificationTypeController {

    private final NotificationTypesCommandService notificationTypesCommandService;
    private final NotificationTypesQueryService notificationTypesQueryService;

    public NotificationTypeController(NotificationTypesCommandService notificationTypesCommandService, NotificationTypesQueryService notificationTypesQueryService) {
        this.notificationTypesCommandService = notificationTypesCommandService;
        this.notificationTypesQueryService = notificationTypesQueryService;
    }

    @PostMapping
    @Operation(
            summary = "Create a notification type",
            description = "Create a notification type with the provided type name"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Notification type created"),
                    @ApiResponse(responseCode = "400", description = "Bad Request")
            }
    )

    public ResponseEntity<NotificationTypesResource> createNotificationType(
            @RequestBody CreateNotificationTypeResource resource
    ){
        var createNotificationTypeCommand = CreateNotificationTypesCommandFromResourceAssembler
                .toCommandFromResource(resource);

        Optional<NotificationsType> notificationsType = notificationTypesCommandService.handle(createNotificationTypeCommand);

        return notificationsType.map(
                source -> new ResponseEntity<>(NotificationTypesResourceFromEntityAssembler.toResourceFromEntity(source), CREATED)
        ).orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @GetMapping("{typeId}")
    @Operation(
            summary = "Get a Notification Type by Id",
            description = "Get a Notification Type with Id param"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Notification Type Found"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public ResponseEntity<NotificationTypesResource> getNotificationTypeById(@PathVariable("typeId") Long typeId) {
        Optional<NotificationsType> notificationsType = notificationTypesQueryService.handle(new GetNotificationTypeByIdQuery(typeId));
        return notificationsType.map(
                source -> ResponseEntity.ok(NotificationTypesResourceFromEntityAssembler.toResourceFromEntity(source))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<NotificationTypesResource>> getNotificationTypes() {
        var notificationTypes = notificationTypesQueryService.handle(new GetAllNotificationTypesQuery());
        var notificationTypesResources = notificationTypes.stream().map( source -> NotificationTypesResourceFromEntityAssembler.toResourceFromEntity(source)).toList();
        return ResponseEntity.ok(notificationTypesResources);
    }

}
