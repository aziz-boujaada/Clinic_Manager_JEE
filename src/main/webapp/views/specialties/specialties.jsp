
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Specialties | Clinic Management</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="min-h-screen bg-gray-100 text-gray-800">

<main class="mx-auto max-w-6xl p-6 md:p-10">
     <a href="<c:out value='${pageContext.request.contextPath}/dashboard.jsp'/>"
         class="text-sm text-blue-700 hover:underline">
        &larr; Dashboard
    </a>

    <div class="mb-7 mt-5 flex flex-wrap items-center justify-between gap-4">
        <div>
            <h1 class="text-3xl font-bold">Specialties</h1>
            <p class="mt-1 text-gray-500">
                Manage specialties and their departments.
            </p>
        </div>

        <a href="<c:out value='${pageContext.request.contextPath}/specialties/create'/>"
           class="rounded-lg bg-blue-600 px-5 py-3 text-sm font-semibold text-white hover:bg-blue-700">
            Add specialty
        </a>
    </div>

    <%-- Success / error messages --%>
    <c:choose>

        <c:when test="${param.success == 'created'}">
            <div class="mb-5 rounded-lg border border-blue-200 bg-blue-50 px-4 py-3 text-sm text-blue-900">
                <c:out value="Specialty created." />
            </div>
        </c:when>

        <c:when test="${param.success == 'updated'}">
            <div class="mb-5 rounded-lg border border-blue-200 bg-blue-50 px-4 py-3 text-sm text-blue-900">
                <c:out value="Specialty updated." />
            </div>
        </c:when>

        <c:when test="${param.success == 'deleted'}">
            <div class="mb-5 rounded-lg border border-blue-200 bg-blue-50 px-4 py-3 text-sm text-blue-900">
                <c:out value="Specialty deleted." />
            </div>
        </c:when>

        <c:when test="${param.error == 'not-found'}">
            <div class="mb-5 rounded-lg border border-blue-200 bg-blue-50 px-4 py-3 text-sm text-blue-900">
                <c:out value="That specialty could not be found." />
            </div>
        </c:when>

        <c:when test="${param.error == 'related'}">
            <div class="mb-5 rounded-lg border border-blue-200 bg-blue-50 px-4 py-3 text-sm text-blue-900">
                <c:out value="Remove the doctors assigned to this specialty before deleting it." />
            </div>
        </c:when>

    </c:choose>

    <div class="overflow-hidden rounded-xl bg-white shadow-sm">
        <div class="overflow-x-auto">

            <table class="w-full text-left text-sm">

                <thead class="border-b bg-gray-50 text-gray-600">
                <tr>
                    <th class="px-6 py-4">Specialty</th>
                    <th class="px-6 py-4">Department</th>
                    <th class="px-6 py-4">Description</th>
                    <th class="px-6 py-4">Actions</th>
                </tr>
                </thead>

                <tbody class="divide-y">

                <c:choose>

                    <%-- No specialties --%>
                    <c:when test="${empty specialties}">
                        <tr>
                            <td colspan="4"
                                class="px-6 py-12 text-center text-gray-500">
                                No specialties yet. Add one to get started.
                            </td>
                        </tr>
                    </c:when>

                    <%-- Display specialties --%>
                    <c:otherwise>

                        <c:forEach var="specialty" items="${specialties}">

                            <tr class="hover:bg-gray-50">

                                <td class="px-6 py-4 font-medium">
                                    <c:out value="${specialty.name}" />
                                </td>

                                <td class="px-6 py-4">
                                    <c:out value="${specialty.department.name}" />
                                </td>

                                <td class="max-w-lg px-6 py-4 text-gray-600">

                                    <c:choose>
                                        <c:when test="${empty specialty.description}">
                                            &mdash;
                                        </c:when>

                                        <c:otherwise>
                                            <c:out value="${specialty.description}" />
                                        </c:otherwise>
                                    </c:choose>

                                </td>

                                <td class="px-6 py-4">
                                    <div class="flex items-center gap-2">

                                        <a class="rounded-md bg-gray-100 px-3 py-2 text-xs hover:bg-gray-200"
                                           href="<c:out value='${pageContext.request.contextPath}/specialties/editSpeciality?id=${specialty.id}'/>">
                                            Edit
                                        </a>

                                        <form method="post"
                                              action="<c:out value='${pageContext.request.contextPath}/specialties'/>"
                                              onsubmit="return confirm('Delete this specialty?')">

                                            <input type="hidden"
                                                   name="action"
                                                   value="delete">

                                            <input type="hidden"
                                                   name="speciality_id"
                                                   value="<c:out value='${specialty.id}' />">

                                            <button class="rounded-md bg-red-50 px-3 py-2 text-xs text-red-700 hover:bg-red-100"
                                                    type="submit">
                                                Delete
                                            </button>

                                        </form>

                                    </div>
                                </td>

                            </tr>

                        </c:forEach>

                    </c:otherwise>

                </c:choose>

                </tbody>

            </table>

        </div>
    </div>

</main>

</body>
</html>