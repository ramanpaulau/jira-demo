package com.atlassian.raman.jira.webwork;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.bc.issue.IssueService;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueInputParameters;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.atlassian.jira.security.xsrf.RequiresXsrfCheck;
import com.atlassian.plugin.webresource.WebResourceManager;

public class MyActionClass extends JiraWebActionSupport
{

    private final IssueService issueService;
    private final JiraAuthenticationContext authenticationContext;
    private final WebResourceManager webResourceManager;
    private IssueService.UpdateValidationResult updateValidationResult;

    private Long id;
    private String comment;

    public MyActionClass() {
        this.issueService = ComponentAccessor.getIssueService();
        this.authenticationContext = ComponentAccessor.getJiraAuthenticationContext();
        this.webResourceManager = ComponentAccessor.getWebResourceManager();
    }

    protected void doValidation() {
        @SuppressWarnings("unchecked")
        final IssueInputParameters issueInputParameters = issueService.newIssueInputParameters();

        issueInputParameters.setComment(comment);
        updateValidationResult = issueService.validateUpdate(authenticationContext.getLoggedInUser(), getId(), issueInputParameters);
        if (!updateValidationResult.isValid()) {
            this.addErrorCollection(updateValidationResult.getErrorCollection());
        }
    }

    @RequiresXsrfCheck
    protected String doExecute() throws Exception {
        final IssueService.IssueResult update = issueService.update(authenticationContext.getLoggedInUser(), updateValidationResult);

        return returnComplete("/browse/" + update.getIssue().getKey());
    }

    public String doDefault() throws Exception {
        final Issue issue = getIssueObject();

        includeResources();

        return INPUT;
    }

    public Issue getIssue() {
        return getIssueObject();
    }

    public Issue getIssueObject() {
        final IssueService.IssueResult issueResult = issueService.getIssue(authenticationContext.getLoggedInUser(), id);
        if (!issueResult.isValid()) {
            this.addErrorCollection(issueResult.getErrorCollection());
            return null;
        }

        return issueResult.getIssue();
    }

    private void includeResources() {
        webResourceManager.requireResource("jira.webresources:jira-fields");
        webResourceManager.requireResource("com.atlassian.raman.moro:my-comment-dialog");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
