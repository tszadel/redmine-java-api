package com.taskadapter.redmineapi.internal;

import static org.junit.Assert.assertTrue;

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
		assertTrue(generatedJSON.contains("\"priority_id\":1"));
	}

	@Test
	public void onlySetFieldsAreAddedToJSonEvenIfTheyAreNull() {
		Issue issue = new Issue();
		issue.setSubject("subj1");
		issue.setDoneRatio(null);
		final String generatedJSON = RedmineJSONBuilder.toSimpleJSON("some_project_key", issue, RedmineJSONBuilder.ISSUE_WRITER);
		assertTrue(generatedJSON.contains("\"subject\":\"subj1\","));
		assertTrue(generatedJSON.contains("\"done_ratio\":null"));
	}
}
