<%@ include file="/admin/shared/page.jsp" %>

<form class="card" method="post">
    <div class="card-header">
        <h5 class="card-title">User Edition</h5>
    </div>
    <div class="card-body row">
        <div class="col">
            <div>
                <div class="form-label">Email</div>
                <input name="email" value="${form.email}" class="form-control">
            </div>
            <div>
                <div class="form-label">Password</div>
                <input name="password" value="${form.password}" class="form-control">
            </div>
            <div>
                <div class="form-label">Fullname</div>
                <input name="fullname" value="${form.fullname}" class="form-control">
            </div>
        </div>
        <div class="col">
            <div>
                <div class="form-label">Mobile</div>
                <input name="mobile" value="${form.mobile}" class="form-control">
            </div>
            <div>
                <div class="form-label">Enabled?</div>
                <div class="form-control d-flex">
                    <div class="form-check me-2">
                        <input ${form.enabled ? 'checked' : '' } value="true" type="radio" name="enabled" id="yes"
                            class="form-check-input">
                        <label for="yes" class="form-check-label">Yes</label>
                    </div>
                    <div class="form-check">
                        <input ${form.enabled ? '' : 'checked' } value="false" type="radio" name="enabled" id="no"
                            class="form-check-input">
                        <label for="no" class="form-check-label">No</label>
                    </div>
                </div>
            </div>
            <div>
                <div class="form-label">Role</div>
                <div class="form-control d-flex">
                    <div class="form-check me-2">
                        <input ${form.role==0 ? 'checked' : '' } value="0" type="radio" name="role" id="user"
                            class="form-check-input">
                        <label for="user" class="form-check-label">User</label>
                    </div>
                    <div class="form-check">
                        <input ${form.role==1 ? 'checked' : '' } value="1" type="radio" name="role" id="admin"
                            class="form-check-input">
                        <label for="admin" class="form-check-label">Administrator</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="card-footer text-end">
        <button formaction="${path}/create" class="btn btn-sm btn-primary rounded-pill px-3">Create</button>
        <button formaction="${path}/update" class="btn btn-sm btn-success rounded-pill px-3">Update</button>
        <a href="${path}/delete/${form.email}" class="btn btn-sm btn-danger rounded-pill px-3">Delete</a>
        <a href="${path}/clear" class="btn btn-sm btn-info rounded-pill px-3">Clear</a>
    </div>
</form>