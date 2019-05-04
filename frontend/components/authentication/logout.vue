<template>
    <button @click="logout()" class="border rounded border-black p-2 ml-2 hover:bg-black hover:text-white cursor-pointer">
        Uitloggen
    </button>
</template>

<script>
    export default {
        methods: {
            logout() {
                window.localStorage.clear();
                window.location.href = '/1/login';
            }
        }
    }
</script>