<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book an appointment | Clinic Management</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="min-h-screen bg-slate-50 text-slate-800">
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<main class="mx-auto max-w-5xl px-5 py-10 md:px-8">
    <a href="<c:out value='${ctx}'/>/dashboard.jsp" class="text-sm font-medium text-sky-700 hover:underline">
        &larr; Dashboard
    </a>

    <div class="mb-8 mt-5">
        <p class="text-sm font-semibold uppercase tracking-wider text-sky-700">Appointments</p>
        <h1 class="mt-2 text-3xl font-bold tracking-tight text-slate-900">Book a visit</h1>
        <p class="mt-2 text-slate-600">Choose a doctor and date to see the available appointment times.</p>
    </div>

    <c:if test="${param.success == 'true'}">
        <div role="status" class="mb-6 rounded-xl border border-emerald-200 bg-emerald-50 px-4 py-3 text-emerald-800">
            Your appointment was booked successfully.
        </div>
    </c:if>

    <c:if test="${not empty error}">
        <div role="alert" class="mb-6 rounded-xl border border-rose-200 bg-rose-50 px-4 py-3 text-rose-800">
            <c:out value="${error}" />
        </div>
    </c:if>

    <section class="rounded-2xl border border-slate-200 bg-white p-5 shadow-sm md:p-7">
        <h2 class="text-lg font-semibold text-slate-900">Choose a doctor and date</h2>
        <form method="get" action="<c:out value='${ctx}'/>/appointments/create"
              class="mt-5 grid gap-4 sm:grid-cols-[1fr_1fr_auto] sm:items-end">
            <div>
                <label for="doctorId" class="mb-1.5 block text-sm font-medium text-slate-700">Doctor</label>
                <select id="doctorId" name="doctorId" required
                        class="w-full rounded-lg border border-slate-300 bg-white px-3 py-2.5 outline-none focus:border-sky-500 focus:ring-2 focus:ring-sky-100">
                    <option value="">Select a doctor</option>
                    <c:forEach var="doctor" items="${doctors}">
                        <option value="<c:out value='${doctor.id}'/>"
                                <c:if test="${doctor.id == doctorId}">selected</c:if>>
                            <c:if test="${not empty doctor.title}"><c:out value="${doctor.title}" /> </c:if>
                            <c:out value="${doctor.firstName}" /> <c:out value="${doctor.lastName}" />
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label for="date" class="mb-1.5 block text-sm font-medium text-slate-700">Appointment date</label>
                <input type="date" id="date" name="date" required
                       value="<c:out value='${appointmentDate}'/>"
                       class="w-full rounded-lg border border-slate-300 px-3 py-2.5 outline-none focus:border-sky-500 focus:ring-2 focus:ring-sky-100">
            </div>
            <button type="submit"
                    class="rounded-lg bg-sky-700 px-5 py-2.5 font-semibold text-white hover:bg-sky-800 focus:outline-none focus:ring-2 focus:ring-sky-300">
                Show times
            </button>
        </form>
        <p class="mt-3 text-xs text-slate-500">Choose a doctor, then use the date calendar to see that doctor’s available times. Sundays are closed.</p>
    </section>

    <c:if test="${not empty availableSlots}">
        <section class="mt-6 rounded-2xl border border-slate-200 bg-white p-5 shadow-sm md:p-7">
            <div class="mb-5">
                <h2 class="text-lg font-semibold text-slate-900">Available times</h2>
                <p class="mt-1 text-sm text-slate-500">Each appointment lasts 30 minutes. Select one time to continue.</p>
            </div>

            <form method="post" action="<c:out value='${ctx}'/>/appointments/create" id="bookingForm">
                <input type="hidden" name="doctorId" value="<c:out value='${doctorId}'/>">

                <div class="grid grid-cols-2 gap-3 sm:grid-cols-3 lg:grid-cols-4">
                    <c:forEach var="slot" items="${availableSlots}" varStatus="status">
                        <label class="cursor-pointer">
                            <input class="peer sr-only" type="radio" name="slotStart"
                                   value="<c:out value='${slot.startDateTime}'/>"
                                   <c:if test="${status.first}">checked</c:if> required>
                            <span class="flex min-h-14 items-center justify-center rounded-xl border border-slate-200 px-3 py-3 text-center text-sm font-semibold text-slate-700 transition hover:border-sky-400 peer-checked:border-sky-700 peer-checked:bg-sky-50 peer-checked:text-sky-800 peer-focus-visible:ring-2 peer-focus-visible:ring-sky-300">
                                <c:out value="${slot.startTime}" /> – <c:out value="${slot.endTime}" />
                            </span>
                        </label>
                    </c:forEach>
                </div>

                <div class="mt-6 grid gap-4 sm:grid-cols-2">
                    <div>
                        <label for="type" class="mb-1.5 block text-sm font-medium text-slate-700">Appointment type</label>
                        <select id="type" name="type" required
                                class="w-full rounded-lg border border-slate-300 bg-white px-3 py-2.5 outline-none focus:border-sky-500 focus:ring-2 focus:ring-sky-100">
                            <option value="CONSULTATION">Consultation</option>
                            <option value="FOLLOW_UP">Follow-up</option>
                            <option value="URGENT">Urgent</option>
                        </select>
                    </div>
                    <div>
                        <label for="reason" class="mb-1.5 block text-sm font-medium text-slate-700">Reason <span class="font-normal text-slate-500">(optional)</span></label>
                        <input type="text" id="reason" name="reason" maxlength="500"
                               class="w-full rounded-lg border border-slate-300 px-3 py-2.5 outline-none focus:border-sky-500 focus:ring-2 focus:ring-sky-100">
                    </div>
                </div>

                <button type="submit"
                        class="mt-6 rounded-lg bg-sky-700 px-5 py-3 font-semibold text-white hover:bg-sky-800 focus:outline-none focus:ring-2 focus:ring-sky-300">
                    Book selected time
                </button>
            </form>
        </section>
    </c:if>

    <c:if test="${availableSlots ne null and empty availableSlots}">
        <section class="mt-6 rounded-2xl border border-amber-200 bg-amber-50 p-5 text-amber-900">
            No available times for this doctor on the selected date. Choose another date.
        </section>
    </c:if>
</main>

<script>
    const appointmentDate = document.getElementById('date');
    appointmentDate.addEventListener('change', () => {
        if (!appointmentDate.value) return;
        const [year, month, day] = appointmentDate.value.split('-').map(Number);
        const selectedDay = new Date(year, month - 1, day).getDay();
        if (selectedDay === 0) {
            alert('The clinic is closed on Sundays. Please choose another date.');
            appointmentDate.value = '';
        }
    });
</script>
</body>
</html>
