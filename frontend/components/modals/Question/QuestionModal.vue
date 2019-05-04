<template>
    <modal :name="modalName"
           :adaptive="true"
           :scrollable="false"
           height="auto"
           @before-open="beforeOpen">

        <div class="p-12">
            <div class="flex flex-col text-center bg-grey-lighter p-8 rounded">
                <div class="flex flex-col mb-2" v-for="(question, index) in questions">
                    <strong>{{ index+1 }}. {{ question.question }}</strong>
                    <p>{{ question.answer }}</p>
                </div>
            </div>

            <button class="bg-grey-darker mt-4 hover:bg-grey text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                    @click="$modal.show('new-question-modal', { game })">
                Nieuwe vraag
            </button>
        </div>

        <new-question-modal @new-question="newQuestion"></new-question-modal>
        <close-modal :name="modalName"></close-modal>
    </modal>
</template>

<script>
    import axios from '../../../axios';

    export default {
        data() {
            return {
                questions: [],
                modalName: 'question-modal'
            }
        },

        methods: {
            newQuestion(question) {
                this.questions.push(question);
            },

            beforeOpen(e) {
                this.game = e.params.game;

                axios.get('/api/question/' + this.game.id).then((response) => {
                    this.questions = response.data;
                });
            }
        }
    }
</script>