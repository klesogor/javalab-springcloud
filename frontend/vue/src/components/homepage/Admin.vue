<template>
    <v-layout>
        <template v-for="id in vps">
            <v-flex xs12 sm6 md4 lg3 :key="id">
                <Vps :id=id />
            </v-flex>
        </template>
    </v-layout>
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
            return this.$state.vps.all.map(x => x.id)
        }
    }
}
</script>
