<%@ include file="../shared/page.jsp" %>

<main class="row mt-3">
	<div class="col-4 offset-4">
		<form action="${ctxurl}/auth/login" method="post" class="card shadow-sm">
		    <div class="card-header">
		        <h5 class="card-title">
		        	<i class="bi bi-cart"></i> Online Shopping
		        </h5>
		    </div>
		    <div class="card-body">
		        <div class="mb-2">
		            <div class="form-label">
		            	<i class="bi bi-person"></i> Username
		            </div>
		            <input name="username" class="form-control">
		        </div>
		        <div class="mb-2">
		            <div class="form-label">
		            	<i class="bi bi-shield-lock"></i> Password
		            </div>
		            <input name="password" type="password" class="form-control">
		        </div>
		        <div class="form-check">
		            <input name="remember" type="checkbox" id="rem" class="form-check-input">
		            <label for="rem" class="form-check-label">Remember me?</label>
		        </div>
		    </div>
		    <div class="card-footer clearfix">
		    	<i class="float-start text-danger">
		    		<i class="bi bi-exclamation-triangle"></i> ${msg}${param.msg}
		    	</i>
		        <button class="float-end btn btn-sm rounded-pill px-3 btn-primary">
		            <i class="bi bi-shield-check"></i> Login
		        </button>
		    </div>
		</form>
	</div>
</main>
