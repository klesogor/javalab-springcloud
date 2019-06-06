<template>
<v-toolbar app>
      <v-toolbar-title class="headline text-uppercase">
        <span>Hosting</span>
        <span class="font-weight-light">MANAGMENT SYSTEM</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <template v-if="!isLoggedIn">
      <v-btn
        flat
        href="/login"
      >
        <span class="mr-2">Sign In</span>
      </v-btn>
      </template>
      <template v-else>
        <v-tooltip
        flat
        >
         <template v-slot:activator="{ on }">
             <v-btn flat href="/profile">
                <span class="mr-2">{{username}}</span>
            </v-btn>
         </template>
        </v-tooltip>
        <v-tooltip
            flat
        >
             <template v-slot:activator="{ on }">
             <v-btn icon @click="logout">
                <v-icon dark icon>exit_to_app</v-icon>
            </v-btn>
         </template>
        </v-tooltip>
      </template>
</v-toolbar>
</template>

<script>
import {mapState, mapGetters} from "vuex"

export default {
    name: "Header", 
    computed: {
        ...mapState("auth",["username"]),
        ...mapGetters("auth", ["isLoggedIn"])
    },
    methods: {
        logout(){
            this.$store.dispatch('auth/logout').then(() => this.$router.push({path:'/login'}))
        }
    }
}
</script>