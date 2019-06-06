<template>
  <AdminHome v-if="isAdmin" />
</template>

<script>
import AdminHome from "../components/homepage/Admin"
  export default {
    components: {
      AdminHome
    },
    computed: {
      isAdmin(){
        return this.$store.getters["auth/isAdmin"]
      }
    }
  }
</script>
