package com.taskadapter.redmineapi.bean;

import com.taskadapter.redmineapi.Include;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Redmine's Issue
 */
public class Issue implements Identifiable {

    /**
     * database ID.
     */
    private final Integer id;

    private final PropertyStorage storage;

    public final static Property<String> SUBJECT = new Property<String>(String.class, "subject");
    public final static Property<Date> START_DATE = new Property<Date>(Date.class, "startDate");
    public final static Property<Integer> DONE_RATIO = new Property<Integer>(Integer.class, "doneRatio");

    private Integer parentId;
    private Float estimatedHours;
    private Float spentHours;
    private User assignee;
    private String priorityText;
    private Integer priorityId;
    private Project project;
    private User author;
    private Date dueDate;
    private Tracker tracker;
    private String description;
    private Date createdOn;
    private Date updatedOn;
    private Integer statusId;
    private String statusName;
    private Version targetVersion;
    private IssueCategory category;

    /**
     * Some comment describing the issue update
     */
    private String notes;

    /**
     * can't have two custom fields with the same ID in the collection, that's why it is declared
     * as a Set, not a List.
     */
    private final Set<CustomField> customFields = new HashSet<CustomField>();
    private final Set<Journal> journals = new HashSet<Journal>();
    private final Set<IssueRelation> relations = new HashSet<IssueRelation>();
    private final Set<Attachment> attachments = new HashSet<Attachment>();
    private final Set<Changeset> changesets = new HashSet<Changeset>();
    private final Set<Watcher> watchers = new HashSet<Watcher>();

    /**
     * @param id database ID.
     */
    Issue(Integer id) {
        this.id = id;
        this.storage = new PropertyStorage();
    }

    public Issue() {
        this(new PropertyStorage());
    }

    Issue(PropertyStorage storage) {
        this.storage = storage;
        this.id = null;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Integer getDoneRatio() {
        return storage.get(DONE_RATIO);
    }

    public void setDoneRatio(Integer doneRatio) {
        storage.set(DONE_RATIO, doneRatio);
    }

    public String getPriorityText() {
        return priorityText;
    }

    /**
     * @deprecated This method has no effect when creating issues on Redmine Server, so we might as well just delete it
     * in the future releases.
     */
    public void setPriorityText(String priority) {
        this.priorityText = priority;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public Float getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(Float estimatedTime) {
        this.estimatedHours = estimatedTime;
    }

    public Float getSpentHours() {
        return spentHours;
    }

    public void setSpentHours(Float spentHours) {
         this.spentHours = spentHours;
    }

  /**
     * Parent Issue ID, or NULL for issues without a parent.
     *
     * @return NULL, if there's no parent
     */
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    /**
     * @return id. can be NULL for Issues not added to Redmine yet
     */
    public Integer getId() {
        return id;
    }

    public String getSubject() {
        return storage.get(SUBJECT);
    }

    public void setSubject(String subject) {
        storage.set(SUBJECT, subject);
    }

    public Date getStartDate() {
        return storage.get(START_DATE);
    }

    public void setStartDate(Date startDate) {
        storage.set(START_DATE, startDate);
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Tracker getTracker() {
        return tracker;
    }

    public void setTracker(Tracker tracker) {
        this.tracker = tracker;
    }

    /**
     * Description is empty by default, not NULL.
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    /**
     * @return Custom Field objects. the collection may be empty, but it is never NULL.
     */
    public Collection<CustomField> getCustomFields() {
        return Collections.unmodifiableCollection(customFields);
    }

    public void clearCustomFields() {
        customFields.clear();
    }

    /**
     * NOTE: The custom field(s) <b>must have correct database ID set</b> to be saved to Redmine. This is Redmine REST API's limitation.
     */
    public void addCustomFields(Collection<CustomField> customFields) {
        this.customFields.addAll(customFields);
    }

    /**
     * If there is a custom field with the same ID already present in the Issue,
     * the new field replaces the old one.
     *
     * @param customField the field to add to the issue.
     */
    public void addCustomField(CustomField customField) {
        customFields.add(customField);
    }

    public String getNotes() {
        return notes;
    }

    /**
     * @param notes Some comment describing the issue update
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Collection<Journal> getJournals() {
        return Collections.unmodifiableCollection(journals);
    }

    public void addJournals(Collection<Journal> journals) {
        this.journals.addAll(journals);
    }

    public Collection<Changeset> getChangesets() {
        return Collections.unmodifiableCollection(changesets);
    }

    public void addChangesets(Collection<Changeset> changesets) {
        this.changesets.addAll(changesets);
    }

    public Collection<Watcher> getWatchers() {
        return Collections.unmodifiableCollection(watchers);
    }

    public void addWatchers(Collection<Watcher> watchers) {
        this.watchers.addAll(watchers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Issue issue = (Issue) o;

        if (id != null ? !id.equals(issue.id) : issue.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    /**
     * @return the value or NULL if the field is not found
     */
    public String getCustomField(String fieldName) {
        for (CustomField f : customFields) {
            if (f.getName().equals(fieldName)) {
                return f.getValue();
            }
        }
        return null;
    }

    public CustomField getCustomFieldById(int customFieldId) {
        if(customFields == null) return null;
        for (CustomField customField : customFields) {
            if (customFieldId == customField.getId()) {
                return customField;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Issue [id=" + id + ", subject=" + getSubject() + "]";
    }

    /**
     * Relations are only loaded if you include Include.relations when loading the Issue.
     *
     * @return relations or EMPTY collection if no relations, never returns NULL
     *
     * @see com.taskadapter.redmineapi.IssueManager#getIssueById(Integer id, Include... include)
     */
    public Collection<IssueRelation> getRelations() {
        return Collections.unmodifiableCollection(relations);
    }

    public void addRelations(Collection<IssueRelation> collection) {
        relations.addAll(collection);
    }

    public Integer getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Integer priorityId) {
        this.priorityId = priorityId;
    }

    public Version getTargetVersion() {
        return targetVersion;
    }

    /**
     * @return attachments. the collection can be empty, but never null.
     */
    public Collection<Attachment> getAttachments() {
        return Collections.unmodifiableCollection(attachments);
    }

    public void addAttachments(Collection<Attachment> collection) {
        attachments.addAll(collection);
    }

    public void addAttachment(Attachment attachment) {
        attachments.add(attachment);
    }

    public void setTargetVersion(Version version) {
        this.targetVersion = version;
    }

    public IssueCategory getCategory() {
        return category;
    }

    public void setCategory(IssueCategory category) {
        this.category = category;
    }

    @Override
    public Issue clone() {
        PropertyStorage clonedStorage = storage.deepClone();
        return new Issue(clonedStorage);
    }

    public PropertyStorage getStorage() {
        return storage;
    }
}
