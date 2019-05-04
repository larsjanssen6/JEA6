<template>
    <div class="flex flex-col justify-center mt-12 px-64">
        <div class="flex justify-end">
            <button @click="$modal.show('new-game-modal')" class="bg-grey-darker hover:bg-blue-dark text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
                Nieuwe game
            </button>
        </div>

        <table class="text-left mt-6 bg-white shadow-lg" style="border-collapse:collapse">
            <thead>
            <tr class="cursor-pointer">
                <th class="py-4 px-6 bg-grey font-sans font-medium uppercase text-sm text-black border-b border-grey-light">
                    id
                </th>

                <th class="py-4 px-6 bg-grey font-sans font-medium uppercase text-sm text-black border-b border-grey-light">
                    Naam
                </th>

                <th class="py-4 px-6 bg-grey font-sans font-medium uppercase text-sm text-black border-b border-grey-light">
                    Omschrijving
                </th>

                <th></th>
            </tr>
            </thead>

            <tbody>
                <tr class="hover:bg-blue-lightest cursor-pointer" v-for="game in games" @click="$modal.show('game-overview-modal', { game: game.game })">
                    <td class="py-4 px-6 border-b border-grey-light">
                        {{ game.game.id }}
                    </td>

                    <td class="py-4 px-6 border-b border-grey-light">
                        {{ game.game.name }}
                    </td>

                    <td class="py-4 px-6 border-b border-grey-light">
                        {{ game.game.description }}
                    </td>

                    <td>
                        <div class="cursor-pointer" @click.stop="remove(game.id)">
                            <svg version="1.1" id="Capa_1" xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="20px" height="20px" viewBox="0 0 510 510" style="enable-background:new 0 0 510 510;" xml:space="preserve"><g><g id="cancel"><path d="M255,0C114.75,0,0,114.75,0,255s114.75,255,255,255s255-114.75,255-255S395.25,0,255,0z M382.5,346.8l-35.7,35.7 L255,290.7l-91.8,91.8l-35.7-35.7l91.8-91.8l-91.8-91.8l35.7-35.7l91.8,91.8l91.8-91.8l35.7,35.7L290.7,255L382.5,346.8z"/></g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g></svg>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>

        <socket-setup></socket-setup>
        <game-overview-modal></game-overview-modal>
        <new-game-modal></new-game-modal>
    </div>
</template>

<script>
    import axios from '../../axios';

    export default {
        data() {
            return {
                games: []
            }
        },

        created() {
           this.getGames();
        },

        methods: {
            remove(id) {
                this.$swal.fire({
                    title: 'Weet u het zeker?',
                    type: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Ja, verwijder dit spel!'
                }).then((result) => {
                    if(result.value) {
                        axios.delete('/api/game/' + id).then(() => {
                            window.location.reload();
                        });
                    }
                });
            },

            getGames() {
                axios.get('/api/user-game/' + localStorage.getItem('user-id')).then((response) => {
                    this.games = response.data;
                });
            }
        }
    }
</script>