package com.bikeshare.backend.notification.interfaces.rest;

import com.bikeshare.backend.notification.domain.model.aggregate.Notifications;
import com.bikeshare.backend.notification.domain.model.queries.GetAllNotificationQuery;
import com.bikeshare.backend.notification.domain.model.queries.GetNotificationByIdQuery;
import com.bikeshare.backend.notification.domain.model.queries.GetNotificationByUserIdMessageAndTypeId;
import com.bikeshare.backend.notification.domain.services.NotificationCommandService;
import com.bikeshare.backend.notification.domain.services.NotificationQueryService;
import com.bikeshare.backend.notification.interfaces.rest.resources.CreateNotificationResource;
import com.bikeshare.backend.notification.interfaces.rest.resources.NotificationResource;
import com.bikeshare.backend.notification.interfaces.rest.transform.CreateNotificationCommandFromResourceAssembler;
import com.bikeshare.backend.notification.interfaces.rest.transform.NotificationResourceFromEntityAssembler;
import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;
import com.bikeshare.backend.userManagement.domain.model.queries.GetAllUsersQuery;
import com.bikeshare.backend.userManagement.domain.model.queries.GetUsersByEmailQuery;
import com.bikeshare.backend.userManagement.domain.model.queries.GetUsersByIdQuery;
import com.bikeshare.backend.userManagement.domain.services.UsersCommandService;
import com.bikeshare.backend.userManagement.domain.services.UsersQueryService;
import com.bikeshare.backend.userManagement.interfaces.rest.resources.CreateUsersResource;
import com.bikeshare.backend.userManagement.interfaces.rest.resources.UsersResource;
import com.bikeshare.backend.userManagement.interfaces.rest.transform.CreateUsersCommandFromResourceAssembler;
import com.bikeshare.backend.userManagement.interfaces.rest.transform.UsersResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.Notification;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;



@RestController
@RequestMapping(value = "/api/v1/notifications", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name= "Notifications", description = "Endpoints")
public class NotificationController {
    private final NotificationCommandService notificationCommandService;
    private final NotificationQueryService notificationQueryService;

    public NotificationController(NotificationCommandService notificationCommandService, NotificationQueryService notificationQueryService) {
        this.notificationCommandService = notificationCommandService;
        this.notificationQueryService = notificationQueryService;
    }


    @PostMapping
    @Operation(
            summary = "Create a notification",
            description = "Creates a notification with the provided data"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Notification created"),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            }
    )

    public ResponseEntity<NotificationResource> createNotification(
            @RequestBody CreateNotificationResource resource
    ) {
        var createNotificationCommand = CreateNotificationCommandFromResourceAssembler
                .toCommandFromResource(resource);

        Optional<Notifications> notifications = notificationCommandService.handle(createNotificationCommand);

        return notifications.map(
                source -> new ResponseEntity<>(NotificationResourceFromEntityAssembler.toResourceFromEntity(source), CREATED)
        ).orElseGet(()->ResponseEntity.badRequest().build());
    }

    @GetMapping("{notificationId}")
    @Operation(
            summary = "Get a users data by notificationId",
            description = "Gets a users data with notificationId param"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Notification Found"),
                    @ApiResponse(responseCode = "404", description = "Notification Found")
            }
    )
    public ResponseEntity<NotificationResource> getUsersById(@PathVariable("notificationId") Long notificationId){
        Optional<Notifications> notifications = notificationQueryService.handle(new GetNotificationByIdQuery(notificationId));
        return notifications.map(
                source -> ResponseEntity.ok(NotificationResourceFromEntityAssembler.toResourceFromEntity(source))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }
    //Pago confirmado,
    @GetMapping("/{userId}/{message}/{typeId}")
    @Operation(
            summary = "Get a users data by UserId, Message and TypeId",
            description = "Gets a users data with UserId, Message and TypeId params"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Notification Found"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public ResponseEntity<NotificationResource> getNotificationByAllParams(@PathVariable("userId") Long userId, @PathVariable("message") String message, @PathVariable("typeId") Long typeId){
        Optional<Notifications> notifications = notificationQueryService.handle(new GetNotificationByUserIdMessageAndTypeId(userId, message, typeId));
        return notifications.map(
                source -> ResponseEntity.ok(NotificationResourceFromEntityAssembler.toResourceFromEntity(source))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }



    @GetMapping
    public ResponseEntity<List<NotificationResource>> getNotifications() {
        var notifications = notificationQueryService.handle(new GetAllNotificationQuery());
        var notificationResources = notifications.stream().map(source -> NotificationResourceFromEntityAssembler.toResourceFromEntity(source)).toList();
        return ResponseEntity.ok(notificationResources);
    }
}
