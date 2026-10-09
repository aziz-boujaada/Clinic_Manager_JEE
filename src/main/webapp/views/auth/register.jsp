<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta
            name="viewport"
            content="width=device-width, initial-scale=1.0"
    >

    <title>Create User - Clinic Management</title>

    <script src="https://cdn.tailwindcss.com"></script>

</head>


<body class="bg-gray-100 text-gray-800">

<div class="min-h-screen">

    <!-- ========================================================= -->
    <!-- SIDEBAR -->
    <!-- ========================================================= -->

    <jsp:include page="/views/Components/side-bar.jsp"/>


    <!-- ========================================================= -->
    <!-- MAIN -->
    <!-- ========================================================= -->

    <main class="ml-64 min-h-screen">

        <!-- ===================================================== -->
        <!-- HEADER -->
        <!-- ===================================================== -->

        <jsp:include page="/views/Components/header.jsp"/>


        <!-- ===================================================== -->
        <!-- CONTENT -->
        <!-- ===================================================== -->

        <div class="p-8">

            <!-- PAGE TITLE -->

            <div class="mb-8">

                <h2 class="text-2xl font-bold">
                    Create User
                </h2>

                <p class="mt-1 text-gray-500">
                    Create a new clinic user account.
                </p>

            </div>


            <!-- ================================================= -->
            <!-- REGISTER FORM -->
            <!-- ================================================= -->

            <div class="max-w-5xl rounded-xl bg-white shadow-sm">

                <form
                    action="<c:out value='${pageContext.request.contextPath}/users/create'/>"
                        method="POST"
                        class="space-y-8 p-8"
                >

                    <!-- ========================================= -->
                    <!-- COMMON INFORMATION -->
                    <!-- ========================================= -->

                    <div>

                        <h3 class="mb-4 text-sm font-semibold uppercase tracking-wide text-gray-500">
                            Account Information
                        </h3>


                        <div class="grid grid-cols-1 gap-4 md:grid-cols-2">

                            <!-- FIRST NAME -->

                            <div>

                                <label
                                        for="firstName"
                                        class="block text-sm font-medium"
                                >
                                    First Name
                                </label>

                                <input
                                        id="firstName"
                                        name="firstName"
                                        type="text"
                                        required
                                        class="mt-1 w-full rounded-lg border border-gray-300 px-3 py-2.5 outline-none focus:border-blue-500 focus:ring-2 focus:ring-blue-100"
                                >

                            </div>


                            <!-- LAST NAME -->

                            <div>

                                <label
                                        for="lastName"
                                        class="block text-sm font-medium"
                                >
                                    Last Name
                                </label>

                                <input
                                        id="lastName"
                                        name="lastName"
                                        type="text"
                                        required
                                        class="mt-1 w-full rounded-lg border border-gray-300 px-3 py-2.5 outline-none focus:border-blue-500 focus:ring-2 focus:ring-blue-100"
                                >

                            </div>


                            <!-- EMAIL -->

                            <div>

                                <label
                                        for="email"
                                        class="block text-sm font-medium"
                                >
                                    Email
                                </label>

                                <input
                                        id="email"
                                        name="email"
                                        type="email"
                                        required
                                        class="mt-1 w-full rounded-lg border border-gray-300 px-3 py-2.5 outline-none focus:border-blue-500 focus:ring-2 focus:ring-blue-100"
                                >

                            </div>


                            <!-- PHONE -->

                            <div>

                                <label
                                        for="phone"
                                        class="block text-sm font-medium"
                                >
                                    Phone
                                </label>

                                <input
                                        id="phone"
                                        name="phone"
                                        type="tel"
                                        class="mt-1 w-full rounded-lg border border-gray-300 px-3 py-2.5 outline-none focus:border-blue-500 focus:ring-2 focus:ring-blue-100"
                                >

                            </div>


                            <!-- PASSWORD -->

                            <div>

                                <label
                                        for="password"
                                        class="block text-sm font-medium"
                                >
                                    Password
                                </label>

                                <input
                                        id="password"
                                        name="password"
                                        type="password"
                                        required
                                        class="mt-1 w-full rounded-lg border border-gray-300 px-3 py-2.5 outline-none focus:border-blue-500 focus:ring-2 focus:ring-blue-100"
                                >

                            </div>


                            <!-- ROLE -->

                            <div>

                                <label
                                        for="role"
                                        class="block text-sm font-medium"
                                >
                                    Role
                                </label>

                                <select
                                        id="role"
                                        name="role"
                                        required
                                        onchange="showRoleFields()"
                                        class="mt-1 w-full rounded-lg border border-gray-300 bg-white px-3 py-2.5 outline-none focus:border-blue-500 focus:ring-2 focus:ring-blue-100"
                                >

                                    <option value="">
                                        Select role
                                    </option>

                                    <option value="PATIENT">
                                        Patient
                                    </option>

                                    <option value="DOCTOR">
                                        Doctor
                                    </option>

                                    <option value="ADMIN">
                                        Admin
                                    </option>

                                </select>

                            </div>

                        </div>

                    </div>


                    <!-- ========================================= -->
                    <!-- PATIENT INFORMATION -->
                    <!-- ========================================= -->

                    <div
                            id="patientFields"
                            class="hidden rounded-xl border border-blue-100 bg-blue-50 p-5"
                    >

                        <h3 class="mb-4 font-semibold text-blue-900">
                            Patient Information
                        </h3>


                        <div class="grid grid-cols-1 gap-4 md:grid-cols-2">

                            <!-- CIN -->

                            <div>

                                <label
                                        for="cin"
                                        class="block text-sm font-medium"
                                >
                                    CIN
                                </label>

                                <input
                                        id="cin"
                                        name="cin"
                                        type="text"
                                        class="mt-1 w-full rounded-lg border border-gray-300 bg-white px-3 py-2.5"
                                >

                            </div>


                            <!-- BIRTH DATE -->

                            <div>

                                <label
                                        for="birthDate"
                                        class="block text-sm font-medium"
                                >
                                    Birth Date
                                </label>

                                <input
                                        id="birthDate"
                                        name="birthDay"
                                        type="date"
                                        class="mt-1 w-full rounded-lg border border-gray-300 bg-white px-3 py-2.5"
                                >

                            </div>


                            <!-- GENDER -->

                            <div>

                                <label
                                        for="gender"
                                        class="block text-sm font-medium"
                                >
                                    Gender
                                </label>

                                <select
                                        id="gender"
                                        name="gender"
                                        class="mt-1 w-full rounded-lg border border-gray-300 bg-white px-3 py-2.5"
                                >

                                    <option value="">
                                        Select gender
                                    </option>

                                    <option value="MALE">
                                        Male
                                    </option>

                                    <option value="FEMALE">
                                        Female
                                    </option>

                                </select>

                            </div>


                            <!-- BLOOD GROUP -->

                            <div>

                                <label
                                        for="bloodGroup"
                                        class="block text-sm font-medium"
                                >
                                    Blood Group
                                </label>

                                <select
                                        id="bloodGroup"
                                        name="bloodGroup"
                                        class="mt-1 w-full rounded-lg border border-gray-300 bg-white px-3 py-2.5"
                                >

                                    <option value="">
                                        Select blood group
                                    </option>

                                    <option value="A_POS">
                                        A+
                                    </option>

                                    <option value="A_NEG">
                                        A-
                                    </option>

                                    <option value="B_POS">
                                        B+
                                    </option>

                                    <option value="B_NEG">
                                        B-
                                    </option>

                                    <option value="AB_POS">
                                        AB+
                                    </option>

                                    <option value="AB_NEG">
                                        AB-
                                    </option>

                                    <option value="O_POS">
                                        O+
                                    </option>

                                    <option value="O_NEG">
                                        O-
                                    </option>

                                </select>

                            </div>


                            <!-- ADDRESS -->

                            <div class="md:col-span-2">

                                <label
                                        for="address"
                                        class="block text-sm font-medium"
                                >
                                    Address
                                </label>

                                <textarea
                                        id="address"
                                        name="address"
                                        rows="3"
                                        class="mt-1 w-full rounded-lg border border-gray-300 bg-white px-3 py-2.5"
                                ></textarea>

                            </div>

                        </div>

                    </div>


                    <!-- ========================================= -->
                    <!-- DOCTOR INFORMATION -->
                    <!-- ========================================= -->

                    <div
                            id="doctorFields"
                            class="hidden rounded-xl border border-green-100 bg-green-50 p-5"
                    >

                        <h3 class="mb-4 font-semibold text-green-900">
                            Doctor Information
                        </h3>


                        <div class="grid grid-cols-1 gap-4 md:grid-cols-2">

                            <!-- SPECIALTY -->

                            <div>

                                <label
                                        for="specialty_id"
                                        class="block text-sm font-medium"
                                >
                                    Specialty
                                </label>

                                <select
                                        id="specialty_id"
                                        name="specialty_id"
                                        class="mt-1 w-full rounded-lg border border-gray-300 bg-white px-3 py-2.5"
                                >

                                    <option value="">
                                        Select specialty
                                    </option>

                                    <c:forEach
                                            var="specialty"
                                            items="${specialties}"
                                    >

                                        <option value="<c:out value='${specialty.id}'/>">
                                            <c:out value="${specialty.name}"/>
                                        </option>

                                    </c:forEach>

                                </select>

                            </div>


                            <!-- TITLE -->

                            <div>

                                <label
                                        for="title"
                                        class="block text-sm font-medium"
                                >
                                    Title
                                </label>

                                <input
                                        id="title"
                                        name="title"
                                        type="text"
                                        placeholder="e.g. Dr., Professor"
                                        class="mt-1 w-full rounded-lg border border-gray-300 bg-white px-3 py-2.5"
                                >

                            </div>

                        </div>

                    </div>


                    <!-- ========================================= -->
                    <!-- SUBMIT -->
                    <!-- ========================================= -->

                    <div class="flex items-center justify-end gap-3 border-t pt-6">

                        <a
                                href="<c:out value='${pageContext.request.contextPath}/users'/>"
                                class="rounded-lg border border-gray-300 px-6 py-2.5 font-medium text-gray-700 hover:bg-gray-50"
                        >
                            Cancel
                        </a>


                        <button
                                type="submit"
                                class="rounded-lg bg-blue-600 px-6 py-2.5 font-medium text-white transition hover:bg-blue-700"
                        >
                            Create Account
                        </button>

                    </div>

                </form>

            </div>

        </div>

    </main>

</div>


<!-- ============================================================= -->
<!-- ROLE FIELDS JAVASCRIPT -->
<!-- ============================================================= -->

<script>

    function showRoleFields() {

        const role =
            document.getElementById("role").value;

        const patientFields =
            document.getElementById("patientFields");

        const doctorFields =
            document.getElementById("doctorFields");


        const cin =
            document.getElementById("cin");

        const birthDay =
            document.getElementById("birthDate");

        const gender =
            document.getElementById("gender");

        const bloodGroup =
            document.getElementById("bloodGroup");

        const address =
            document.getElementById("address");

        const specialty =
            document.getElementById("specialty_id");

        const title =
            document.getElementById("title");


        /* Hide both sections */

        patientFields.classList.add("hidden");

        doctorFields.classList.add("hidden");


        /* Reset required fields */

        cin.required = false;
        birthDay.required = false;
        gender.required = false;
        bloodGroup.required = false;
        address.required = false;

        specialty.required = false;
        title.required = false;


        /* PATIENT */

        if (role === "PATIENT") {

            patientFields.classList.remove("hidden");

            cin.required = true;
            birthDay.required = true;
            gender.required = true;
            bloodGroup.required = true;
            address.required = true;

        }


        /* DOCTOR */

        else if (role === "DOCTOR") {

            doctorFields.classList.remove("hidden");

            specialty.required = true;
            title.required = true;

        }

    }

</script>

</body>

</html>

