<%@ include file="/admin/shared/page.jsp" %>
<c:url var="path" value="/admin/product" scope="request"/>

<main>
	<h4>PRODUCT MANAGEMENT</h4>
	<!-- Nav tabs -->
	<ul class="nav nav-tabs">
	    <li class="nav-item">
	        <a class="nav-link ${tab == 0 ? 'active' : ''}" data-bs-toggle="tab" href="#list">
	            <i class="bi bi-list"></i> LIST
	        </a>
	    </li>
	    <li class="nav-item">
	        <a class="nav-link ${tab == 1 ? 'active' : ''}" data-bs-toggle="tab" href="#edit">
	            <i class="bi bi-pencil-square"></i> EDIT
	        </a>
	    </li>
	</ul>
	<!-- Tab panes -->
	<div class="tab-content">
	    <div class="tab-pane ${tab == 0 ? 'active' : 'fade'}" id="list">
			<jsp:include page="list.jsp"/>
	    </div>
	    <div class="tab-pane ${tab == 1 ? 'active' : 'fade'}" id="edit">
			<jsp:include page="form.jsp"/>
	    </div>
	</div>
</main>