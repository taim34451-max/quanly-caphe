<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="tab-content p-4 border border-dark bg-dark text-light">
    <div class="tab-pane fade show active" id="edit">
        <form action="${pageContext.request.contextPath}/admin/video/edit" method="post">
            <div class="row">
                <div class="col-md-4 text-center border border-secondary p-3">
                    <div style="height: 150px; background: #000; display: flex; align-items: center; justify-content: center;">POSTER</div>
                </div>
                <div class="col-md-8">
                    <label>Youtube ID?</label>
                    <input type="text" name="id" class="form-control mb-2" value="${video.id}">
                    <label>Video Title?</label>
                    <input type="text" name="title" class="form-control mb-2" value="${video.title}">
                    <label>View Count?</label>
                    <input type="number" name="views" class="form-control mb-2" value="${video.views}">
                    <div>
                        <input type="radio" name="active" value="true" ${video.active ? 'checked' : ''}> Active 
                        <input type="radio" name="active" value="false" ${!video.active ? 'checked' : ''} class="ms-3"> Inactive
                    </div>
                </div>
            </div>
            <label class="mt-2">Description?</label>
            <textarea name="description" class="form-control mb-3" rows="3">${video.description}</textarea>
            
            <button type="submit" formaction="${pageContext.request.contextPath}/admin/video/create" class="btn btn-primary">Create</button>
            <button type="submit" formaction="${pageContext.request.contextPath}/admin/video/update" class="btn btn-success">Update</button>
            <button type="submit" formaction="${pageContext.request.contextPath}/admin/video/delete" class="btn btn-danger">Delete</button>
            <button type="submit" formaction="${pageContext.request.contextPath}/admin/video/reset" class="btn btn-secondary">Reset</button>
        </form>
    </div>

    <div class="tab-pane fade" id="list">
        <table class="table table-dark table-hover">
            <thead>
                <tr><th>ID</th><th>Title</th><th>Views</th><th>Status</th><th>Action</th></tr>
            </thead>
            <tbody>
               <c:forEach var="v" items="${videos}">
                <tr>
                    <td>${v.id}</td><td>${v.title}</td><td>${v.views}</td>
                    <td>${v.active ? 'Active' : 'Inactive'}</td>
                    <td><a href="${pageContext.request.contextPath}/admin/video/edit/${v.id}" class="btn btn-sm btn-outline-light">Edit</a></td>
                </tr>
               </c:forEach>
            </tbody>
        </table>
    </div>
</div>