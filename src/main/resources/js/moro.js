function loadProjects() {
    AJS.$.ajax({
          url: "/jira/rest/myrestresource/1.0/message",
          type: "GET",
          dataType: "json",
          success: function(data) {
            data.forEach(row => {
                let href = "/jira/projects/" + row.key + "/issues";
                let html = "<tr><td><a href=\"" + href + "\">" + row.name + "</a></td><td>" + row.key + "</td></tr>";
                $(".project-list tbody").append(html);
            })
            $(".project-list > table").toggleClass("hidden");
            $(".project-list > aui-spinner").toggleClass("hidden");
          }
        });
}