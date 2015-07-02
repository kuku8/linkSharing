<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="dash_head">
    <title>LinkSharing</title>

    </head>


<body>
<div >
    <g:if test="${flash.message}">
        <div class="message" role="status" style="color:red;"align="right">${flash.message}</div>
    </g:if>


<div class="panel panel-default" align="left">
    <div class="panel-heading">
        <h3 class="panel-title">Share Link</h3>
    </div>
 <div class="panel-body"  >
<form action="save">
    <div class="form-group">
        <label for="url">Link</label>
        <input type="text" class="form-control" placeholder="URL" id="url" name="url">
    </div>
    <div class="form-group">
        <label for="description">Description</label>
        <textarea class="form-control" rows="5" id="description" name="description" placeholder="Description"></textarea>
    </div>
    <label>Topic</label>

    <g:select name="topic" from="${topics}" optionKey="id" optionValue="name"
    />
    <button type="submit" class="btn btn-default">Share</button>
    <button class="btn btn-default">Cancel</button>
</form>
     </div>
</div>


    </div>
</body>
</html>