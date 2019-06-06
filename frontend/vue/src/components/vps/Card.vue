<template>
    <v-card>
        <v-card-title primary-title>
            <h3 class="headline mb-0">{{vps.operatingSystem}}</h3>
            <div>
                Owner: <span class="font-weight-medium">{{owner}}</span>
            </div>
            <div>
                Status: <span :class="statusClass">{{statusText}}</span>
            </div>
        </v-card-title>
        <v-card-actions v-if="canMutate">
            <v-btn flat color="error">Delete</v-btn>
            <v-btn flat color="info">Edit</v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
export default {
    options:{
        id:{
            required: true,
            type: "string"
        }
    },
    computed:{
        vps(){
            return this.$store.vps.state.byId[this.id]
        },
        online(){
            return Math.random() > 0.5 ? true : false;
        },
        statusText(){
            return this.online ? "ONLINE" : "OFFLINE"
        },
        owner(){
            return this.$store.users.state.byId[this.vps.userId]
        },
        statusClass(){
            return {
                "green--text": this.online,
                "red--text": !this.online,
                "text--accent-3": true
            }
        },
        canMutate(){
            return this.$store.auth.getters.isAdmin()
        }
    }
}
</script>