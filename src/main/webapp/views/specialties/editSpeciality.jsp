<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Specialty</title>
</head>

<body>

<h1>Edit Specialty</h1>

<form action="<c:out value='${pageContext.request.contextPath}/specialties'/>" method="post">

    <input type="hidden" name="action" value="update">

    <input
            type="hidden"
            name="specialty_id"
            value="<c:out value='${specialty.id}' />"
    >

    <div>
        <label for="name">Name</label>
        <input
                type="text"
                id="name"
                name="name"
                value="<c:out value='${specialty.name}' />"
                required
        >
    </div>

    <br>

    <div>
        <label for="department_id">Department</label>

        <select
                id="department_id"
                name="department_id"
                required
        >
            <option value="">Select a department</option>

            <c:forEach var="department" items="${departments}">
                <option
                        value="<c:out value='${department.id}' />"
                        <c:if test="${specialty.department.id == department.id}">
                            selected
                        </c:if>
                >
                    <c:out value="${department.name}"/>
                </option>
            </c:forEach>

        </select>
    </div>

    <br>

    <div>
        <label for="description">Description</label>

        <textarea
                id="description"
                name="description"
                rows="5"
                cols="40"
        ><c:out value="${specialty.description}"/></textarea>
    </div>

    <br>

    <button type="submit">Update Specialty</button>

    <a href="<c:out value='${pageContext.request.contextPath}/specialties'/>">
        Cancel
    </a>

</form>

</body>
</html>