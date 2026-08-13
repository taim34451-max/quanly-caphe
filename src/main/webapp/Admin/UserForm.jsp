<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>
    ${mode == 'update' ? 'Edit User' : 'Create User'}
</title>

<style>

    * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
        font-family: Arial, sans-serif;
    }

    body {
        min-height: 100vh;

        background: linear-gradient(135deg, #f7f1e8, #ead8c8);

        display: flex;
        justify-content: center;
        align-items: center;

        padding: 30px;
    }


    /* =========================
       FORM CONTAINER
       ========================= */

    .form-container {

        width: 100%;
        max-width: 550px;

        background: #ffffff;

        padding: 35px;

        border-radius: 20px;

        box-shadow: 0 10px 30px rgba(91, 25, 25, 0.15);
    }


    /* =========================
       HEADER
       ========================= */

    .form-header {

        text-align: center;

        margin-bottom: 30px;
    }


    .icon {

        width: 60px;
        height: 60px;

        margin: 0 auto 15px;

        background: #8b1e1e;

        color: white;

        border-radius: 16px;

        display: flex;

        justify-content: center;

        align-items: center;

        font-size: 30px;

        box-shadow: 0 5px 12px rgba(139, 30, 30, 0.25);
    }


    .form-header h1 {

        color: #7a1717;

        font-size: 28px;

        margin-bottom: 8px;
    }


    .form-header p {

        color: #8a7a70;

        font-size: 14px;
    }


    /* =========================
       FORM GROUP
       ========================= */

    .form-group {

        margin-bottom: 18px;
    }


    .form-group label {

        display: block;

        margin-bottom: 7px;

        color: #5f5149;

        font-size: 14px;

        font-weight: bold;
    }


    .form-group input,
    .form-group select {

        width: 100%;

        padding: 12px 14px;

        border: 1px solid #dccfc4;

        border-radius: 10px;

        background: #fffdfb;

        color: #4f4038;

        font-size: 14px;

        outline: none;

        transition: 0.2s;
    }


    .form-group input:focus,
    .form-group select:focus {

        border-color: #8b1e1e;

        box-shadow: 0 0 0 3px rgba(139, 30, 30, 0.10);
    }


    /* =========================
       ROLE SELECT
       ========================= */

    .form-group select {

        cursor: pointer;
    }


    /* =========================
       BUTTON AREA
       ========================= */

    .button-area {

        display: flex;

        gap: 10px;

        margin-top: 25px;
    }


    .btn {

        flex: 1;

        padding: 12px;

        border: none;

        border-radius: 10px;

        font-size: 14px;

        font-weight: bold;

        cursor: pointer;

        text-align: center;

        text-decoration: none;

        transition: 0.2s;
    }


    /* CREATE / UPDATE */

    .btn-primary {

        background: #8b1e1e;

        color: white;

        box-shadow: 0 4px 10px rgba(139, 30, 30, 0.2);
    }


    .btn-primary:hover {

        background: #6f1515;

        transform: translateY(-2px);
    }


    /* CANCEL */

    .btn-secondary {

        background: #eee0d1;

        color: #6f5140;
    }


    .btn-secondary:hover {

        background: #e3d0bd;
    }


    /* =========================
       BACK LINK
       ========================= */

    .back-link {

        display: block;

        text-align: center;

        margin-top: 20px;

        color: #8b1e1e;

        text-decoration: none;

        font-size: 13px;

        font-weight: bold;
    }


    .back-link:hover {

        text-decoration: underline;
    }


    /* =========================
       RESPONSIVE
       ========================= */

    @media (max-width: 600px) {

        body {

            padding: 20px;
        }


        .form-container {

            padding: 25px;
        }


        .button-area {

            flex-direction: column;
        }

    }

</style>

</head>

<body>

<div class="form-container">

    <div class="form-header">

        <div class="icon">☕</div>

        <h1>
            ${mode == 'update' ? 'Edit User' : 'Create User'}
        </h1>

        <p>
            ${mode == 'update'
                ? 'Update user information'
                : 'Add a new user to the system'}
        </p>

    </div>


    <form action="${pageContext.request.contextPath}/UserList"
          method="post">

        <!-- ACTION -->

        <input type="hidden"
               name="action"
               value="${mode == 'update' ? 'updateUser' : 'create'}">


        <!-- USER ID -->

        <c:if test="${mode == 'update'}">

            <input type="hidden"
                   name="userId"
                   value="${user.userId}">

        </c:if>


        <!-- USERNAME -->

        <div class="form-group">

            <label>
                Username <span class="required">*</span>
            </label>

            <!-- USERNAME -->
		<input type="text"
		       name="userName"
		       value="${mode == 'update' ? user.userName : ''}"
		       placeholder="Enter username"
		       required>

        </div>


        <!-- PASSWORD -->

        <div class="form-group">

            <label>
                Password <span class="required">*</span>
            </label>

            <input type="password"
		       name="userPass"
		       value="${mode == 'update' ? user.password : ''}"
		       placeholder="Enter password"
		       required>

        </div>


        <!-- PHONE -->

        <div class="form-group">

            <label>
                Phone
            </label>

            <input type="text"
       name="userPhone"
       value="${mode == 'update' ? user.userPhone : ''}"
       placeholder="Enter phone number">

        </div>


        <!-- EMAIL -->

        <div class="form-group">

            <label>
                Email
            </label>

            <input type="email"
       name="userEmail"
       value="${mode == 'update' ? user.email : ''}"
       placeholder="Enter email">

        </div>


        <!-- ROLE -->

        <div class="form-group">

            <label>
                Role <span class="required">*</span>
            </label>

            <select name="role" required>

               <option value="">-- Select Role --</option>

				<option value="ADMIN"
				    ${mode == 'update' && user.role == 'ADMIN' ? 'selected' : ''}>
				    ADMIN
				</option>
				
				<option value="BARISTA"
				    ${mode == 'update' && user.role == 'BARISTA' ? 'selected' : ''}>
				    BARISTA
				</option>
				
				<option value="USER"
				    ${mode == 'update' && user.role == 'USER' ? 'selected' : ''}>
				    USER
				</option>
            </select>

        </div>


        <!-- BUTTONS -->

        <div class="button-group">

            <button type="submit"
                    class="btn btn-submit">

                ${mode == 'update' ? 'Update User' : 'Create User'}

            </button>


            <button type="button"
                    class="btn btn-cancel"
                    onclick="window.location.href='${pageContext.request.contextPath}/UserList'">

                Cancel

            </button>

        </div>

    </form>

</div>

</body>
</html>