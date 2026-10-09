<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>
        <c:choose>
            <c:when test="${not empty specialty}">
                Edit Specialty
            </c:when>
            <c:otherwise>
                Add Specialty
            </c:otherwise>
        </c:choose>
        | Clinic Management
    </title>

    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="min-h-screen bg-gray-100 text-gray-800">

<main class="mx-auto max-w-2xl p-6 md:p-10">

    <!-- Back -->
    <a
    href="<c:out value='${pageContext.request.contextPath}/specialties'/>"
        class="text-sm font-medium text-blue-700 hover:underline"
    >
        &larr; Back to specialties
    </a>

    <!-- Card -->
    <section class="mt-5 rounded-2xl bg-white p-6 shadow-sm md:p-8">

        <!-- Header -->
        <div>

            <h1 class="text-2xl font-bold">

                <c:choose>

                    <c:when test="${not empty specialty}">
                        Edit specialty
                    </c:when>

                    <c:otherwise>
                        Add specialty
                    </c:otherwise>

                </c:choose>

            </h1>

            <p class="mt-1 text-sm text-gray-500">

                <c:choose>

                    <c:when test="${not empty specialty}">
                        Update the specialty information.
                    </c:when>

                    <c:otherwise>
                        Create a new specialty and assign it to a department.
                    </c:otherwise>

                </c:choose>

            </p>

        </div>


        <!-- Error -->
        <c:if test="${not empty error}">

            <div
                class="mt-5 rounded-lg border border-red-200
                       bg-red-50 px-4 py-3 text-sm text-red-800"
            >
                <c:out value="${error}"/>
            </div>

        </c:if>


        <!-- No departments -->
        <c:choose>

            <c:when test="${empty departments}">

                <div
                    class="mt-6 rounded-lg border border-amber-200
                           bg-amber-50 p-4 text-sm text-amber-900"
                >

                    <p>
                        You need to create a department before
                        adding a specialty.
                    </p>

                    <a
                        href="<c:out value='${pageContext.request.contextPath}/departments/new'/>"
                        class="mt-2 inline-block font-semibold underline"
                    >
                        Add department
                    </a>

                </div>

            </c:when>


            <c:otherwise>

                <!-- Form -->
                <form
                    class="mt-6 space-y-5"
                    method="post"
                    action="<c:out value='${pageContext.request.contextPath}/specialties'/>"
                >

                    <!-- Action -->
                    <c:choose>
                        <c:when test="${not empty specialty}">
                            <input
                                type="hidden"
                                name="action"
                                value="update"
                            >
                        </c:when>
                        <c:otherwise>
                            <input
                                type="hidden"
                                name="action"
                                value="create"
                            >
                        </c:otherwise>
                    </c:choose>


                    <!-- Specialty ID -->
                    <c:if test="${not empty specialty}">

                        <input
                            type="hidden"
                            name="specialty_id"
                            value="<c:out value='${specialty.id}' />"
                        >

                    </c:if>


                    <!-- Name -->
                    <div>

                        <label
                            for="name"
                            class="mb-1 block text-sm font-medium"
                        >
                            Name
                        </label>

                        <input
                            id="name"
                            name="name"
                            type="text"
                            maxlength="255"
                            required
                            value="<c:out value='${specialty.name}' />"
                            class="w-full rounded-lg border border-gray-300
                                   px-3 py-2.5 outline-none
                                   focus:border-blue-500
                                   focus:ring-2 focus:ring-blue-100"
                        >

                    </div>


                    <!-- Department -->
                    <div>

                        <label
                            for="department_id"
                            class="mb-1 block text-sm font-medium"
                        >
                            Department
                        </label>

                        <select
                            id="department_id"
                            name="department_id"
                            required
                            class="w-full rounded-lg border border-gray-300
                                   bg-white px-3 py-2.5 outline-none
                                   focus:border-blue-500
                                   focus:ring-2 focus:ring-blue-100"
                        >

                            <option value="">
                                Select a department
                            </option>

                            <c:forEach
                                var="department"
                                items="${departments}"
                            >

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


                    <!-- Description -->
                    <div>

                        <label
                            for="description"
                            class="mb-1 block text-sm font-medium"
                        >
                            Description
                            <span class="font-normal text-gray-500">
                                (optional)
                            </span>
                        </label>

                        <textarea
                            id="description"
                            name="description"
                            rows="5"
                            maxlength="1000"
                            class="w-full rounded-lg border border-gray-300
                                   px-3 py-2.5 outline-none
                                   focus:border-blue-500
                                   focus:ring-2 focus:ring-blue-100"
                        ><c:out value="${specialty.description}"/></textarea>

                    </div>


                    <!-- Actions -->
                    <div
                        class="flex justify-end gap-3 border-t pt-5"
                    >

                        <a
                            href="<c:out value='${pageContext.request.contextPath}/specialties'/>"
                            class="rounded-lg border border-gray-300
                                   px-4 py-2.5 text-sm
                                   hover:bg-gray-50"
                        >
                            Cancel
                        </a>

                        <button
                            type="submit"
                            class="rounded-lg bg-blue-600
                                   px-5 py-2.5 text-sm font-semibold
                                   text-white hover:bg-blue-700"
                        >

                            <c:choose>

                                <c:when test="${not empty specialty}">
                                    Save changes
                                </c:when>

                                <c:otherwise>
                                    Create specialty
                                </c:otherwise>

                            </c:choose>

                        </button>

                    </div>

                </form>

            </c:otherwise>

        </c:choose>

    </section>

</main>

</body>
</html>