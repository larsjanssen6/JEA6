<template>
    <modal :name="modalName"
           :adaptive="true"
           :scrollable="false"
           :max-width="maxWidth"
           height="350px">

        <form id="scroll" class="overflow-y-auto p-8 center w-full h-full" @submit.prevent="update()">
            <div class="center w-full flex-col">
                <h2 class="text-black text-center text-2xl">Bewerk profiel</h2>

                <div class="w-full">
                    <div class="flex flex-col p-4">
                        <input class="mt-4 bg-white appearance-none border-2 border-grey-lighter rounded w-full py-2 px-4 text-grey-darker leading-tight focus:outline-none focus:bg-white focus:border-purple"
                               v-model="form.firstName"
                               name="firstName"
                               type="text"
                               placeholder="Voornaam">

                        <input class="mt-4 bg-white appearance-none border-2 border-grey-lighter rounded w-full py-2 px-4 text-grey-darker leading-tight focus:outline-none focus:bg-white focus:border-purple"
                               v-model="form.lastName"
                               name="lastName"
                               type="text"
                               placeholder="Achternaam">

                        <input class="mt-4 bg-white appearance-none border-2 border-grey-lighter rounded w-full py-2 px-4 text-grey-darker leading-tight focus:outline-none focus:bg-white focus:border-purple"
                               v-model="form.email"
                               name="email"
                               type="email"
                               placeholder="Email">

                        <button class="bg-grey-darker mt-4 hover:bg-grey text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
                            Opslaan
                        </button>
                    </div>
                </div>
            </div>
        </form>

        <close-modal :name="modalName"></close-modal>
    </modal>
</template>

<script>
    import axios from '../../../axios';

    export default {
        data() {
            return {
                maxWidth: 420,
                form: {
                    firstName: '',
                    lastName: '',
                    email: '',
                    id: localStorage.getItem('user-id')
                },
                modalName: 'edit-profile-modal'
            }
        },

        created() {
            axios.get('/api/user/' + localStorage.getItem('user-id')).then((response) => {
                this.form.firstName = response.data.firstName;
                this.form.lastName = response.data.lastName;
                this.form.email = response.data.email;
            });
        },

        methods: {
            update() {
                axios.post('/api/user', this.form).then((response) => {
                    alert('Profiel geupdate!');
                    console.log(response);
//                    window.location.reload();
                });
            }
        }
    }
</script>