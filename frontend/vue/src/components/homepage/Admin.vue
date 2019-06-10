<template> 
    <v-container class="pa-4" grid-list-md v-if="$store.state.is_loaded">
        <v-layout wrap>
                <v-flex xs12>
                    <h2 class="display-1 mb-1">
                        Vps
                    </h2>
                </v-flex>
                <v-flex xs12>
                    <v-layout row wrap>
                        <v-flex xs12 sm6 md4 v-for="vps in vps" :key="vps.id">
                            <Vps :vps=vps @remove="handleRemove" canMutate />
                        </v-flex>
                    </v-layout>
                </v-flex>
                <v-flex xs12>
                    <h2 class="display-1 mb-1">
                        Tickets
                    </h2>
                </v-flex>
                <v-flex xs12>
                    <v-layout row wrap>
                        <v-flex xs12 sm6 md4 v-for="ticket in tickets" :key="ticket.id">
                            <Ticket :ticket=ticket />
                        </v-flex>
                    </v-layout>
                </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
import Vps from "../vps/Card.vue"
import Ticket from "../ticket/Card.vue"
export default {
    components:{
        Vps,
        Ticket
        },
    created(){
        this.$store.commit("SET_LOADING", true)
        Promise.all([
            this.$store.dispatch("vps/fetch"),
            this.$store.dispatch("users/fetch"),
            this.$store.dispatch("tickets/fetch")
        ]).then(_ => this.$store.commit("SET_LOADING", false))
    },
    computed:{
        vps(){
            return this.$store.state.vps.all.map(v => {
                const user = this.$store.state.users.byId[v.userId]
                return {...v,owner: user ? user.username : "No owner yet"}
            })
        },
        tickets(){
            return this.$store.state.tickets.all.map(v => {
                const vps = this.$store.state.vps.byId[v.vpsId]
                const user = this.$store.state.users.byId[v.userId]
                return {...v,creator: user.username, vps: vps.operatingSystem}
            })
        }
    },
     methods: {
         handleRemove(vpsId){
            this.$store.commit("SET_LOADING", true)
            this.$store.dispatch("vps/delete", vpsId)
                .then(_ => this.$store.dispatch("vps/fetch"))
                .then(_ => this.$store.commit("SET_LOADING",false))
         }
     }
}
</script>
