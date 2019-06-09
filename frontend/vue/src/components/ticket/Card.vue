<template>
    <v-card>
        <v-card-title primary-title>
            <h3 class="headline mb-0">{{ticket.vps}}</h3>
            <v-flex xs12>
                <span class="caption font-weight-lite grey--text">{{ticket.id}}</span>
            </v-flex>
            <v-flex xs12>
                Creator: <span class="font-weight-medium">{{ticket.creator}}</span>
            </v-flex>
        </v-card-title>
        <v-card-actions>
            <v-btn :href="`/ticket/${ticket.id}`" >Details</v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
import TicketModal from "../ticket/Modal"
export default {
    components: {TicketModal},
    props:{
        ticket: Object,
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