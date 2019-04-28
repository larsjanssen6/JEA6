<template>
    <button @click="logout" class="bg-blue hover:bg-blue-dark text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
        Logout
    </button>
</template>

<script>
    export default {
        methods: {
            logout() {
                axios.get('/1/api/jxrs/logout').then((response) => {
                    window.location.href = '/1/session-authentication';
                })
            }
        }
    }
</script>