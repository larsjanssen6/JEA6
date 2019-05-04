<template>
    <modal :name="modalName"
           :adaptive="true"
           :scrollable="false"
           height="250px"
           @before-open="beforeOpen">

        <div class="w-full flex flex-col items-center justify-center h-full p-12">
            <h1 class="mb-2">Profiel</h1>

            <div class="flex mb-2">
                <strong>Voornaam:</strong>&nbsp;

                <p class="mb-2">
                    {{ user.firstName }}
                </p>
            </div>

            <div class="flex mb-2">
                <strong>Achternaam:</strong>&nbsp;

                <p class="mb-2">
                    {{ user.lastName }}
                </p>
            </div>

            <div class="flex mb-2">
                <strong>Email:</strong>&nbsp;

                <p class="mb-2">
                    {{ user.email }}
                </p>
            </div>

            <button class="bg-grey-darker mt-4 hover:bg-grey text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                    @click="$modal.show('edit-profile-modal')">
                Bewerk profiel
            </button>
        </div>

        <edit-profile-modal></edit-profile-modal>
        <close-modal :name="modalName"></close-modal>
    </modal>
</template>

<script>
    import axios from '../../../axios';

    export default {
        data() {
            return {
                user: {},
                modalName: 'profile-modal'
            }
        },

        methods: {
            beforeOpen(e) {
                axios.get('/api/user/' + localStorage.getItem('user-id')).then((response) => {
                    this.user = response.data;
                });
            }
        }
    }
</script>