<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Users | Clinic Management</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-slate-100 text-slate-800">
<c:set var="pageTitle" value="Users" />

<div class="min-h-screen">
    <jsp:include page="/views/Components/side-bar.jsp" />

    <main class="ml-64 min-h-screen">
        <jsp:include page="/views/Components/header.jsp" />

        <div class="p-8">
            <div class="mb-8 flex flex-wrap items-center justify-between gap-4">
                <div>
                    <p class="text-sm uppercase tracking-[0.24em] text-slate-400">
                        <c:out value="Administration" />
                    </p>
                    <h1 class="mt-2 text-3xl font-semibold text-slate-900">
                        <c:out value="Users" />
                    </h1>
                    <p class="mt-2 text-slate-500">
                        <c:out value="Review clinic accounts and roles." />
                    </p>
                </div>

                <a href="<c:out value='${pageContext.request.contextPath}/users/create'/>" class="rounded-full bg-slate-900 px-5 py-3 text-sm font-semibold text-white transition hover:bg-slate-800">
                    <c:out value="Create user" />
                </a>
            </div>

            <div class="overflow-hidden rounded-3xl border border-slate-200 bg-white shadow-sm">
                <div class="overflow-x-auto">
                    <table class="w-full text-left text-sm">
                        <thead class="border-b border-slate-200 bg-slate-50 text-slate-500">
                        <tr>
                            <th class="px-6 py-4 font-medium">Name</th>
                            <th class="px-6 py-4 font-medium">Email</th>
                            <th class="px-6 py-4 font-medium">Phone</th>
                            <th class="px-6 py-4 font-medium">Role</th>
                            <th class="px-6 py-4 font-medium">Status</th>
                        </tr>
                        </thead>
                        <tbody class="divide-y divide-slate-200">
                        <c:choose>
                            <c:when test="${empty users}">
                                <tr>
                                    <td colspan="5" class="px-6 py-12 text-center text-slate-500">
                                        <c:out value="No users found yet." />
                                    </td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="user" items="${users}">
                                    <tr class="hover:bg-slate-50">
                                        <td class="px-6 py-4 font-medium text-slate-900">
                                            <c:out value="${user.firstName}" />
                                            <c:out value=" " />
                                            <c:out value="${user.lastName}" />
                                        </td>
                                        <td class="px-6 py-4 text-slate-600">
                                            <c:out value="${user.email}" />
                                        </td>
                                        <td class="px-6 py-4 text-slate-600">
                                            <c:choose>
                                                <c:when test="${empty user.phone}">
                                                    <c:out value="—" />
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value="${user.phone}" />
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td class="px-6 py-4">
                                            <span class="inline-flex rounded-full bg-sky-50 px-3 py-1 text-xs font-semibold text-sky-700">
                                                <c:out value="${user.role}" />
                                            </span>
                                        </td>
                                        <td class="px-6 py-4">
                                            <c:choose>
                                                <c:when test="${user.active}">
                                                    <span class="inline-flex rounded-full bg-emerald-50 px-3 py-1 text-xs font-semibold text-emerald-700">
                                                        <c:out value="Active" />
                                                    </span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="inline-flex rounded-full bg-slate-100 px-3 py-1 text-xs font-semibold text-slate-500">
                                                        <c:out value="Inactive" />
                                                    </span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </main>
</div>
</body>
</html>