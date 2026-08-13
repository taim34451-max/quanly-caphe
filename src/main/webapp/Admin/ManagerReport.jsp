<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ include file="//NewFile.jsp" %>
		<!DOCTYPE html>
		<html>
<link rel="stylesheet" href="User/dinh-dang.css">
		<head>
			<meta charset="UTF-8">
			<title>Manager Report</title>
			<style>
        body {
            font-family: Arial, sans-serif;
            margin: 30px;
            background: #f5f5f5;
        }

        h1 {
            margin-bottom: 30px;
        }

        .revenue-container {
            display: flex;
            gap: 20px;
            margin-bottom: 40px;
        }

        .revenue-box {
            background: white;
            padding: 20px;
            width: 200px;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }

        .revenue-box h3 {
            margin-top: 0;
        }

        .revenue {
            font-size: 22px;
            font-weight: bold;
        }

        .report-section {
            background: white;
            padding: 20px;
            margin-bottom: 30px;
            border-radius: 8px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }

        th {
            background: #eee;
        }
    </style>
		</head>

		<body>
			<%@ include file="/Header.jsp" %>
				<h1>Manager Report</h1>


				<!-- ================================================= -->
				<!-- REVENUE -->
				<!-- ================================================= -->

				<div class="revenue-container">

					<div class="revenue-box">
						<h3>Daily Revenue</h3>
						<div class="revenue">
							${dailyRevenue} VNĐ
						</div>
					</div>

					<div class="revenue-box">
						<h3>Weekly Revenue</h3>
						<div class="revenue">
							${weeklyRevenue} VNĐ
						</div>
					</div>

					<div class="revenue-box">
						<h3>Monthly Revenue</h3>
						<div class="revenue">
							${monthlyRevenue} VNĐ
						</div>
					</div>

				</div>


				<!-- ================================================= -->
				<!-- HIGHEST REVENUE ITEMS -->
				<!-- ================================================= -->

				<div class="report-section">

					<h2>Highest Revenue Items</h2>

					<table>

						<thead>
							<tr>
								<th>ID</th>
								<th>Product</th>
								<th>Category</th>
								<th>Quantity Sold</th>
								<th>Revenue</th>
							</tr>
						</thead>

						<tbody>

							<c:forEach var="item" items="${highestItems}">

								<tr>

									<!-- productId -->
									<td>${item[0]}</td>

									<!-- productName -->
									<td>${item[1]}</td>

									<!-- category -->
									<td>${item[2]}</td>

									<!-- quantity -->
									<td>${item[3]}</td>

									<!-- revenue -->
									<td>${item[4]} VNĐ</td>

								</tr>

							</c:forEach>

						</tbody>

					</table>

				</div>


				<!-- ================================================= -->
				<!-- SALES BY CATEGORY -->
				<!-- ================================================= -->

				<div class="report-section">

					<h2>Sales by Category</h2>

					<table>

						<thead>
							<tr>
								<th>Category</th>
								<th>Quantity Sold</th>
								<th>Revenue</th>
							</tr>
						</thead>

						<tbody>

							<c:forEach var="item" items="${categorySales}">

								<tr>

									<!-- category -->
									<td>${item[0]}</td>


									<!-- quantity -->
									<td>${item[1]}</td>

									<!-- revenue -->
									<td>${item[2]} VNĐ</td>

								</tr>

							</c:forEach>

						</tbody>

					</table>

				</div>
		</body>

		</html>