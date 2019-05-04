<template>
    <modal :name="modalName"
           :adaptive="true"
           :scrollable="false"
           width="30%"
           height="500px"
           @before-open="beforeOpen">

        <div class="relative w-full h-full p-12" v-if="game">
            <div class="w-full h-full flex flex-col items-center justify-center bg-grey-lighter p-2 rounded">
                <div class="flex ">
                    <div class="flex text-center text-black mt-2 flex-col">
                        <h3>{{ game.name }}</h3>
                        <h4>{{ game.description }}</h4>
                    </div>
                </div>

                <button class="bg-grey-darker w-1/2 mt-4 hover:bg-grey text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                        @click="$modal.show('new-user-game-modal', { game })">
                    Nieuwe speler
                </button>

                <button class="bg-grey-darker w-1/2 mt-4 hover:bg-grey text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                        @click="$modal.show('question-modal', { game })">
                    Vragen
                </button>

                <button class="bg-grey-darker w-1/2 mt-4 hover:bg-grey text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                        @click="$modal.show('score-modal', { game })">
                    Scores
                </button>

                <div class="flex flex-col items-center justify-center w-full mt-4">
                    <div class="max-w-lg p-8 text-center bg-white rounded">
                        <div class="mb-2">
                            <h4>Spelers:</h4>
                        </div>

                        <div class="text-black flex-col p-2">
                            <div v-for="player in players">
                                <h3>{{ player.user.firstName }}  {{ player.user.lastName }}</h3>
                            </div>
                        </div>
                    </div>

                    <button @click="$modal.show('game-modal', { game })" class="text-white p-2 bg-green rounded mt-4 w-2/3">
                        Start Game
                    </button>
                </div>
            </div>
        </div>

        <game-modal></game-modal>
        <new-user-game-modal></new-user-game-modal>
        <question-modal></question-modal>
        <score-modal></score-modal>
        <close-modal :name="modalName"></close-modal>
    </modal>
</template>

<script>
    import axios from '../../../axios';

    export default {
        data() {
            return {
                game: {
                    name: "",
                    description: ""
                },
                players: [],
                modalName: 'game-overview-modal'
            }
        },

        methods: {
            beforeOpen(e) {
                this.game = e.params.game;

                axios.get('/api/game/' + this.game.id).then((response) => {
                    this.players = response.data;
                });
            }
        }
    }
</script>