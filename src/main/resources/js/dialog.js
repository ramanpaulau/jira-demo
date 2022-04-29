AJS.$(function () {
    JIRA.Dialogs.commentIssue = new JIRA.FormDialog({
        id: "comment-dialog",
        trigger: ".comment-action",
        ajaxOptions: JIRA.Dialogs.getDefaultAjaxOptions,
        onSuccessfulSubmit : JIRA.Dialogs.storeCurrentIssueIdOnSucessfulSubmit,
        issueMsg : 'thanks_issue_updated'
    });
});