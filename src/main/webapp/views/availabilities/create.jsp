<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Create Availability</title>

    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="min-h-screen bg-slate-50 flex items-center justify-center p-6">

<div class="w-full max-w-2xl">

    <!-- Header -->

    <div class="mb-8">
        <a
                href="${pageContext.request.contextPath}/dashboard.jsp"
                class="inline-flex items-center gap-2 text-sm text-slate-500 hover:text-blue-600 transition mb-5"
        >
            ← Back to Dashboard
        </a>

        <h1 class="text-3xl font-bold text-slate-900">
            Create Availability
        </h1>

        <p class="mt-2 text-slate-500">
            Define your working hours and availability period.
        </p>
    </div>


    <!-- Card -->

    <div class="bg-white rounded-2xl border border-slate-200 shadow-sm p-8">


        <!-- Error -->

        <c:if test="${not empty requestScope.error}">

            <div class="mb-6 rounded-lg border border-red-200 bg-red-50 px-4 py-3">

                <div class="flex items-start gap-3">

                    <div class="text-red-500">
                        !
                    </div>

                    <div>
                        <p class="font-medium text-red-800">
                            Unable to create availability
                        </p>

                        <p class="mt-1 text-sm text-red-700">
                            <c:out value="${requestScope.error}"/>
                        </p>
                    </div>

                </div>

            </div>

        </c:if>


        <!-- Success -->

        <c:if test="${not empty param.success}">

            <div class="mb-6 rounded-lg border border-green-200 bg-green-50 px-4 py-3">

                <div class="flex items-start gap-3">

                    <div class="text-green-600">
                        ✓
                    </div>

                    <div>
                        <p class="font-medium text-green-800">
                            Availability created successfully.
                        </p>
                    </div>

                </div>

            </div>

        </c:if>


        <!-- Form -->

        <form
                method="post"
                action="${pageContext.request.contextPath}/availabilities/create"
                class="space-y-6"
        >


            <!-- Day -->

            <div>

                <label
                        for="day"
                        class="block text-sm font-medium text-slate-700 mb-2"
                >
                    Day
                </label>

                <select
                        id="day"
                        name="day"
                        required
                        class="w-full rounded-lg border border-slate-300 bg-white px-4 py-3 text-sm text-slate-900 focus:border-blue-500 focus:outline-none focus:ring-2 focus:ring-blue-500/20"
                >

                    <option value="">
                        Select a day
                    </option>

                    <option value="MONDAY">
                        Monday
                    </option>

                    <option value="TUESDAY">
                        Tuesday
                    </option>

                    <option value="WEDNESDAY">
                        Wednesday
                    </option>

                    <option value="THURSDAY">
                        Thursday
                    </option>

                    <option value="FRIDAY">
                        Friday
                    </option>

                    <option value="SATURDAY">
                        Saturday
                    </option>

                </select>

                <p class="mt-2 text-xs text-slate-500">
                    Sunday is closed.
                </p>

            </div>


            <!-- Time -->

            <div class="grid grid-cols-1 md:grid-cols-2 gap-5">

                <!-- Start -->

                <div>

                    <label
                            for="startTime"
                            class="block text-sm font-medium text-slate-700 mb-2"
                    >
                        Start time
                    </label>

                    <input
                            type="time"
                            id="startTime"
                            name="startTime"
                            required
                            class="w-full rounded-lg border border-slate-300 px-4 py-3 text-sm text-slate-900 focus:border-blue-500 focus:outline-none focus:ring-2 focus:ring-blue-500/20"
                    >

                </div>


                <!-- End -->

                <div>

                    <label
                            for="endTime"
                            class="block text-sm font-medium text-slate-700 mb-2"
                    >
                        End time
                    </label>

                    <input
                            type="time"
                            id="endTime"
                            name="endTime"
                            required
                            class="w-full rounded-lg border border-slate-300 px-4 py-3 text-sm text-slate-900 focus:border-blue-500 focus:outline-none focus:ring-2 focus:ring-blue-500/20"
                    >

                </div>

            </div>


            <!-- Validity -->

            <div>

                <div class="mb-4">

                    <h2 class="text-sm font-semibold text-slate-800">
                        Validity period
                    </h2>

                    <p class="mt-1 text-xs text-slate-500">
                        Define the period during which this schedule is active.
                    </p>

                </div>


                <div class="grid grid-cols-1 md:grid-cols-2 gap-5">

                    <!-- From -->

                    <div>

                        <label
                                for="validateFrom"
                                class="block text-sm font-medium text-slate-700 mb-2"
                        >
                            Valid from
                        </label>

                        <input
                                type="date"
                                id="validateFrom"
                                name="validateFrom"
                                required
                                class="w-full rounded-lg border border-slate-300 px-4 py-3 text-sm text-slate-900 focus:border-blue-500 focus:outline-none focus:ring-2 focus:ring-blue-500/20"
                        >

                    </div>


                    <!-- To -->

                    <div>

                        <label
                                for="validateTo"
                                class="block text-sm font-medium text-slate-700 mb-2"
                        >
                            Valid to
                        </label>

                        <input
                                type="date"
                                id="validateTo"
                                name="validateTo"
                                required
                                class="w-full rounded-lg border border-slate-300 px-4 py-3 text-sm text-slate-900 focus:border-blue-500 focus:outline-none focus:ring-2 focus:ring-blue-500/20"
                        >

                    </div>

                </div>

            </div>


            <!-- Actions -->

            <div class="flex flex-col-reverse sm:flex-row gap-3 pt-4 border-t border-slate-100">

                <a
                        href="${pageContext.request.contextPath}/dashboard.jsp"
                        class="flex-1 inline-flex items-center justify-center rounded-lg border border-slate-300 px-5 py-3 text-sm font-medium text-slate-700 hover:bg-slate-50 transition"
                >
                    Cancel
                </a>

                <button
                        type="submit"
                        class="flex-1 rounded-lg bg-blue-600 px-5 py-3 text-sm font-semibold text-white hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500/30 transition"
                >
                    Create Availability
                </button>

            </div>

        </form>

    </div>

</div>

</body>

</html>
