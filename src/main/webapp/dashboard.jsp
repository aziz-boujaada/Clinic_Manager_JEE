<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Clinic Management</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-slate-100 text-slate-800">
<c:set var="pageTitle" value="Dashboard" />

<div class="min-h-screen">
    <jsp:include page="/views/Components/side-bar.jsp" />

    <main class="ml-64 min-h-screen">
        <jsp:include page="/views/Components/header.jsp" />

        <div class="space-y-8 p-8">
            <section class="overflow-hidden rounded-3xl bg-gradient-to-br from-slate-900 via-slate-800 to-sky-900 text-white shadow-xl shadow-slate-200/60">
                <div class="grid gap-6 p-8 lg:grid-cols-[1.4fr_0.8fr] lg:p-10">
                    <div class="space-y-5">
                        <p class="inline-flex items-center rounded-full border border-white/15 bg-white/10 px-3 py-1 text-xs uppercase tracking-[0.28em] text-sky-100">
                            <c:out value="Operations overview" />
                        </p>
                        <div class="space-y-3">
                            <h1 class="max-w-2xl text-3xl font-semibold leading-tight md:text-4xl">
                                <c:out value="Run the clinic from one calm, focused workspace." />
                            </h1>
                            <p class="max-w-2xl text-sm leading-6 text-slate-300 md:text-base">
                                <c:out value="Track departments, specialties, users, and day-to-day workflows without jumping between screens." />
                            </p>
                        </div>
                        <div class="flex flex-wrap gap-3">
                            <a href="<c:out value='${pageContext.request.contextPath}/departments'/>" class="rounded-full bg-white px-5 py-2.5 text-sm font-semibold text-slate-900 transition hover:bg-slate-100">
                                <c:out value="Open departments" />
                            </a>
                            <a href="<c:out value='${pageContext.request.contextPath}/specialties'/>" class="rounded-full border border-white/15 bg-white/5 px-5 py-2.5 text-sm font-semibold text-white transition hover:bg-white/10">
                                <c:out value="Open specialties" />
                            </a>
                        </div>
                    </div>

                    <div class="grid gap-4 sm:grid-cols-2 lg:grid-cols-1">
                        <div class="rounded-2xl border border-white/10 bg-white/10 p-4 backdrop-blur">
                            <p class="text-xs uppercase tracking-[0.24em] text-sky-100">
                                <c:out value="Today" />
                            </p>
                            <p class="mt-2 text-3xl font-semibold">
                                <c:out value="14" />
                            </p>
                            <p class="mt-1 text-sm text-slate-300">
                                <c:out value="appointments scheduled" />
                            </p>
                        </div>

                        <div class="rounded-2xl border border-white/10 bg-white/10 p-4 backdrop-blur">
                            <p class="text-xs uppercase tracking-[0.24em] text-sky-100">
                                <c:out value="Active staff" />
                            </p>
                            <p class="mt-2 text-3xl font-semibold">
                                <c:out value="28" />
                            </p>
                            <p class="mt-1 text-sm text-slate-300">
                                <c:out value="users currently online" />
                            </p>
                        </div>
                    </div>
                </div>
            </section>

            <section class="grid gap-4 md:grid-cols-2 xl:grid-cols-4">
                <div class="rounded-2xl border border-slate-200 bg-white p-6 shadow-sm">
                    <p class="text-sm text-slate-500"><c:out value="Total Users" /></p>
                    <p class="mt-3 text-3xl font-semibold text-slate-900"><c:out value="0" /></p>
                </div>
                <div class="rounded-2xl border border-slate-200 bg-white p-6 shadow-sm">
                    <p class="text-sm text-slate-500"><c:out value="Doctors" /></p>
                    <p class="mt-3 text-3xl font-semibold text-slate-900"><c:out value="0" /></p>
                </div>
                <div class="rounded-2xl border border-slate-200 bg-white p-6 shadow-sm">
                    <p class="text-sm text-slate-500"><c:out value="Patients" /></p>
                    <p class="mt-3 text-3xl font-semibold text-slate-900"><c:out value="0" /></p>
                </div>
                <div class="rounded-2xl border border-slate-200 bg-white p-6 shadow-sm">
                    <p class="text-sm text-slate-500"><c:out value="Appointments" /></p>
                    <p class="mt-3 text-3xl font-semibold text-slate-900"><c:out value="0" /></p>
                </div>
            </section>

            <section class="grid gap-6 lg:grid-cols-[1.2fr_0.8fr]">
                <div class="rounded-3xl border border-slate-200 bg-white p-6 shadow-sm">
                    <div>
                        <h3 class="text-lg font-semibold text-slate-900"><c:out value="Quick access" /></h3>
                        <p class="mt-1 text-sm text-slate-500"><c:out value="Jump straight to the core management areas." /></p>
                    </div>

                    <div class="mt-6 grid gap-4 sm:grid-cols-2">
                        <a href="<c:out value='${pageContext.request.contextPath}/departments'/>" class="group rounded-2xl border border-slate-200 bg-slate-50 p-5 transition hover:-translate-y-0.5 hover:border-sky-200 hover:bg-sky-50">
                            <p class="text-xs uppercase tracking-[0.24em] text-slate-400"><c:out value="Administration" /></p>
                            <h4 class="mt-2 text-lg font-semibold text-slate-900 group-hover:text-sky-700"><c:out value="Departments" /></h4>
                            <p class="mt-2 text-sm text-slate-600"><c:out value="Create and organize clinic departments." /></p>
                        </a>

                        <a href="<c:out value='${pageContext.request.contextPath}/specialties'/>" class="group rounded-2xl border border-slate-200 bg-slate-50 p-5 transition hover:-translate-y-0.5 hover:border-sky-200 hover:bg-sky-50">
                            <p class="text-xs uppercase tracking-[0.24em] text-slate-400"><c:out value="Clinical setup" /></p>
                            <h4 class="mt-2 text-lg font-semibold text-slate-900 group-hover:text-sky-700"><c:out value="Specialties" /></h4>
                            <p class="mt-2 text-sm text-slate-600"><c:out value="Assign medical specialties to departments." /></p>
                        </a>
                    </div>
                </div>

                <div class="rounded-3xl border border-slate-200 bg-white p-6 shadow-sm">
                    <h3 class="text-lg font-semibold text-slate-900"><c:out value="System notes" /></h3>
                    <div class="mt-5 space-y-4 text-sm text-slate-600">
                        <div class="rounded-2xl bg-slate-50 p-4">
                            <p class="font-medium text-slate-900"><c:out value="Shared shell" /></p>
                            <p class="mt-1"><c:out value="Sidebar navigation and page cards follow one visual system." /></p>
                        </div>
                        <div class="rounded-2xl bg-slate-50 p-4">
                            <p class="font-medium text-slate-900"><c:out value="Escaped output" /></p>
                            <p class="mt-1"><c:out value="Dynamic fields render with JSTL c:out for safer JSP output." /></p>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </main>
</div>
</body>
</html>
