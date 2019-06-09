<template> 
    <v-container class="pa-4" grid-list-md>
        <v-layout wrap v-if="$store.state.is_loaded">
                <v-flex xs12>
                    <h2 class="display-1 mb-1">
                        Vps
                    </h2>
                </v-flex>
                <v-flex xs12>
                    <v-layout row wrap>
                        <v-flex xs12 sm6 md4 v-for="v in vps" :key="v.id">
                            <Vps :vps="v" @createTicket="handleCreateTicket" />
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
            this.$store.dispatch("vps/fetchMy"),
            this.$store.dispatch("tickets/fetchMy")
        ]).then(_ => this.$store.commit("SET_LOADING", false))
    },
    computed:{
        vps(){
            return this.$store.state.vps.all.map(v => ({...v, owner: this.$store.state.auth.username}))
        },
        tickets(){
            return this.$store.state.tickets.all.map(v => {
                const vps = this.$store.state.vps.byId[v.vpsId]
                return {...v, creator: this.$store.state.auth.username, vps: vps.operatingSystem}
            })
        }
    },
    methods:{
        handleCreateTicket({userId,vpsId,description}){
            this.$store.dispatch("tickets/create",{userId,vpsId,description}).then(({id}) => this.$router.push({path:`/ticket/${id}`}))
        }
    }
}
</script>
