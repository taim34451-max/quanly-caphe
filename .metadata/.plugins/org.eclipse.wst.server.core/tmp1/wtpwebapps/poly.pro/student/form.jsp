<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

 <form action="${path}/index" method="post" enctype="multipart/form-data">
 	<div class="row">
	    <div class="col">
	    	<div class="mb-2">
		         <label class="form-label">Student Id</label>
		         <input name="id" value="${form.id}" class="form-control">
		     </div>
	    </div>
	    <div class="col">
	    	<div class="mb-2">
		         <label class="form-label">Fullname</label>
		         <input name="fullname" value="${form.fullname}" class="form-control">
		     </div>
	    </div>
	</div>
    <div class="row">
	    <div class="col">
	    	<div class="mb-2">
			    <label class="form-label">Gender</label>
			    <div class="form-control d-flex">
			        <label class="form-check">
			            <input ${form.gender?'checked':''} name="gender" value="true" type="radio"> Male
			        </label>
			        <label class="form-check">
			            <input ${form.gender?'':'checked'} name="gender" value="false" type="radio"> Female
			        </label>
			    </div>
			</div>
	    </div>
	    <div class="col">
	    	<div class="mb-2">
			    <label class="form-label">Photo</label>
			    <input type="file" name="photo_file" class="form-control">
			    <input type="hidden" name="photo" value="${form.photo}">
			</div>
	    </div>
	</div>
	<div class="row">
	    <div class="col">
	    	<div class="mb-2">
			    <label class="form-label">Birthday</label>
			    <input name="birthday" value="${form.birthday}" type="date" class="form-control">
			</div>
	    </div>
	    <div class="col">
	    	<div class="mb-2">
			    <label class="form-label">Email</label>
			    <input name="email" value="${form.email}" class="form-control">
			</div>
	    </div>
	</div>
	<div class="row">
	    <div class="col">
	    	<div class="mb-2">
			    <label class="form-label">Mark</label>
			    <input name="mark" value="${form.mark}" class="form-control">
			</div>
	    </div>
	    <div class="col"></div>
	</div>
     <div class="mb-2">
         <button formaction="${path}/create" class="btn btn-sm btn-primary">
             Create
         </button>
         <button formaction="${path}/update" class="btn btn-sm btn-success">
             Update
         </button>
         <a href="${path}/delete/${form.id}" class="btn btn-sm btn-danger">
             Delete
         </a>
         <a href="${path}/clear" class="btn btn-sm btn-info">
             Clear
         </a>
     </div>
 </form>