<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:url var="path" value="/coffee" />

<form method="post"
      enctype="multipart/form-data">

    <input name="id" value="${form.id}">

    <input name="name" value="${form.name}">

    <textarea name="description">${form.description}</textarea>

    <input name="price" value="${form.price}">

    <input type="file" name="photo-file">

    <button formaction="${path}/create">
        Create
    </button>

    <button formaction="${path}/update">
        Update
    </button>

</form>

<hr>

<!-- Your table here -->

<table class="table table-sm table-hover">
    <thead class="table-info">
        <tr>
            <th>No.</th>
            <th>Photo</th>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Action</th>
        </tr>
    </thead>

    <tbody>
        <c:forEach var="item" items="${items}" varStatus="status">
            <tr>
                <td>${status.count}</td>

                <td>
                    <img src="${pageContext.request.contextPath}/photos/${item.photo}"
                         width="60" height="60">
                </td>

                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.description}</td>
                <td>${item.price}</td>

                <td>
                    <a href="${path}/edit/${item.id}"
                       class="badge rounded-pill bg-warning text-decoration-none px-3 me-1">
                        Edit
                    </a>

                    <a href="${path}/delete/${item.id}"
                       class="badge rounded-pill bg-danger text-decoration-none px-3">
                        Delete
                    </a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>