package com.ravi.librarymanagement.DTO;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.ToString;

@Data
@ToString

public class BookDTO {

    private String title;
    private String author;
    private String isbn;
    private Integer quantity;
    private Boolean isAvailabe;
}
