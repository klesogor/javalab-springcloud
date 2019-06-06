<template>
    <v-card>
        <v-card-title primary-title>
            <h3 class="headline mb-0">{{vps.operatingSystem}}</h3>
            <v-flex xs12>
                Owner: <span class="font-weight-medium">{{owner}}</span>
            </v-flex>
            <v-flex xs12>
                Status: <span :class="statusClass">{{statusText}}</span>
            </v-flex>
        </v-card-title>
        <v-card-actions v-if="canMutate">
            <v-btn flat color="error">Delete</v-btn>
            <v-btn flat color="info">Edit</v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
export default {
    props:{
        id: String
    },
    computed:{
        vps(){
            console.log(this.id)
            return this.$store.state.vps.byId[this.id]
        },
        online(){
            return Math.random() > 0.5 ? true : false;
        },
        statusText(){
            return this.online ? "ONLINE" : "OFFLINE"
        },
        owner(){
            const user = this.$store.state.users.byId[this.vps.userId]
            return user ? user.username : "No user yet"
        },
        statusClass(){
            return {
                "green--text": this.online,
                "red--text": !this.online,
                "text--accent-3": true
            }
        },
        canMutate(){
            return this.$store.getters["auth/isAdmin"]
        }
    }
}
</script>