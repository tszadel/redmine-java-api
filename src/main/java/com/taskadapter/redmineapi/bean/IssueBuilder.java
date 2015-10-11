package com.taskadapter.redmineapi.bean;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;

public final class IssueBuilder {
    private final AbstractCollection<Journal> journals = new ArrayList<>();
    private String subject = "";
    private String description = "";
    private Integer id;

    public IssueBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public IssueBuilder setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public IssueBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public IssueBuilder setJournals(Collection<Journal> journals) {
        this.journals.addAll(journals);
        return this;
    }

    public Issue build() {
        final Issue issue = new Issue(id);
        issue.setSubject(subject);
        issue.setDescription(description);
        issue.addJournals(journals);
        return issue;
    }
}
