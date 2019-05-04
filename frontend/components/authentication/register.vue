<template>
    <div class="flex flex-wrap justify-center items-center w-full h-full px-64 mt-8">
        <div class="flex w-full mt-12">
            <form @submit.prevent="register()" class="bg-white w-full shadow-md rounded px-8 pt-6 pb-8 mb-4">
                <h2 class="mb-4">Registreer</h2>

                <div class="mb-4">
                    <label class="block text-grey-darker text-sm font-bold mb-2" for="firstName">
                        Voornaam
                    </label>

                    <input class="shadow appearance-none border rounded w-full py-2 px-3 text-grey-darker leading-tight focus:outline-none focus:shadow-outline"
                           id="firstName"
                           type="text"
                           v-model="form.firstName"
                           placeholder="Voornaam"
                           required>
                </div>

                <div class="mb-4">
                    <label class="block text-grey-darker text-sm font-bold mb-2" for="lastName">
                        Achternaam
                    </label>

                    <input class="shadow appearance-none border rounded w-full py-2 px-3 text-grey-darker leading-tight focus:outline-none focus:shadow-outline"
                           id="lastName"
                           type="text"
                           v-model="form.lastName"
                           placeholder="Achternaam"
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

                <div class="flex items-center">
                    <button class="bg-blue hover:bg-blue-dark text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                            type="submit">
                        Register
                    </button>

                    <div class="ml-2 underline" @click="login()">
                        Login
                    </div>
                </div>
            </form>
        </div>
    </div>
</template>

<script>
    import axios from '../../../frontend/axios';

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
            register() {
                axios.post('/api/jwt/register', this.form).then(({data}) => {
                    localStorage.setItem('token', data);
                    window.location.href = '/1/home';
                }).catch((error) => {
                    alert('Wrong credentials!');
                });
            },

            login() {
                this.$router.push({name:'login'})
            }
        }
    }
</script>