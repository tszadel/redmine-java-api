package com.taskadapter.redmineapi.internal;

import static org.fest.assertions.Assertions.assertThat;

import com.taskadapter.redmineapi.bean.Group;
import com.taskadapter.redmineapi.bean.GroupFactory;
import com.taskadapter.redmineapi.bean.User;
import com.taskadapter.redmineapi.bean.UserFactory;
import org.junit.Test;
import com.taskadapter.redmineapi.bean.Issue;

public class RedmineJSONGeneratorTest {
	/**
	 * Ported regression test for
	 * http://code.google.com/p/redmine-java-api/issues/detail?id=98 from
	 * RedmineXMLGeneratorTest
	 */
	@Test
	public void priorityIdIsAddedToXMLIfProvided() {
		Issue issue = new Issue();
		issue.setPriorityId(1);
		final String generatedJSON = RedmineJSONBuilder.toSimpleJSON(
                "some_project_key", issue, RedmineJSONBuilder.ISSUE_WRITER);
		assertThat(generatedJSON).contains("\"priority_id\":1");
	}

	@Test
	public void onlyExplicitlySetFieldsAreAddedToIssueJSon() {
		Issue issue = new Issue();
		issue.setSubject("subj1");
		issue.setDoneRatio(null);
		final String generatedJSON = RedmineJSONBuilder.toSimpleJSON("some_project_key", issue, RedmineJSONBuilder.ISSUE_WRITER);
		assertThat(generatedJSON).contains("\"subject\":\"subj1\",");
		assertThat(generatedJSON).contains("\"done_ratio\":null");
	}

	@Test
	public void onlyExplicitlySetFieldsAreAddedToUserJSon() {
		User user = UserFactory.create();
		user.setLogin("login1");
		user.setMail(null);
		user.setStatus(null);
		final String generatedJSON = RedmineJSONBuilder.toSimpleJSON("some_project_key", user, RedmineJSONBuilder.USER_WRITER);
		assertThat(generatedJSON).contains("\"login\":\"login1\",");
		assertThat(generatedJSON).contains("\"mail\":null");
		assertThat(generatedJSON).contains("\"status\":null");
		assertThat(generatedJSON).doesNotContain("\"id\"");
	}

	@Test
	public void onlyExplicitlySetFieldsAreAddedToGroupJSon() {
		Group groupWithoutName = GroupFactory.create(4);
		final String generatedJSON = RedmineJSONBuilder.toSimpleJSON("some_project_key", groupWithoutName, RedmineJSONBuilder.GROUP_WRITER);
		assertThat(generatedJSON).doesNotContain("\"name\"");

		Group groupWithName = GroupFactory.create(4);
		groupWithName.setName("some name");
		final String generatedJSONWithName = RedmineJSONBuilder.toSimpleJSON("some_project_key", groupWithName, RedmineJSONBuilder.GROUP_WRITER);
		assertThat(generatedJSONWithName).contains("\"name\":\"some name\"");
	}

}
