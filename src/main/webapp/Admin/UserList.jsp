<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>User Management</title>

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
        padding: 40px;
    }

    .container {
        max-width: 1100px;
        margin: auto;
        background: #ffffff;
        padding: 30px;
        border-radius: 20px;
        box-shadow: 0 10px 30px rgba(91, 25, 25, 0.15);
    }

    /* =========================
       HEADER
       ========================= */

    .header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 30px;
    }

    .title-area {
        display: flex;
        align-items: center;
        gap: 15px;
    }

    .icon {
        width: 55px;
        height: 55px;

        background: #8b1e1e;
        color: white;

        border-radius: 14px;

        display: flex;
        justify-content: center;
        align-items: center;

        font-size: 28px;

        box-shadow: 0 5px 12px rgba(139, 30, 30, 0.25);
    }

    .title-area h1 {
        color: #7a1717;
        font-size: 28px;
        margin-bottom: 5px;
    }

    .title-area p {
        color: #8a7a70;
        font-size: 14px;
    }

    /* =========================
       ADD BUTTON
       ========================= */

    .btn-add {
        text-decoration: none;

        background: #8b1e1e;
        color: white;

        padding: 12px 18px;

        border-radius: 10px;

        font-size: 14px;
        font-weight: bold;

        transition: 0.2s;

        box-shadow: 0 4px 10px rgba(139, 30, 30, 0.2);
    }

    .btn-add:hover {
        background: #6f1515;
        transform: translateY(-2px);
    }

    /* =========================
       TABLE
       ========================= */

    .table-wrapper {
        overflow-x: auto;
        border-radius: 12px;
    }

    table {
        width: 100%;
        border-collapse: collapse;
    }

    thead {
        background: #8b1e1e;
        color: white;
    }

    th {
        padding: 15px;
        text-align: left;
        font-size: 14px;
    }

    td {
        padding: 15px;
        border-bottom: 1px solid #eee3da;
        color: #5f5149;
        font-size: 14px;
    }

    tbody tr {
        transition: 0.2s;
    }

    tbody tr:hover {
        background: #fff8f2;
    }

    /* =========================
       USER ID
       ========================= */

    .user-id {
        font-weight: bold;
        color: #8b1e1e;
    }

    /* =========================
       ROLE BADGE
       ========================= */

    .role {
        display: inline-block;

        padding: 6px 12px;

        border-radius: 20px;

        font-size: 12px;
        font-weight: bold;
    }

    .admin {
        background: #f3dada;
        color: #8b1e1e;
    }

    .employee {
        background: #eee0d1;
        color: #795548;
    }

    .customer {
        background: #e8e0d8;
        color: #68584d;
    }

    /* =========================
       ACTION BUTTONS
       ========================= */

    .actions {
        display: flex;
        gap: 8px;
    }

    .btn-edit,
    .btn-delete {
        text-decoration: none;

        padding: 7px 12px;

        border-radius: 8px;

        font-size: 12px;
        font-weight: bold;

        transition: 0.2s;
    }

    .btn-edit {
        background: #f1e1d3;
        color: #7a1717;
    }

    .btn-edit:hover {
        background: #e5cdb9;
    }

    .btn-delete {
        background: #f5dddd;
        color: #a52b2b;
    }

    .btn-delete:hover {
        background: #ecc6c6;
    }

    /* =========================
       EMPTY DATA
       ========================= */

    .empty {
        text-align: center;
        padding: 40px;
        color: #9b8c83;
    }

    /* =========================
       FOOTER
       ========================= */

    .footer {
        margin-top: 20px;
        color: #a18f84;
        font-size: 13px;
        text-align: right;
    }

    /* =========================
       RESPONSIVE
       ========================= */

    @media (max-width: 700px) {

        body {
            padding: 20px;
        }

        .container {
            padding: 20px;
        }

        .header {
            flex-direction: column;
            align-items: flex-start;
            gap: 20px;
        }

        .btn-add {
            width: 100%;
            text-align: center;
        }

        th,
        td {
            padding: 10px;
        }

    }

</style>

</head>


<body>


<div class="container">


    <!-- =========================
         HEADER
         ========================= -->

    <div class="header">

        <div class="title-area">

            <div class="icon">
                ☕
            </div>

            <div>

                <h1>User Management</h1>

                <p>
                    Manage users of PolyCoffee
                </p>

            </div>

        </div>


        <a class="btn-add"
           href="${pageContext.request.contextPath}/UserList?action=add">

            + Add User

        </a>

    </div>


    <!-- =========================
         USER TABLE
         ========================= -->

    <div class="table-wrapper">

        <table>

            <thead>

                <tr>

                    <th>ID</th>

                    <th>Username</th>

                    <th>Password</th>

                    <th>Phone</th>

                    <th>Email</th>

                    <th>Role</th>

                    <th>Action</th>

                </tr>

            </thead>


            <tbody>

    <c:choose>

        <c:when test="${not empty list}">

            <c:forEach var="u" items="${list}">

                <tr>

                    <td class="user-id">
                        ${u.userId}
                    </td>

                    <td>
                        ${u.userName}
                    </td>

                    <td>
                        ${u.password}
                    </td>

                    <td>
                        ${u.userPhone}
                    </td>

                    

                    <td>

                        <c:choose>
                            <c:when test="${u.role == 'Admin'}">
                                <span class="role admin">
                                    Admin
                                </span>
                            </c:when>

                            <c:when test="${u.role == 'Employee'}">
                                <span class="role employee">
                                    Employee
                                </span>
                            </c:when>

                            <c:when test="${u.role == 'Customer'}">
                                <span class="role customer">
                                    Customer
                                </span>
                            </c:when>

                            <c:otherwise>
                                <span class="role">
                                    ${u.role}
                                </span>
                            </c:otherwise>
                        </c:choose>

                    </td>

                    <td>

                        <div class="actions">

                            <a class="btn-edit"
                               href="${pageContext.request.contextPath}/UserList?action=UpdateUser&id=${u.userId}">
                                Edit
                            </a>

                            <a class="btn-delete"
                               href="${pageContext.request.contextPath}/UserList?action=DeleteUser&id=${u.userId}"
                               onclick="return confirm('Bạn có chắc muốn xóa User này không?');">
                                Delete
                            </a>

                        </div>

                    </td>

                </tr>

            </c:forEach>

        </c:when>

        <c:otherwise>

            <tr>

                <td colspan="7" class="empty">
                    ☕ Chưa có User nào trong hệ thống.
                </td>

            </tr>

        </c:otherwise>

    </c:choose>

</tbody>

        </table>

    </div>


    <!-- =========================
         FOOTER
         ========================= -->

    <div class="footer">

        PolyCoffee • User Management

    </div>


</div>


</body>

</html>