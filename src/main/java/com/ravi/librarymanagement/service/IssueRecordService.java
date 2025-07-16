package com.ravi.librarymanagement.service;

import com.ravi.librarymanagement.model.Book;
import com.ravi.librarymanagement.model.IssueRecord;
import com.ravi.librarymanagement.model.User;
import com.ravi.librarymanagement.repository.BookRepository;
import com.ravi.librarymanagement.repository.IssueRecordRepository;
import com.ravi.librarymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class IssueRecordService {

    @Autowired
    IssueRecordRepository issueRecordRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    public IssueRecord issueTheBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book nor found"));

        if (book.getQuantity() <= 0 && !book.getIsAvailabe()) {
            throw new RuntimeException("Book not available.");
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        //Actual Issuing Procedure
        IssueRecord issueRecordEntry = new IssueRecord();

        issueRecordEntry.setIssueDate(LocalDate.now());
        issueRecordEntry.setDueDate(LocalDate.now().plusDays(14));
        issueRecordEntry.setReturnDate(null); //optional
        issueRecordEntry.setIsReturned(false);
        issueRecordEntry.setUser(user);
        issueRecordEntry.setBook(book);

        book.setQuantity(book.getQuantity() - 1);
        if (book.getQuantity() == 0) {
            book.setIsAvailabe(false);
        }

        bookRepository.save(book);
        issueRecordRepository.save(issueRecordEntry);

        return issueRecordRepository.save(issueRecordEntry);

    }

    public IssueRecord returnTheBook(Long issueRecordId) {
        IssueRecord issueRecord = issueRecordRepository.findById(issueRecordId)
                .orElseThrow(() -> new RuntimeException("issueRecord not found"));

        if(issueRecord.getIsReturned()){
            throw new RuntimeException("Book already returned");
        }

        Book book = issueRecord.getBook();
        book.setQuantity(book.getQuantity() + 1);
        book.setIsAvailabe(true);
        bookRepository.save(book); //Most Important thing

        issueRecord.setIsReturned(true);
        issueRecord.setReturnDate(LocalDate.now());
        return issueRecordRepository.save(issueRecord);
    }
}









