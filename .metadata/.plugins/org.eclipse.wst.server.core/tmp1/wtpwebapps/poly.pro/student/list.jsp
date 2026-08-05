<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<table class="table table-sm table-hover">
    <thead class="table-info">
        <tr>
            <th>No.</th>
            <th>Image</th>
            <th>Id</th>
            <th>Name</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
    
    <c:forEach var="item" items="${items}">
        <tr>
            <td>STT</td>
            <td>
            	<c:url value="/photos/${item.photo}" var="photo"/>
            	<img src="${photo}" style="width:50px;height:50px">
            </td>
            <td>${item.id}</td>
            <td>${item.fullname}</td>
            <td>
                <a href="${path}/edit/${item.id}" class="badge rounded-pill bg-warning text-decoration-none px-3 me-1">Edit</a>
                <a href="${path}/delete/${item.id}" class="badge rounded-pill bg-danger text-decoration-none px-3">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>