package com.company.book.book;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class BookDtoConverter {

    public BookDto convert(Book from) {
        return new BookDto(
                from.getId(),
                from.getTitle(),
                from.getAuthorName(),
                from.getIsbn(),
                from.getSynopsis(),
                from.getOwner().fullName(),
                from.getBookCover().getBytes(StandardCharsets.UTF_8),
                from.getRate(),
                from.isArchived(),
                from.isShareable()
        );
    }

    public List<BookDto> convert(Page<Book> from) {
        return from.stream()
                .map(this::convert)
                .toList();
    }
}
