<template>
    <div class="flex flex-wrap justify-center items-center w-full h-full px-64 mt-8">
        <div class="flex w-full">
            <form @submit.prevent="register()" class="bg-white w-full shadow-md rounded px-8 pt-6 pb-8 mb-4">
                <h2 class="mb-4">Register</h2>

                <div class="mb-4">
                    <label class="block text-grey-darker text-sm font-bold mb-2" for="firstname">
                        Firstname
                    </label>

                    <input class="shadow appearance-none border rounded w-full py-2 px-3 text-grey-darker leading-tight focus:outline-none focus:shadow-outline"
                           id="firstname"
                           type="text"
                           v-model="form.firstName"
                           placeholder="First name"
                           required>
                </div>

                <div class="mb-4">
                    <label class="block text-grey-darker text-sm font-bold mb-2" for="lastName">
                        Lastname
                    </label>

                    <input class="shadow appearance-none border rounded w-full py-2 px-3 text-grey-darker leading-tight focus:outline-none focus:shadow-outline"
                           id="lastName"
                           type="text"
                           v-model="form.lastName"
                           placeholder="Last name"
                           required>
                </div>

                <div class="mb-4">
                    <label class="block text-grey-darker text-sm font-bold mb-2" for="email">
                        Email
                    </label>

                    <input class="shadow appearance-none border rounded w-full py-2 px-3 text-grey-darker leading-tight focus:outline-none focus:shadow-outline"
                           id="email"
                           type="email"
                           v-model="form.email"
                           placeholder="Email"
                           required>
                </div>

                <div class="mb-6">
                    <label class="block text-grey-darker text-sm font-bold mb-2" for="password1">
                        Password
                    </label>

                    <input class="shadow appearance-none rounded w-full py-2 px-3 text-grey-darker mb-3 leading-tight focus:outline-none focus:shadow-outline"
                           id="password1"
                           type="password"
                           v-model="form.password1"
                           placeholder="******************"
                           required>
                </div>

                <div class="mb-6">
                    <label class="block text-grey-darker text-sm font-bold mb-2" for="password2">
                        Repeat password
                    </label>

                    <input class="shadow appearance-none rounded w-full py-2 px-3 text-grey-darker mb-3 leading-tight focus:outline-none focus:shadow-outline"
                           id="password2"
                           type="password"
                           v-model="form.password2"
                           placeholder="******************">
                </div>

                <div class="flex items-center justify-between">
                    <button class="bg-blue hover:bg-blue-dark text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                            type="submit">
                        Register
                    </button>
                </div>
            </form>
        </div>

        <div class="flex w-full mt-12">
            <form @submit.prevent="login()" class="bg-white w-full shadow-md rounded px-8 pt-6 pb-8 mb-4">
                <h2 class="mb-4">Login</h2>

                <div class="mb-4">
                    <label class="block text-grey-darker text-sm font-bold mb-2" for="email2">
                        Email
                    </label>

                    <input class="shadow appearance-none border rounded w-full py-2 px-3 text-grey-darker leading-tight focus:outline-none focus:shadow-outline"
                           id="email2"
                           type="email"
                           v-model="form2.email"
                           placeholder="Email"
                           required>
                </div>

                <div class="mb-6">
                    <label class="block text-grey-darker text-sm font-bold mb-2" for="password1">
                        Password
                    </label>

                    <input class="shadow appearance-none rounded w-full py-2 px-3 text-grey-darker mb-3 leading-tight focus:outline-none focus:shadow-outline"
                           id="password"
                           type="password"
                           v-model="form2.password"
                           placeholder="******************"
                           required>
                </div>

                <div class="flex items-center justify-between">
                    <button class="bg-blue hover:bg-blue-dark text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                            type="submit">
                        Login
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                form: {
                    firstName: null,
                    lastName: null,
                    email: null,
                    password1: null,
                    password2: null
                },

                form2: {
                    email: null,
                    password: null
                }
            }
        },


        methods: {
            register() {
                axios.post('/1/api/jxrs/register', this.form).then((response) => {
                    window.location.href = '/1/secured';
                }).catch(() => {
                    alert('Something went wrong please try again');
                });
            },

            login() {
                axios.post('/1/api/jxrs/login', this.form2).then((response) => {
                    window.location.href = '/1/secured';
                }).catch(() => {
                    alert('Incorrect credentials!');
                })
            }
        }
    }
</script>