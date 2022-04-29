package com.atlassian.raman.rest;

import com.atlassian.jira.project.Project;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.atlassian.jira.component.ComponentAccessor;

import java.util.ArrayList;
import java.util.List;

@Path("/message")
public class MyRestResource {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getMessage()
    {
        List<Project> projectList = ComponentAccessor.getProjectManager().getProjectObjects();

        return Response.ok(transformList(projectList)).build();
    }

    public List<MyRestResourceModel> transformList(List<Project> projectList) {
        List<MyRestResourceModel> result = new ArrayList<>();
        projectList.forEach((Project p) -> result.add(new MyRestResourceModel(p.getName(), p.getKey())));
        return result;
    }
}