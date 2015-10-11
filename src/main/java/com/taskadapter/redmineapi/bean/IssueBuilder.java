package com.taskadapter.redmineapi.bean;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;

public final class IssueBuilder {
    private final AbstractCollection<Journal> journals = new ArrayList<>();
    private String subject = "";
    private String description = "";
    private Integer id;

    public IssueBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public IssueBuilder withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public IssueBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public IssueBuilder withJournals(Collection<Journal> journals) {
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
