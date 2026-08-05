<%@ include file="/admin/shared/page.jsp" %>

<form class="card" method="post" enctype="multipart/form-data">
    <div class="card-header">
        <h5 class="card-title">
        	Product Edition
        </h5>
    </div>
    <div class="card-body">
        <div class="row">
            <div class="col">
                <input name="id" value="${form.id}" type="hidden">
                <div>
                    <div class="form-label">Name</div>
                    <input name="name" value="${form.name}" class="form-control">
                </div>
                <div>
                    <div class="form-label">Image</div>
                    <input name="image" value="${form.image}" type="hidden">
                    <input name="image_file" type="file" class="form-control">
                </div>
                <div>
                    <div class="form-label">Unit Price</div>
                    <input name="image" value="${form.price}" class="form-control">
                </div>
            </div>
            <div class="col">
                <div>
                    <div class="form-label">Quantity</div>
                    <input name="quantity" value="${form.quantity}" class="form-control">
                </div>
                <div>
                    <div class="form-label">Product Date</div>
                    <f:formatDate var="date" value="${form.prodate}" pattern="yyyy-MM-dd" />
                    <input name="prodate" value="${date}" type="date" class="form-control">
                </div>
                <div>
                    <div class="form-label">Available?</div>
                    <div class="form-control d-flex">
                        <div class="form-check me-2">
                            <input ${form.available ? 'checked' : '' } value="true" type="radio" name="available"
                                id="available" class="form-check-input">
                            <label for="available" class="form-check-label">Yes</label>
                        </div>
                        <div class="form-check">
                            <input ${form.available ? '' : 'checked' } value="false" type="radio" name="available"
                                id="available" class="form-check-input">
                            <label for="available" class="form-check-label">No</label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <div class="form-label">Description</div>
            <textarea name="description" class="form-control">${form.description}</textarea>
        </div>
    </div>
    <div class="card-footer text-end">
        <button formaction="${path}/create" class="btn btn-sm btn-primary rounded-pill px-3">
            <i class="bi bi-bookmark-plus"></i> Create
        </button>
        <button formaction="${path}/update" class="btn btn-sm btn-success rounded-pill px-3">
            <i class="bi bi-bookmark-check"></i> Update
        </button>
        <a href="${path}/delete/${form.id}" class="btn btn-sm btn-danger rounded-pill px-3">
            <i class="bi bi-trash"></i> Delete
        </a>
        <a href="${path}/index" class="btn btn-sm btn-info rounded-pill px-3">
            <i class="bi bi-recycle"></i> Clear
        </a>
    </div>
</form>