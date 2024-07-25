package com.company.book.feedback;

public record FeedbackDto(
        Double note,
        String comment,
        boolean ownFeedback
) {
}
