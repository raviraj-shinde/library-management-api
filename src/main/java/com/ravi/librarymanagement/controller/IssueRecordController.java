package com.ravi.librarymanagement.controller;

import com.ravi.librarymanagement.model.IssueRecord;
import com.ravi.librarymanagement.service.IssueRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/issuerecords")
public class IssueRecordController {

    @Autowired
    IssueRecordService issueRecordService;

    @PostMapping("/issuethebook/{bookid}")
    public ResponseEntity<IssueRecord> issueTheBook(@PathVariable Long bookId){
        return  ResponseEntity.ok(issueRecordService.issueTheBook(bookId));
    }

    @PostMapping("/returnthebook/{issuerecordid}")
    public ResponseEntity<IssueRecord> returnTheBook(@PathVariable Long issueRecordId){
        return ResponseEntity.ok(issueRecordService.returnTheBook(issueRecordId));
    }
}
