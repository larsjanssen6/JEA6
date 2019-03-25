<template>
    <div class="flex flex-wrap justify-center items-center w-full h-full px-64 mt-8">
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
                           v-model="form.email"
                           placeholder="Email"
                           required>
                </div>

                <div class="mb-6">
                    <label class="block text-grey-darker text-sm font-bold mb-2" for="password">
                        Password
                    </label>

                    <input class="shadow appearance-none rounded w-full py-2 px-3 text-grey-darker mb-3 leading-tight focus:outline-none focus:shadow-outline"
                           id="password"
                           type="password"
                           v-model="form.password"
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
    import axios from '../axios';

    export default {
        data() {
            return {
                form: {
                    email: null,
                    password: null,
                }
            }
        },

        methods: {
            login() {
                axios.post('/jwt/login', this.form).then(({data}) => {
                    localStorage.setItem('token', data.token);
                }).catch((error) => {
                    alert('Wrong credentials!');
                });
            }
        }
    }
</script>