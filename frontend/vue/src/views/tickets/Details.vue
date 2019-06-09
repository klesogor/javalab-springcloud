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
                <v-btn v-if="canClose" @click="closeTicket">
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
                    <v-flex xs12>
                        <Comment
                            v-for="comment in comments"
                            :key="comment.id"
                            :username="comment.username"
                            :isMyMessage="comment.fromId === this.$store.state.auth.user_id"
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
export default {
    components:{
        Comment
    },
    created(){
        this.$store.commit("SET_LOADING",true)
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

        },
        sendMessage(){

        }
    }
}
</script>
