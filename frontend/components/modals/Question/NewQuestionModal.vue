<template>
    <modal :name="modalName"
           :adaptive="true"
           :scrollable="false"
           :max-width="maxWidth"
           height="auto"
           @before-open="beforeOpen">

        <form id="scroll" class="overflow-y-auto p-8 center" @submit.prevent="create()">
            <div class="center flex-col">
                <h2 class="text-black text-center text-2xl">Nieuwe vraag</h2>

                <div class="w-full">
                    <div class="flex flex-col p-4">
                        <input class="mt-4 bg-white appearance-none border-2 border-grey-lighter rounded w-full py-2 px-4 text-grey-darker leading-tight focus:outline-none focus:bg-white focus:border-purple"
                               v-model="form.question"
                               name="question"
                               type="text"
                               placeholder="Vraag">

                        <input class="mt-4 bg-white appearance-none border-2 border-grey-lighter rounded w-full py-2 px-4 text-grey-darker leading-tight focus:outline-none focus:bg-white focus:border-purple"
                               v-model="form.answer"
                               name="answer"
                               type="text"
                               placeholder="Antwoord">

                        <input class="mt-4 bg-white appearance-none border-2 border-grey-lighter rounded w-full py-2 px-4 text-grey-darker leading-tight focus:outline-none focus:bg-white focus:border-purple"
                               v-model="form.points"
                               name="points"
                               type="number"
                               placeholder="Punten">

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
                    question: '',
                    answer: '',
                    points: '',
                    game: null
                },
                modalName: 'new-question-modal'
            }
        },

        methods: {
            create() {
                axios.post('/api/question', this.form).then((response) => {
                    this.$emit('new-question', response.data);
                    this.$modal.hide(this.modalName);
                });
            },

            beforeOpen(e) {
                this.form.game = e.params.game;
            }
        }
    }
</script>