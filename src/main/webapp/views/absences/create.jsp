<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Absence</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="min-h-screen bg-slate-50 flex items-center justify-center p-6">

<div class="w-full max-w-2xl">

    <div class="mb-8">
        <a href="${pageContext.request.contextPath}/dashboard.jsp"
           class="inline-flex items-center gap-2 text-sm text-slate-500 hover:text-blue-600 transition mb-5">
            ← Back to Dashboard
        </a>

        <h1 class="text-3xl font-bold text-slate-900">
            Create Absence
        </h1>

        <p class="mt-2 text-slate-500">
            Submit your planned absence period and reason.
        </p>
    </div>

    <div class="bg-white rounded-2xl border border-slate-200 shadow-sm p-8">

        <c:if test="${not empty requestScope.error}">
            <div class="mb-6 rounded-lg border border-red-200 bg-red-50 px-4 py-3">
                <div class="flex items-start gap-3">
                    <div class="text-red-500">!</div>
                    <div>
                        <p class="font-medium text-red-800">Unable to create absence</p>
                        <p class="mt-1 text-sm text-red-700">
                            <c:out value="${requestScope.error}"/>
                        </p>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${not empty param.success}">
            <div class="mb-6 rounded-lg border border-green-200 bg-green-50 px-4 py-3">
                <div class="flex items-start gap-3">
                    <div class="text-green-600">✓</div>
                    <div>
                        <p class="font-medium text-green-800">Absence created successfully.</p>
                    </div>
                </div>
            </div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/absences/create" class="space-y-6">

            <div>
                <label for="absenceType" class="block text-sm font-medium text-slate-700 mb-2">
                    Absence type
                </label>

                <select id="absenceType" name="absenceType" required
                        class="w-full rounded-lg border border-slate-300 bg-white px-4 py-3 text-sm text-slate-900 focus:border-blue-500 focus:outline-none focus:ring-2 focus:ring-blue-500/20">
                    <option value="">Select absence type</option>
                    <option value="LEAVE">Leave</option>
                    <option value="ABSENCE">Absence</option>
                </select>
            </div>

            <div class="grid grid-cols-1 md:grid-cols-2 gap-5">
                <div>
                    <label for="startDateTime" class="block text-sm font-medium text-slate-700 mb-2">
                        Start date & time
                    </label>
                    <input type="datetime-local" id="startDateTime" name="startDateTime" required
                           class="w-full rounded-lg border border-slate-300 px-4 py-3 text-sm text-slate-900 focus:border-blue-500 focus:outline-none focus:ring-2 focus:ring-blue-500/20">
                </div>

                <div>
                    <label for="startEndTime" class="block text-sm font-medium text-slate-700 mb-2">
                        End date & time
                    </label>
                    <input type="datetime-local" id="startEndTime" name="startEndTime" required
                           class="w-full rounded-lg border border-slate-300 px-4 py-3 text-sm text-slate-900 focus:border-blue-500 focus:outline-none focus:ring-2 focus:ring-blue-500/20">
                </div>
            </div>

            <div>
                <label for="reason" class="block text-sm font-medium text-slate-700 mb-2">
                    Reason
                </label>
                <textarea id="reason" name="reason" rows="4" required
                          class="w-full rounded-lg border border-slate-300 px-4 py-3 text-sm text-slate-900 focus:border-blue-500 focus:outline-none focus:ring-2 focus:ring-blue-500/20"
                          placeholder="Explain the reason for the absence..."></textarea>
            </div>

            <div class="flex flex-col-reverse sm:flex-row gap-3 pt-4 border-t border-slate-100">
                <a href="${pageContext.request.contextPath}/dashboard.jsp"
                   class="flex-1 inline-flex items-center justify-center rounded-lg border border-slate-300 px-5 py-3 text-sm font-medium text-slate-700 hover:bg-slate-50 transition">
                    Cancel
                </a>

                <button type="submit"
                        class="flex-1 rounded-lg bg-blue-600 px-5 py-3 text-sm font-semibold text-white hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500/30 transition">
                    Create Absence
                </button>
            </div>
        </form>
    </div>
</div>

</body>
</html>
