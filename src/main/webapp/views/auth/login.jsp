<%--
  Created by IntelliJ IDEA.
  User: aziz
  Date: 10/1/26
  Time: 3:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title><c:out value="Login | Clinic Management" /></title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="min-h-screen bg-slate-100 text-slate-800">
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="min-h-screen lg:grid lg:grid-cols-[1.05fr_0.95fr]">
  <section class="relative flex items-end overflow-hidden bg-gradient-to-br from-slate-950 via-slate-900 to-sky-900 px-8 py-10 text-white lg:px-12 lg:py-14">
    <div class="absolute inset-0 bg-[radial-gradient(circle_at_top_left,_rgba(56,189,248,0.25),_transparent_35%),radial-gradient(circle_at_bottom_right,_rgba(148,163,184,0.2),_transparent_30%)]"></div>
    <div class="relative max-w-xl space-y-6">
      <div class="inline-flex rounded-full border border-white/15 bg-white/10 px-4 py-1.5 text-xs uppercase tracking-[0.28em] text-sky-100">
        <c:out value="Clinic Management" />
      </div>
      <div class="space-y-3">
        <h1 class="text-4xl font-semibold leading-tight md:text-5xl">
          <c:out value="Sign in to the clinic dashboard." />
        </h1>
        <p class="max-w-lg text-sm leading-6 text-slate-300 md:text-base">
          <c:out value="Access departments, specialties, and user workflows from a single, focused workspace." />
        </p>
      </div>
    </div>
  </section>

  <section class="flex items-center justify-center px-6 py-10 sm:px-8 lg:px-12">
    <div class="w-full max-w-md rounded-3xl border border-slate-200 bg-white p-8 shadow-2xl shadow-slate-200/70">
      <div class="space-y-2">
        <p class="text-xs uppercase tracking-[0.24em] text-slate-400">
          <c:out value="Welcome back" />
        </p>
        <h2 class="text-2xl font-semibold text-slate-900">
          <c:out value="Login" />
        </h2>
        <p class="text-sm text-slate-500">
          <c:out value="Use your account credentials to continue." />
        </p>
      </div>

      <c:if test="${not empty error}">
        <div class="mt-6 rounded-2xl border border-rose-200 bg-rose-50 px-4 py-3 text-sm text-rose-800">
          <c:out value="${error}" />
        </div>
      </c:if>

      <form class="mt-6 space-y-5" action="<c:out value='${ctx}/login'/>" method="post">
        <div>
          <label class="mb-1.5 block text-sm font-medium text-slate-700" for="email">
            <c:out value="Email" />
          </label>
          <input id="email" type="email" name="email" required class="w-full rounded-2xl border border-slate-300 bg-white px-4 py-3.5 outline-none transition placeholder:text-slate-400 focus:border-sky-500 focus:ring-4 focus:ring-sky-100" placeholder="name@clinic.com">
        </div>

        <div>
          <label class="mb-1.5 block text-sm font-medium text-slate-700" for="password">
            <c:out value="Password" />
          </label>
          <input id="password" type="password" name="password" required class="w-full rounded-2xl border border-slate-300 bg-white px-4 py-3.5 outline-none transition placeholder:text-slate-400 focus:border-sky-500 focus:ring-4 focus:ring-sky-100" placeholder="••••••••">
        </div>

        <button type="submit" class="w-full rounded-2xl bg-slate-900 px-4 py-3.5 text-sm font-semibold text-white transition hover:bg-slate-800">
          <c:out value="Login" />
        </button>
      </form>
    </div>
  </section>
</div>
</body>
</html>
