<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<aside class="fixed left-0 top-0 z-40 h-screen w-64 bg-slate-900 text-white">
    <div class="flex h-16 items-center border-b border-slate-700 px-6">
        <h1 class="text-xl font-bold">
            <c:out value="Clinic Management" />
        </h1>
    </div>

    <nav class="mt-6 px-3">
        <a href="<c:out value='${pageContext.request.contextPath}/dashboard.jsp'/>" class="mt-2 flex w-full items-center rounded-lg px-4 py-3 text-sm font-medium text-slate-300 hover:bg-slate-800">
            <c:out value="Dashboard" />
        </a>
        <a href="<c:out value='${pageContext.request.contextPath}/users'/>" class="mt-2 flex w-full items-center rounded-lg px-4 py-3 text-sm font-medium text-slate-300 hover:bg-slate-800">
            <c:out value="Users" />
        </a>
        <a href="<c:out value='${pageContext.request.contextPath}/departments'/>" class="mt-2 flex w-full items-center rounded-lg px-4 py-3 text-sm font-medium text-slate-300 hover:bg-slate-800">
            <c:out value="Departments" />
        </a>
        <a href="<c:out value='${pageContext.request.contextPath}/specialties'/>" class="mt-2 flex w-full items-center rounded-lg px-4 py-3 text-sm font-medium text-slate-300 hover:bg-slate-800">
            <c:out value="Specialties" />
        </a>
        <a href="<c:out value='${pageContext.request.contextPath}/appointments'/>" class="mt-2 flex w-full items-center rounded-lg px-4 py-3 text-sm font-medium text-slate-300 hover:bg-slate-800">
            <c:out value="Appointments" />
        </a>
        <a href="<c:out value='${pageContext.request.contextPath}/availabilities'/>" class="mt-2 flex w-full items-center rounded-lg px-4 py-3 text-sm font-medium text-slate-300 hover:bg-slate-800">
            <c:out value="Availabilities" />
        </a>
        <a href="<c:out value='${pageContext.request.contextPath}/absences'/>" class="mt-2 flex w-full items-center rounded-lg px-4 py-3 text-sm font-medium text-slate-300 hover:bg-slate-800">
            <c:out value="Absences" />
        </a>
    </nav>
</aside>

