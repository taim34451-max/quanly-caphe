<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>
        <c:choose>
            <c:when test="${edit}">
                Edit Drink
            </c:when>

            <c:otherwise>
                Create Drink
            </c:otherwise>
        </c:choose>
    </title>


    <style>

        * {
            box-sizing: border-box;
        }


        body {

            margin: 0;

            padding: 35px 20px;

            font-family:
                Arial,
                Helvetica,
                sans-serif;

            background:
                linear-gradient(
                    135deg,
                    #f8f3ed,
                    #eee2d5
                );

            min-height: 100vh;
        }


        /* =========================
           CONTAINER
        ========================= */

        .container {

            width: 850px;

            max-width: 100%;

            margin: auto;

            background: white;

            border-radius: 20px;

            overflow: hidden;

            box-shadow:
                0 15px 40px
                rgba(80, 35, 20, 0.18);
        }


        /* =========================
           HEADER
        ========================= */

        .header {

            background:
                linear-gradient(
                    135deg,
                    #8b1e23,
                    #a52a2f
                );

            color: white;

            text-align: center;

            padding: 45px 30px 40px;

            position: relative;
        }


        .header::after {

            content: "";

            position: absolute;

            bottom: 0;

            left: 50%;

            transform: translateX(-50%);

            width: 88px;

            height: 5px;

            background: #d9a441;

            border-radius: 10px;
        }


        .header h1 {

            margin: 0 0 10px;

            font-size: 36px;
        }


        .header p {

            margin: 0;

            color: #f6e5da;

            font-size: 16px;
        }


        /* =========================
           FORM
        ========================= */

        .form-container {

            padding: 40px 50px 45px;
        }


        .form-group {

            margin-bottom: 25px;
        }


        label {

            display: block;

            margin-bottom: 9px;

            color: #4d3028;

            font-weight: bold;

            font-size: 16px;
        }


        input,
        select,
        textarea {

            width: 100%;

            padding: 15px 18px;

            border: 1px solid #d8c5b8;

            border-radius: 10px;

            background: white;

            color: #3f302b;

            font-size: 16px;

            outline: none;

            transition: 0.2s;
        }


        input:focus,
        select:focus,
        textarea:focus {

            border-color: #a52a2f;

            box-shadow:
                0 0 0 3px
                rgba(165, 42, 47, 0.10);
        }


        input.readonly {

            background: #f1ebe6;

            color: #765f54;

            cursor: not-allowed;
        }


        textarea {

            min-height: 110px;

            resize: vertical;
        }


        /* =========================
           CATEGORY
        ========================= */

        select {

            cursor: pointer;

            appearance: auto;
        }


        /* =========================
           CHECKBOX
        ========================= */

        .checkbox-wrapper {

            display: flex;

            align-items: center;

            gap: 10px;

            padding-top: 5px;
        }


        .checkbox-wrapper input {

            width: 18px;

            height: 18px;

            cursor: pointer;

            accent-color: #8b1e23;
        }


        .checkbox-wrapper label {

            margin: 0;

            cursor: pointer;

            font-weight: normal;
        }


        /* =========================
           BUTTONS
        ========================= */

        .buttons {

            display: flex;

            justify-content: flex-end;

            gap: 12px;

            margin-top: 35px;

            padding-top: 25px;

            border-top:
                1px solid #eadfd8;
        }


        .btn {

            border: none;

            border-radius: 9px;

            padding: 13px 23px;

            font-size: 15px;

            font-weight: bold;

            text-decoration: none;

            cursor: pointer;

            transition: 0.2s;

            display: inline-block;
        }


        .btn-save {

            background: #8b1e23;

            color: white;
        }


        .btn-save:hover {

            background: #72171c;

            transform: translateY(-2px);

            box-shadow:
                0 5px 12px
                rgba(139, 30, 35, 0.25);
        }


        .btn-cancel {

            background: #eee5de;

            color: #63483b;
        }


        .btn-cancel:hover {

            background: #e0d3ca;

            transform: translateY(-2px);
        }


        /* =========================
           RESPONSIVE
        ========================= */

        @media (max-width: 650px) {

            body {

                padding: 15px 8px;
            }


            .form-container {

                padding: 30px 20px;
            }


            .header h1 {

                font-size: 28px;
            }


            .buttons {

                flex-direction: column;
            }


            .btn {

                width: 100%;

                text-align: center;
            }
        }

    </style>

</head>


<body>


<div class="container">


    <!-- HEADER -->

    <div class="header">

        <h1>

            <c:choose>

                <c:when test="${edit}">
                    ☕ Edit Drink
                </c:when>

                <c:otherwise>
                    ☕ Create Drink
                </c:otherwise>

            </c:choose>

        </h1>


        <p>

            <c:choose>

                <c:when test="${edit}">
                    Chỉnh sửa thông tin món uống
                </c:when>

                <c:otherwise>
                    Thêm món uống mới vào thực đơn
                </c:otherwise>

            </c:choose>

        </p>

    </div>



    <!-- FORM -->

    <div class="form-container">

        <form
            action="${pageContext.request.contextPath}/DrinkServlet"
            method="post">


            <!-- ACTION -->

            <c:choose>

                <c:when test="${edit}">

                    <input
                        type="hidden"
                        name="action"
                        value="update">

                </c:when>


                <c:otherwise>

                    <input
                        type="hidden"
                        name="action"
                        value="create">

                </c:otherwise>

            </c:choose>



            <!-- DRINK ID -->

            <c:if test="${edit}">

                <div class="form-group">

                    <label>
                        Drink ID
                    </label>


                    <input
                        type="text"
                        name="idDrink"
                        value="${drink.idDrink}"
                        class="readonly"
                        readonly>

                </div>

            </c:if>



            <!-- DRINK NAME -->

            <div class="form-group">

                <label for="drinkName">
                    Drink Name
                </label>


                <input
                    type="text"
                    id="drinkName"
                    name="drinkName"
                    value="${drink.drinkName}"
                    placeholder="Nhập tên món..."
                    required>

            </div>



            <!-- PRICE -->

            <div class="form-group">

                <label for="drinkPrice">
                    Price (VNĐ)
                </label>


                <input
                    type="number"
                    id="drinkPrice"
                    name="drinkPrice"
                    value="${drink.drinkPrice}"
                    min="0"
                    step="1000"
                    placeholder="Nhập giá món..."
                    required>

            </div>



            <!-- CATEGORY -->

            <div class="form-group">

                <label for="category">
                    Category
                </label>


                <select
                    id="category"
                    name="category"
                    required>
                    <option value="">
                        -- Select Category --
                    </option>
                    <option
                        value="Cà phê"
                        <c:if test="${drink.category == 'Cà phê'}">
                            selected
                        </c:if>>
                        Cà phê
                    </option>
                    <option
                        value="Trà"
                        <c:if test="${drink.category == 'Trà'}">
                            selected
                        </c:if>>
                        Trà
                    </option>
                    <option
                        value="Freeze"
                        <c:if test="${drink.category == 'Freeze'}">
                            selected
                        </c:if>>
                        Freeze
                    </option>
                    <option
                        value="Bánh"
                        <c:if test="${drink.category == 'Bánh'}">
                            selected
                        </c:if>>
                        Bánh
                    </option>
                </select>
            </div>
            <!-- IMAGE -->
            <div class="form-group">
                <label for="drinkIMG">
                    Image
                </label>
                <input
                    type="text"
                    id="drinkIMG"
                    name="drinkIMG"
                    value="${drink.drinkIMG}"
                    placeholder="Ví dụ: 1">
            </div>
            <!-- STATUS-->
            <div class="form-group">
                <label>
                    Status
                </label>
                <div class="checkbox-wrapper">
                    <input
                        type="checkbox"
                        id="drinkActive"
                        name="drinkActive"
                        value="true"
                        <c:if test="${drink.drinkActive || not edit}">
                            checked
                        </c:if>>
                    <label for="drinkActive">
                        Available
                    </label>
                </div>
            </div>
            <!-- BUTTONS -->
            <div class="buttons">
                <c:choose>
                    <c:when test="${edit}">
                        <button
                            type="submit"
                            class="btn btn-save">
                            ✓ Update Drink
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button
                            type="submit"
                            class="btn btn-save">
                            ✓ Create Drink
                        </button>
                    </c:otherwise>
                </c:choose>
                <a
                    href="${pageContext.request.contextPath}/DrinkServlet"
                    class="btn btn-cancel">
                    Cancel
                </a>
            </div>
        </form>
    </div>
</div>
</body>

</html>