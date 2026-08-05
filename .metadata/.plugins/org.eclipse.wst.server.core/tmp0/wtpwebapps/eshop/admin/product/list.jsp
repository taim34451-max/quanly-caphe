<%@ include file="/admin/shared/page.jsp" %>

<div class="card">
    <div class="card-header">
        <h5 class="card-title">
        	Product List
        </h5>
    </div>
    <table class="table table-sm table-hover">
        <thead class="table-info">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Unit Price</th>
                <th>Quantity</th>
                <th>Available</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="p" items="${list}">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td>${p.price}</td>
                    <td>${p.quantity}</td>
                    <td>${p.available ? 'Yes' : 'No'}</td>
                    <td class="text-end">
                    	<a href="${path}/edit/${p.id}" class="btn btn-sm btn-warning rounded-pill px-3">
				            <i class="bi bi-pencil-square"></i> Edit
				        </a>
                    	<a href="${path}/delete/${p.id}" class="btn btn-sm btn-danger rounded-pill px-3">
				            <i class="bi bi-trash"></i> Delete
				        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>