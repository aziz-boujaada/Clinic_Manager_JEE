<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<header class="flex h-16 items-center justify-between border-b border-slate-200 bg-white/90 px-8 backdrop-blur">
    <div>
        <p class="text-xs uppercase tracking-[0.28em] text-slate-400">
            <c:out value="Clinic Management" />
        </p>
        <h2 class="text-xl font-semibold text-slate-900">
            <c:out value="${empty pageTitle ? 'Dashboard' : pageTitle}" />
        </h2>
    </div>

    <div class="flex items-center gap-3">
        <div class="text-right">
            <p class="text-sm font-medium text-slate-900">
                <c:out value="Admin" />
            </p>
            <p class="text-xs text-slate-500">
                <c:out value="Administrator" />
            </p>
        </div>
        <div class="flex h-10 w-10 items-center justify-center rounded-full bg-sky-600 font-semibold text-white shadow-sm">
            <c:out value="A" />
        </div>
    </div>
</header>
