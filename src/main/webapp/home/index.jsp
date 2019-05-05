<%@page language="java" contentType="text/html; charset=utf-8" %>
<html>
<body>
<head>
    <link href="/1/css/app.css" rel="stylesheet">
</head>

<div id="app">
    <div class="flex items-center h-16 justify-end">
        <div class="flex mr-2">
            <button class="border rounded border-black p-2 hover:bg-black hover:text-white cursor-pointer"
                    @click="$modal.show('makers-modal')">
                Makers
            </button>

            <button class="border rounded border-black ml-2 p-2 hover:bg-black hover:text-white cursor-pointer"
                    @click="$modal.show('profile-modal')">
                Profiel
            </button>

            <logout></logout>
            <profile-modal></profile-modal>
        </div>
    </div>

    <div class="flex items-center justify-center w-full p-8 bg-blue shadow">
        <h2 class="text-white">Games</h2>
    </div>

    <router-view></router-view>
    <makers-modal></makers-modal>
</div>

<script src="/1/js/app.js?version=1"></script>
</body>
</html>
