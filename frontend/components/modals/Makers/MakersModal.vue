<template>
    <modal :name="modalName"
           :adaptive="true"
           :scrollable="false"
           height="250px">

        <div class="w-full flex flex-col items-center justify-center h-full p-12">
            <h2>Gemaakt door: Lars Janssen</h2>
            <h6 class="mt-4">Versie: 1.0</h6>
        </div>

        <close-modal :name="modalName"></close-modal>
    </modal>
</template>

<script>
    export default {
        data() {
            return {
                modalName: 'makers-modal'
            }
        }
    }
</script>