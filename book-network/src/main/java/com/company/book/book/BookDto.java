package com.company.book.book;

public record BookDto(
        Integer id,
        String title,
        String authorName,
        String isbn,
        String synopsis,
        String owner,
        byte[] cover,
        double rate,
        boolean archived,
        boolean shareable
) {
}
