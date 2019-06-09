<template>
    <v-card>
        <v-card-title primary-title>
            <h3 class="headline mb-0">{{vps.operatingSystem}}</h3>
            <v-flex xs12>
                <span class="caption font-weight-lite grey--text">{{vps.id}}</span>
            </v-flex>
            <v-flex xs12>
                Owner: <span class="font-weight-medium">{{vps.owner}}</span>
            </v-flex>
            <v-flex xs12>
                Status: <span :class="statusClass">{{statusText}}</span>
            </v-flex>
        </v-card-title>
        <v-card-actions>
        <template v-if="canMutate">
            <v-btn @click="remove" color="error">Delete</v-btn>
            <v-spacer />
            <v-btn :href="`/vps/form/${vps.id}`" color="info">Edit</v-btn>
        </template>
        <template v-else>
            <TicketModal
                :vpsId="vps.id"
                :userId="$store.state.auth.user_id"
                @create="createTicket"
             />
        </template>
        </v-card-actions>
    </v-card>
</template>

<script>
import TicketModal from "../ticket/Modal"
export default {
    components: {TicketModal},
    props:{
        vps: Object,
        canMutate: Boolean
    },
    computed:{
        online(){
            return Math.random() > 0.5 ? true : false;
        },
        statusText(){
            return this.online ? "ONLINE" : "OFFLINE"
        },
        statusClass(){
            return {
                "green--text": this.online,
                "red--text": !this.online,
                "text--accent-3": true
            }
        },
    },
    methods: {
        remove(){
            this.$emit("remove", this.vps.id)
        },
        createTicket(payload){
            this.$emit("createTicket", payload)
        }
    }
}
</script>