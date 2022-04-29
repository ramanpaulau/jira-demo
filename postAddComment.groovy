import com.atlassian.jira.component.ComponentAccessor
import groovyx.net.http.HTTPBuilder

final boolean dispatchEvent = true

def issueService = ComponentAccessor.getIssueService()
def user = ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser()
def author = ComponentAccessor.jiraAuthenticationContext.loggedInUser

def name = user.getUsername().split("\\.")[0]
def builder = new HTTPBuilder('https://api.agify.io')
builder.get(
    query: ["name": name]
){ resp, reader ->
    def body = "Age of " + name + " is " + (reader as Map).get("age")
    ComponentAccessor.commentManager.create(issue, author, body, dispatchEvent)
}