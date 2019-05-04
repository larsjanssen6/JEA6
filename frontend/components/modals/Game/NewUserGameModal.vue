<template>
    <modal :name="modalName"
           :adaptive="true"
           :scrollable="false"
           :max-width="maxWidth"
           height="auto"
           @before-open="beforeOpen">

        <form id="scroll" class="overflow-y-auto p-8 center">
            <div class="center flex-col">
                <h2 class="text-black text-center text-2xl">Nieuwe speler</h2>

                <div class="w-full">
                    <div class="flex flex-col p-4">
                        <div class="relative">
                            <select class="block appearance-none w-full bg-grey-lighter border border-grey-lighter text-grey-darker py-3 px-4 pr-8 rounded leading-tight focus:outline-none focus:bg-white focus:border-grey" id="grid-state"
                                    v-model="form.user">
                                <option :value="user" v-for="user in users">{{ user.firstName }}</option>
                            </select>

                            <div class="pointer-events-none absolute pin-y pin-r flex items-center px-2 text-grey-darker">
                                <svg class="fill-current h-4 w-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20"><path d="M9.293 12.95l.707.707L15.657 8l-1.414-1.414L10 10.828 5.757 6.586 4.343 8z"/></svg>
                            </div>
                        </div>

                        <button class="bg-grey-darker mt-4 hover:bg-grey text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" @click="save()">
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
                   user: {},
                   game: {}
                },
                users: [],
                modalName: 'new-user-game-modal'
            }
        },

        mounted() {
            axios.get('/api/user').then((response) => {
                this.users = response.data;
            });
        },

        methods: {
            save() {
                axios.post('/api/user-game', this.form).then((response) => {
                    alert('Speler is toegevoegd!');
                    this.$modal.hide(this.modalName);
                });
            },

            beforeOpen(e) {
                this.form.game = e.params.game;
            }
        }
    }
</script>