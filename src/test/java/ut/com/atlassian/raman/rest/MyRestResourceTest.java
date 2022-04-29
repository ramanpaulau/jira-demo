package ut.com.atlassian.raman.rest;

import com.atlassian.jira.bc.project.ProjectService;
import com.atlassian.jira.project.Project;
import org.junit.Test;

import com.atlassian.raman.rest.MyRestResource;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MyRestResourceTest {

    @Mock
    private ProjectService projectService;

    @Test
    public void transformIsValid() {
        MyRestResource resource = new MyRestResource();

        final List<Project> testList = Collections.emptyList();

        resource.transformList(testList);

        assertEquals(Collections.emptyList(), testList);
    }

    @Test
    public void justTest() {
        assertNotNull(projectService);
    }
}