<template> 
    <v-container class="pa-4" grid-list-md v-if="$store.state.is_loaded">
        <v-layout wrap>
                <v-toolbar
                card
                color="primary"
                dark
                >
                <v-toolbar-title>Ticket details</v-toolbar-title>
                <v-spacer></v-spacer>
                <v-btn color="error" v-if="canClose" @click="closeTicket">
                    <v-icon>close</v-icon>
                </v-btn>
                </v-toolbar>
                    <v-flex xs12>
                        Vps: <a :href="`/vps/${self.vpsId}`"> {{self.vpsId}}</a>
                    </v-flex>
                    <v-flex xs12>
                        Creator: <span class="font-weight-medium">{{creator}}</span>
                    </v-flex>
                    <v-flex xs12>
                        Description:
                    </v-flex>
                    <v-flex xs12>
                        <v-text-field readonly v-model="self.description" />
                    </v-flex>
                    <v-divider />
                    <v-flex xs12 v-for="comment in comments" :key="comment.id">
                        <Comment 
                            :username="comment.username"
                            :isMyMessage="comment.fromId === $store.state.auth.user_id"
                            :text="comment.text"
                        />
                    </v-flex>
                    <v-flex xs12>
                        <v-text-field
                            v-model="message"
                            append-outer-icon="send"
                            box
                            label="Message"
                            type="text"
                            @click:append-outer="sendMessage"
                        />
                    </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
import Comment from "../../components/ticket/Comment"
import SockJS from "sockjs-client"
import Stomp from "webstomp-client"
export default {
    components:{
        Comment
    },
    data: () => ({
        connected:false,
        socket: null,
        stompClient: null,
        message: ""
    }),
    created(){
        this.$store.commit("SET_LOADING",true)
        this.connect()
        Promise.all([
            this.$store.dispatch("tickets/fetchById", this.$route.params.id),
            this.$store.dispatch("users/fetch")
        ]).then(_ => this.$store.commit("SET_LOADING",false))
    },
    computed:{
        self(){
            return this.$store.state.tickets.byId[this.$route.params.id];
        },
        comments(){
            return this.self.comments.map(x => {
                const username = this.$store.state.users.byId[x.fromId].username
                return {...x,username}
            })
        },
        canClose(){
            return this.$store.getters["auth/isAdmin"]
        },
        creator(){
            return this.$store.state.users.byId[this.self.userId].username
        }
    },
    methods:{
        closeTicket(){
            this.stompClient.send("/app/ticket/close", JSON.stringify({
                    ticketId: this.$route.params.id
                }
            ));
        },
        sendMessage(){
            this.stompClient.send("/app/comment", JSON.stringify({
                    ticketId: this.$route.params.id,
                    text: this.message,
                    fromId: this.$store.state.auth.user_id
                })
            );
        },
        connect(){
            this.socket = new SockJS("/ws/info")
            this.stompClient = Stomp.over(this.socket)
            this.stompClient.connect(
                {},
                frame => {
                this.connected = true
                console.log(frame)
                this.stompClient.subscribe("/topic/comments", tick => {
                    this.handleCommentAdded(JSON.parse(tick.body))
                 });
                this.stompClient.subscribe("/topic/ticket/closed", tick => {
                    this.handleTicketClosed(JSON.parse(tick.body))
                 });
                },
                error => {
                    console.log(error)
                    this.connected = false
                }
            );
        },
        handleCommentAdded(tick){
            this.$store.commit("tickets/ADD_COMMENT_TO_TICKET", {ticketId: this.$route.params.id,comment: tick.data})
        },
        handleTicketClosed(tick){
            if(tick === this.$route.params.id){
                this.$router.push({path: "/"})
            }
        }
    }
}
</script>
