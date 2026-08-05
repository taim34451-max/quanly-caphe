<%@ include file="/admin/shared/page.jsp" %>

<div class="card">
    <div class="card-header">
        <h5 class="card-title">User List</h5>
    </div>
    <table class="table table-sm table-hover">
        <thead class="table-info">
            <tr>
                <th>Email</th>
                <th>Fullname</th>
                <th>Mobile</th>
                <th>Role</th>
                <th>Enabled?</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="u" items="${list}" varStatus="s">
                <tr>
                    <td>${u.email}</td>
                    <td>${u.fullname}</td>
                    <td>${u.mobile}</td>
                    <td>${u.role == 0 ? 'User' : 'Administrator'}</td>
                    <td>${u.enabled ? 'Yes' : 'No' }</td>
                    <td class="text-end">
                        <a href="${path}/edit/${u.email}" class="btn btn-sm btn-warning rounded-pill px-3">Edit</a> 
                        <a href="${path}/delete/${u.email}" class="btn btn-sm btn-danger rounded-pill px-3">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>