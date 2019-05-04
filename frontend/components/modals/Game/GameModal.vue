<template>
    <modal :name="modalName"
           :adaptive="true"
           :scrollable="false"
           width="40%"
           height="250px"
           @before-open="beforeOpen">

        <div class="flex flex-col relative w-full h-full p-12" v-if="questions.length > 0">
            <h2 class="mb-2 mt-2">{{ question.question }}</h2>

            <input class="mt-4 bg-white appearance-none border-2 border-grey-lighter rounded w-full py-2 px-4 text-grey-darker leading-tight focus:outline-none focus:bg-white focus:border-purple"
                   v-model="form.answer"
                   name="answer"
                   type="text"
                   placeholder="Antwoord.....">

            <button @click="send()" class="bg-grey-darker mt-4 hover:bg-grey text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
                Submit
            </button>

            <close-modal :name="modalName"></close-modal>
        </div>
    </modal>
</template>

<script>
    import axios from '../../../axios';

    export default {
        data() {
            return {
                questions: [],
                points: [],
                form: {
                    answer: null
                },
                activeQuestion: 0,
                modalName: 'game-modal',
                game: null,
            }
        },

        methods: {
            async beforeOpen(e) {
                this.game = e.params.game;

                const questionPromise = axios.get('/api/question/' + this.game.id);
                const pointPromise = axios.get('/api/point/' + localStorage.getItem('user-id') + '/' + this.game.id);

                const [questionResponse, pointResponse] =
                    await Promise.all([questionPromise, pointPromise]);

                let questions = questionResponse.data;
                this.points = pointResponse.data;

                questions.forEach((question) => {
                    let exists = false;

                    this.points.forEach((point) => {
                        if(point.question.id === question.id) {
                            exists = true;
                        }
                    });

                    if(!exists) {
                        this.questions.push(question);
                    }
                });

                if(this.questions.length === 0) {
                    alert('Je hebt dit spel al gespeeld!');
                    this.$modal.hide(this.modalName);
                }
            },

            send() {
                if(this.question.answer.replace(/ /g,'') === this.form.answer.replace(/ /g,'')) {
                    alert('Goed geantwoord!');
                    axios.post('/api/point', { question: this.question, game: this.game, score: this.question.points, user: { id: localStorage.getItem('user-id')} })
                }

                else {
                    alert('Whoops fout antwoord!');
                    axios.post('/api/point', { question: this.question, game: this.game, score: 0, user: { id: localStorage.getItem('user-id')} })
                }

                this.form.answer = null;

                let activeQuestion = this.activeQuestion;

                if(this.questions.length === (activeQuestion+=1)) {
                    alert('Spel is afgelopen!');
                    this.$modal.hide(this.modalName);
                }

                this.activeQuestion++;
            }
        },

        computed: {
            question: function() {
                return this.questions[this.activeQuestion];
            }
        }
    }
</script>