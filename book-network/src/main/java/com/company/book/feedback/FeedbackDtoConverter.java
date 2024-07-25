package com.company.book.feedback;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class FeedbackDtoConverter {

    public FeedbackDto convert(Feedback feedback, Integer id) {
        return new FeedbackDto(
                feedback.getNote(),
                feedback.getComment(),
                Objects.equals(feedback.getCreatedBy(), id)
        );
    }
}
