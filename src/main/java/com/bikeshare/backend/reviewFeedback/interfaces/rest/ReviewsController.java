package com.bikeshare.backend.reviewFeedback.interfaces.rest;


import com.bikeshare.backend.reviewFeedback.domain.model.aggregate.Reviews;
import com.bikeshare.backend.reviewFeedback.domain.model.queries.GetAllReviewsQuery;
import com.bikeshare.backend.reviewFeedback.domain.model.queries.GetReviewsByIdQuery;
import com.bikeshare.backend.reviewFeedback.domain.model.queries.GetReviewsByReviewerIdAndTargetUserIdQuery;
import com.bikeshare.backend.reviewFeedback.domain.services.ReviewsCommandService;
import com.bikeshare.backend.reviewFeedback.domain.services.ReviewsQueryService;
import com.bikeshare.backend.reviewFeedback.infrastructure.persistence.jpa.ReviewsRepository;
import com.bikeshare.backend.reviewFeedback.interfaces.rest.resources.CreateReviewsResource;
import com.bikeshare.backend.reviewFeedback.interfaces.rest.resources.ReviewsResource;
import com.bikeshare.backend.reviewFeedback.interfaces.rest.transform.CreateReviewsCommandFromResourceAssembler;
import com.bikeshare.backend.reviewFeedback.interfaces.rest.transform.ReviewsResourceFromEntityAssembler;
import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "api/v1/reviews/", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Reviews", description = "Endpoints")
public class ReviewsController {

    private final ReviewsCommandService reviewsCommandService;
    private final ReviewsQueryService reviewsQueryService;

    public ReviewsController(ReviewsCommandService reviewsCommandService, ReviewsQueryService reviewsQueryService) {
        this.reviewsCommandService = reviewsCommandService;
        this.reviewsQueryService = reviewsQueryService;
    }

    @PostMapping
    @Operation(
            summary = "Create a Review",
            description = "Creates a Review with the provided data "
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Review Created"),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            }
    )

    public ResponseEntity<ReviewsResource> createReview(
            @RequestBody CreateReviewsResource resource
    ){
        var createReviewCommand = CreateReviewsCommandFromResourceAssembler
                .toCommandFromResource(resource);

        Optional<Reviews> reviews = reviewsCommandService.handle(createReviewCommand);

        return reviews.map(
                source -> new ResponseEntity<>(ReviewsResourceFromEntityAssembler.toResourceFromEntity(source), CREATED)
        ).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/id/{reviewId}")
    @Operation(
            summary = "Get a review data by reviewId",
            description = "Get a review data with reviewId param"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Review Found"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )

    public ResponseEntity<ReviewsResource> getReviewsById(@PathVariable("reviewId") Long reviewId){
        Optional<Reviews> reviews = reviewsQueryService.handle(new GetReviewsByIdQuery(reviewId));
        return reviews.map(
                source -> ResponseEntity.ok(ReviewsResourceFromEntityAssembler.toResourceFromEntity(source))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/by-reviewer/{reviewerId}/target/{targetUserId}")
    @Operation(
            summary = "Get a reviews data by reviewerId and targetUserId",
            description = "Get a reviews data with reviewerId and targetUserId param"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Review Found"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public ResponseEntity<ReviewsResource> getReviewsByReviewerIdAndTargetUserId(@PathVariable("reviewerId")Long reviewerId, @PathVariable("targetUserId") Long targetUserId){
        Optional<Reviews> reviews = reviewsQueryService.handle(new GetReviewsByReviewerIdAndTargetUserIdQuery(reviewerId, targetUserId));
        return reviews.map(
                source -> ResponseEntity.ok(ReviewsResourceFromEntityAssembler.toResourceFromEntity(source))
        ).orElseGet(() -> ResponseEntity.notFound().build());

    }


    @GetMapping
    public ResponseEntity<List<ReviewsResource>> getReviews() {
        var reviews = reviewsQueryService.handle(new GetAllReviewsQuery());
        var reviewsResource = reviews.stream().map(source -> ReviewsResourceFromEntityAssembler.toResourceFromEntity(source)).toList();
        return ResponseEntity.ok(reviewsResource);
    }
}
