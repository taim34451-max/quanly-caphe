<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<c:url var="path" value="/student" scope="request" />

<!-- Nav tabs -->
<ul class="nav nav-tabs">
  <li class="nav-item">
    <a class="nav-link ${tab==0?'active':''}" data-bs-toggle="tab" href="#list">List</a>
  </li>
  <li class="nav-item">
    <a class="nav-link ${tab==1?'active':''}" data-bs-toggle="tab" href="#edit">Edition</a>
  </li>
</ul>

<!-- Tab panes -->
<div class="tab-content">
  <div class="tab-pane container ${tab==0?'active':'fade'}" id="list">
  	<jsp:include page="list.jsp"/>
  </div>
  <div class="tab-pane container ${tab==1?'active':'fade'}" id="edit">
  	<jsp:include page="form.jsp"/>
  </div>
</div>