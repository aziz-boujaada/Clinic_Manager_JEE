<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
        <c:choose>
            <c:when test="${not empty department and not empty department.id}">Edit</c:when>
            <c:otherwise>Add</c:otherwise>
        </c:choose>
        Department | Clinic Management
    </title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="min-h-screen bg-gray-100 text-gray-800">
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="editing" value="${not empty department and not empty department.id}" />
<main class="mx-auto max-w-2xl p-6 md:p-10">
    <a href="<c:out value='${ctx}'/>/departments" class="text-sm text-blue-700 hover:underline">&larr; Departments</a>
    <section class="mt-5 rounded-2xl bg-white p-6 shadow-sm md:p-8">
        <h1 class="text-2xl font-bold">
            <c:choose>
                <c:when test="${editing}">Edit department</c:when>
                <c:otherwise>Add department</c:otherwise>
            </c:choose>
        </h1>
        <p class="mt-1 text-sm text-gray-500">Enter the department details below.</p>

        <c:if test="${not empty error}">
            <div class="mt-5 rounded-lg border border-red-200 bg-red-50 px-4 py-3 text-sm text-red-800">
                <c:out value="${error}" />
            </div>
        </c:if>

        <form class="mt-6 space-y-5" method="post" action="<c:out value='${ctx}'/>/departments">
            <c:choose>
                <c:when test="${editing}">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="department_id" value="<c:out value='${department.id}' />">
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="action" value="create">
                </c:otherwise>
            </c:choose>

            <div>
                <label for="name" class="mb-1 block text-sm font-medium">Name</label>
                <input id="name" name="name" type="text" maxlength="255" required value="<c:out value='${department.name}' />" class="w-full rounded-lg border border-gray-300 px-3 py-2.5 outline-none focus:border-blue-500 focus:ring-2 focus:ring-blue-100">
            </div>

            <div>
                <label for="description" class="mb-1 block text-sm font-medium">Description <span class="font-normal text-gray-500">(optional)</span></label>
                <textarea id="description" name="description" rows="5" class="w-full rounded-lg border border-gray-300 px-3 py-2.5 outline-none focus:border-blue-500 focus:ring-2 focus:ring-blue-100"><c:out value="${department.description}" /></textarea>
            </div>

            <div class="flex justify-end gap-3 border-t pt-5">
                <a href="<c:out value='${ctx}'/>/departments" class="rounded-lg border px-4 py-2.5 text-sm hover:bg-gray-50">Cancel</a>
                <button class="rounded-lg bg-blue-600 px-5 py-2.5 text-sm font-semibold text-white hover:bg-blue-700" type="submit">
                    <c:choose>
                        <c:when test="${editing}">Save changes</c:when>
                        <c:otherwise>Create department</c:otherwise>
                    </c:choose>
                </button>
            </div>
        </form>
    </section>
</main>
</body>
</html>
