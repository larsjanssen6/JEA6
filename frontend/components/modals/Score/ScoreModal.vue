<template>
    <modal :name="modalName"
           :adaptive="true"
           :scrollable="false"
           height="auto"
           @before-open="beforeOpen">

        <div class="flex items-center justify-center p-12">
            <div class="flex flex-col p-4">
                <h2 class="mb-4">Scores</h2>

                <div class="flex" v-for="point in finalPoints" v-if="finalPoints.length > 0">
                    <strong>{{ point.user.firstName}}</strong> - {{ point.score }} punten
                </div>

                <div v-else>
                    <strong>Er zijn nog geen scores.</strong>
                </div>
            </div>
        </div>

        <close-modal :name="modalName"></close-modal>
    </modal>
</template>

<script>
    import axios from '../../../axios';

    export default {
        data() {
            return {
                points: [],
                modalName: 'score-modal'
            }
        },

        methods: {
            beforeOpen(e) {
                this.game = e.params.game;

                axios.get('/api/point/' + this.game.id).then((response) => {
                    this.points = response.data;
                });
            }
        },

        computed: {
            finalPoints: function() {
                let result = [];

                this.points.forEach((point) => {
                    let exists = false;

                    result.forEach((result) => {
                        if(result.user.id === point.user.id) {
                            exists = result;
                        }
                    });

                    if(exists) {
                        result.find((f) => f.user.id === point.user.id).score += point.score;
                    }

                    else {
                        result.push(point);
                    }
                });

                return result;
            }
        }
    }
</script>