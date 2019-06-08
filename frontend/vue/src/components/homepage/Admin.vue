<template> 
    <v-container class="pa-4" grid-list-md>
        <v-layout wrap>
                <v-flex xs12>
                    <h2 class="display-1 mb-1">
                        Vps
                    </h2>
                </v-flex>
                <v-flex xs12>
                    <v-layout row wrap>
                        <v-flex xs12 sm6 md4 v-for="vps in vps" :key="vps.id">
                            <Vps :vps=vps canMutate />
                        </v-flex>
                    </v-layout>
                </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
import Vps from "../vps/Card.vue"
import { Promise } from 'q';
export default {
    components:{
        Vps
        },
    created(){
        this.$store.commit("SET_LOADING", true)
        Promise.all([
            this.$store.dispatch("vps/fetch"),
            this.$store.dispatch("users/fetch")
        ]).then(this.$store.commit("SET_LOADING", false))
    },
    computed:{
        vps(){
            return this.$store.state.vps.all.map(v => {
                const user = this.$store.state.users.byId[v.userId]
                return {...v,owner: user ? user.username : "No owner yet"}
            })
        }
    }
}
</script>
