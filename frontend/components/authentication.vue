<template>
    <div class="register">
        <form @submit.prevent="register()">
            <div>
                <label for="firstName">First Name</label>
                <input type="text" id="firstName" name="firstName" v-model="form.firstName"/>
            </div>
            <div>
                <label for="lastName">Last Name</label>
                <input type="text" id="lastName" name="lastName" v-model="form.lastName"/>
            </div>

            <div>
                <label for="email">Email</label>
                <input type="text" id="email" name="email" v-model="form.email"/>
            </div>
            <div>
                <label for="password1">Password</label>
                <input type="password" id="password1" name="password1" v-model="form.password1"/>
            </div>
            <div>
                <label for="password2">Password (repeat)</label>
                <input type="password" id="password2" name="password2" v-model="form.password2"/>
            </div>

            <div class="buttonRow">
                <button type="submit">
                    Register and Login
                </button>
            </div>
        </form>


        <form @submit.prevent="login()">

            <div>
                <label for="email">Email</label>
                <input type="text" id="email" name="email" v-model="form2.email"/>
            </div>
            <div>
                <label for="password1">Password</label>
                <input type="password" id="password" name="password1" v-model="form2.password"/>
            </div>

            <div class="buttonRow">
                <button type="submit">
                    Register and Login
                </button>
            </div>
        </form>
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
                    if(response.status == 200) {
                        window.location.href = '/1/secured';
                    }
                })
            },

            login() {
                axios.post('/1/api/jxrs/login', this.form2).then((response) => {
                    if(response.status == 200) {
                        window.location.href = '/1/secured';
                    }

                    else {
                        alert('Incorrect credentials!');
                    }
                })
            }
        }
    }
</script>