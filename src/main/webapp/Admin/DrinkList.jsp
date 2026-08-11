<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Drink Management</title>


<style>
* {
	box-sizing: border-box;
}

body {
	margin: 0;
	padding: 35px 25px;
	font-family: Arial, Helvetica, sans-serif;
	background: linear-gradient(135deg, #f8f3ed, #eee2d5);
	min-height: 100vh;
}

/* =========================
           MAIN CONTAINER
        ========================= */
.container {
	width: 1200px;
	max-width: 100%;
	margin: auto;
	background: white;
	border-radius: 16px;
	overflow: hidden;
	box-shadow: 0 15px 40px rgba(80, 35, 20, 0.18);
}

/* =========================
           HEADER
        ========================= */
.header {
	padding: 30px 35px;
	background: linear-gradient(135deg, #8b1e23, #a52a2f);
	color: white;
	display: flex;
	justify-content: space-between;
	align-items: center;
	gap: 20px;
}

.header-left h1 {
	margin: 0 0 7px;
	font-size: 28px;
}

.header-left p {
	margin: 0;
	color: #f4dfd2;
	font-size: 14px;
}

/* =========================
           CREATE BUTTON
        ========================= */
.btn-create {
	display: inline-block;
	padding: 12px 20px;
	border-radius: 8px;
	background: #d9a441;
	color: #4b3028;
	text-decoration: none;
	font-weight: bold;
	transition: 0.2s;
}

.btn-create:hover {
	background: #e5b65c;
	transform: translateY(-2px);
}

/* =========================
           CONTENT
        ========================= */
.content {
	padding: 30px;
}

/* =========================
           TABLE
        ========================= */
.table-wrapper {
	width: 100%;
	overflow-x: auto;
	border: 1px solid #e4d8cf;
	border-radius: 10px;
}

table {
	width: 100%;
	border-collapse: collapse;
	min-width: 900px;
}

thead {
	background: #f2e8df;
}

th {
	padding: 15px 12px;
	color: #5a382e;
	font-size: 14px;
	font-weight: 700;
	text-align: left;
	border-bottom: 2px solid #d9c7ba;
}

td {
	padding: 14px 12px;
	color: #4b3a34;
	font-size: 14px;
	border-bottom: 1px solid #eee5df;
	vertical-align: middle;
}

tbody tr {
	transition: 0.15s;
}

tbody tr:hover {
	background: #faf6f2;
}

/* =========================
           IMAGE
        ========================= */
.drink-img {
	width: 55px;
	height: 55px;
	object-fit: cover;
	border-radius: 8px;
	border: 1px solid #ddd0c7;
	background: #f5eee8;
}

.no-image {
	width: 55px;
	height: 55px;
	display: flex;
	align-items: center;
	justify-content: center;
	border-radius: 8px;
	background: #f0e7df;
	color: #92786b;
	font-size: 12px;
}

/* =========================
           CATEGORY
        ========================= */
.category {
	display: inline-block;
	padding: 5px 10px;
	border-radius: 20px;
	background: #f2e2d5;
	color: #754a38;
	font-size: 12px;
	font-weight: bold;
}

/* =========================
           PRICE
        ========================= */
.price {
	font-weight: bold;
	color: #8b1e23;
	white-space: nowrap;
}

/* =========================
           STATUS
        ========================= */
.status {
	display: inline-block;
	padding: 5px 10px;
	border-radius: 20px;
	font-size: 12px;
	font-weight: bold;
}

.available {
	background: #e4f2e7;
	color: #2e6b3b;
}

.unavailable {
	background: #f5dfdf;
	color: #8b3030;
}

/* =========================
           ACTION BUTTONS
        ========================= */
.actions {
	display: flex;
	gap: 7px;
}

.btn {
	display: inline-block;
	padding: 8px 12px;
	border-radius: 6px;
	text-decoration: none;
	font-size: 12px;
	font-weight: bold;
	transition: 0.2s;
}

.btn-edit {
	background: #ead9c7;
	color: #63432f;
}

.btn-edit:hover {
	background: #dcc4ad;
}

.btn-delete {
	background: #f2dddd;
	color: #8b3030;
}

.btn-delete:hover {
	background: #e7c4c4;
}

/* =========================
           EMPTY
        ========================= */
.empty {
	text-align: center;
	padding: 50px 20px;
	color: #927e72;
}

.empty-icon {
	font-size: 45px;
	margin-bottom: 10px;
}

.empty h3 {
	margin: 5px 0;
	color: #63483b;
}

/* =========================
           RESPONSIVE
        ========================= */
@media ( max-width : 700px) {
	body {
		padding: 15px 8px;
	}
	.header {
		flex-direction: column;
		align-items: stretch;
		text-align: center;
	}
	.btn-create {
		text-align: center;
	}
	.content {
		padding: 15px;
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
			<div class="header-left">
				<h1>☕ Drink Management</h1>
				<p>Quản lý thực đơn và các món uống</p>
			</div>
			<a href="${pageContext.request.contextPath}/DrinkServlet?action=add"
				class="btn-create"> + Create Drink </a>
		</div>

		<!-- =========================
         CONTENT
    ========================= -->

		<div class="content">
			<c:choose>
				<c:when test="${not empty list}">
					<div class="table-wrapper">
						<table>
							<thead>
								<tr>
									<th>ID</th>
									<th>Image</th>
									<th>Drink Name</th>
									<th>Price</th>
									<th>Category</th>
									<th>Status</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="d" items="${list}">
									<tr>
										<td>${d.idDrink}</td>
										<td><c:choose>
												<c:when test="${not empty d.drinkIMG}">
													<img
														src="${pageContext.request.contextPath}/images/${d.drinkIMG}.jpg"
														class="drink-img" alt="${d.drinkName}">
												</c:when>
												<c:otherwise>
													<div class="no-image">No Image</div>
												</c:otherwise>
											</c:choose></td>
										<td><strong> ${d.drinkName} </strong></td>
										<td><span class="price"> ${d.drinkPrice} VNĐ </span></td>
										<td><span class="category"> ${d.category} </span></td>
										<td><c:choose>
												<c:when test="${d.drinkActive}">
													<span class="status available"> Available </span>
												</c:when>
												<c:otherwise>
													<span class="status unavailable"> Unavailable </span>
												</c:otherwise>
											</c:choose></td>
										<td>
											<div class="actions">
												<a
													href="${pageContext.request.contextPath}/DrinkServlet?action=update&id=${d.idDrink}"
													class="btn btn-edit"> Edit </a> 
												<a
													href="${pageContext.request.contextPath}/DrinkServlet?action=delete&id=${d.idDrink}"
													class="btn btn-delete"
													onclick="return confirm('Bạn có chắc muốn xóa món này không?');">
													Delete </a>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:when>
				<c:otherwise>
					<div class="empty">
						<div class="empty-icon">☕</div>
						<h3>Chưa có món uống nào</h3>
						<p>Hãy thêm món đầu tiên vào thực đơn.</p>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>

</html>