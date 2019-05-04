<template>
    <div class="p-8">
        <notifications group="main" position="bottom right" />
    </div>
</template>
<script>
    export default {
        created() {
            let ws = new WebSocket("ws://localhost:8080/1/game/socket/" + localStorage.getItem('user-id'));

            ws.onmessage = (event) => {
                let parsed = JSON.parse(event.data);

                console.log(parsed);
                if(parsed.from !== localStorage.getItem('user-id') && parsed.content === 'new-user') {
                    this.$notify({
                        group: 'main',
                        title: parsed.firstName + ' is online!'
                    });
               }
            };

            if(!localStorage.getItem('registered')) {
                setTimeout(() => {
                    let json = JSON.stringify({
                        "from": localStorage.getItem('user-id'),
                        "content": 'new-user'
                    });

                    ws.send(json);
                    localStorage.setItem('registered', true);
                }, 2000)
            }
        }
    }
</script>

<style>
    .vue-notification {
        padding: 20px;
        margin: 0 30px 30px;
    }
</style>