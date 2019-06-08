<template>
  <AdminHome v-if="isAdmin" />
  <UserHome v-else />
</template>

<script>
import AdminHome from "../components/homepage/Admin"
import UserHome from "../components/homepage/User"
  export default {
    components: {
      AdminHome,
      UserHome
    },
    computed: {
      isAdmin(){
        return this.$store.getters["auth/isAdmin"]
      }
    }
  }
</script>
