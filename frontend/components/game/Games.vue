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

                <th class="py-4 px-6 bg-grey font-sans font-medium uppercase text-sm text-black border-b border-grey-light">
                    Datum
                </th>
            </tr>
            </thead>

            <tbody>
                <tr class="hover:bg-blue-lightest cursor-pointer" v-for="game in games">
                    <td class="py-4 px-6 border-b border-grey-light">
                        {{ game.id }}
                    </td>

                    <td class="py-4 px-6 border-b border-grey-light">
                        {{ game.name }}
                    </td>

                    <td class="py-4 px-6 border-b border-grey-light">
                        {{ game.description }}
                    </td>

                    <td class="py-4 px-6 border-b border-grey-light">
                        {{ game.created_at }}
                    </td>
                </tr>
            </tbody>
        </table>

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
            axios.get('/api/game').then((response) => {
                this.games = response.data;
            });
        }
    }
</script>