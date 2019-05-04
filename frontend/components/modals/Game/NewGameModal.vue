<template>
    <modal name="new-game-modal"
           :adaptive="true"
           :scrollable="false"
           :max-width="maxWidth"
           height="auto">

        <form id="scroll" class="overflow-y-auto p-8 center" @submit.prevent="create()">
            <div class="center flex-col">
                <h2 class="text-black text-center text-2xl">Nieuwe game</h2>

                <div class="w-full">
                    <div class="flex flex-col p-4">
                        <input class="mt-4 bg-white appearance-none border-2 border-grey-lighter rounded w-full py-2 px-4 text-grey-darker leading-tight focus:outline-none focus:bg-white focus:border-purple"
                               v-model="form.name"
                               name="name"
                               id="name"
                               type="text"
                               placeholder="Naam">

                        <textarea class="mt-4 shadow appearance-none border rounded w-full py-2 px-3 text-grey-darker leading-tight focus:outline-none focus:shadow-outline"
                               v-model="form.description"
                               name="description"
                               id="description"
                               placeholder="Omschrijving"></textarea>

                        <button class="bg-grey-darker mt-4 hover:bg-grey text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
                            Opslaan
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </modal>
</template>

<script>
    import axios from '../../../axios';

    export default {
        data() {
            return {
                maxWidth: 420,
                form: {
                    name: '',
                    description: ''
                }
            }
        },

        methods: {
            create() {
                axios.post('/api/game/' + localStorage.getItem('user-id'), this.form).then((response) => {
                    window.location.reload();
                });
            }
        }
    }
</script>