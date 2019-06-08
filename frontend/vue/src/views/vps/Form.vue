<template>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4>
            <v-card class="elevation-12">
              <v-toolbar dark color="primary">
                <v-toolbar-title>Vps</v-toolbar-title>
                <v-spacer></v-spacer>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-select 
                  label="Operating system"
                    v-model="operatingSystem"
                    :items="osList"
                    :error-messages="osErrors"
                    @input="$v.operatingSystem.$touch"
                    @blur="$v.operatingSystem.$touch"
                  />
                  <v-select
                    label="Owner"
                    v-model="userId"
                    :items="users"
                    item-text="username"
                    item-value="id"
                  />
                  <v-text-field 
                        name="cpuCount" label="CPU count" 
                        type="text" 
                        v-model="cpuCount"
                        @input="$v.cpuCount.$touch"
                        @blur="$v.cpuCount.$touch"
                        :error-messages="cpuCountErrors"
                  />
                  <v-text-field 
                        name="cpuRate" label="CPU rate" 
                        type="text" 
                        v-model="cpuRate"
                        @input="$v.cpuRate.$touch"
                        @blur="$v.cpuRate.$touch"
                        :error-messages="cpuRateErrors"
                  />
                  <v-text-field 
                        name="cpuCount" label="RAM" 
                        type="text" 
                        v-model="ram"
                        @input="$v.ram.$touch"
                        @blur="$v.ram.$touch"
                        :error-messages="ramErrors"
                  />
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-btn color="primary" @click="post">{{action}}</v-btn>
              </v-card-actions>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
</template>

<script>
    import {numeric, integer, required} from "vuelidate/lib/validators"

  export default {
    validations:{
        operatingSystem:{required},
        cpuCount:{required,integer},
        cpuRate:{required,numeric},
        ram:{required,numeric}
    },
    data: () => ({
        osList: ["Ubuntu 16.4", "Ubuntu 18.2", "Alpine Linux", "Free BSD", "Windows Server", "Debian 10"]
    }),
    created(){
        this.$store.commit("SET_LOADING", true)
        Promise.all([
            this.$store.dispatch("vps/fetchById", this.$route.params.id),
            this.$store.dispatch("users/fetch"),
        ])
            .then(_ => this.$store.commit("vps/SET_CURRENT",this.$route.params.id))
            .then(_ => this.$store.commit("SET_LOADING", false))
    },
    methods:{
        post(){
            if(this.$v.$invalid) return;
            const data = {
                operatingSystem: this.operatingSystem,
                userId: this.userId,
                cpucount: +this.cpuCount,
                cpurate: +this.cpuRate,
                ram: +this.ram
            }   
            let prom;
            if(this.$route.params.id){
                prom = this.$store.dispatch("vps/update",{id:this.$route.params.id,vps:data})
            } else {
                prom = this.$store.dispatch("vps/create",data)
            }
            prom.then(_ => this.$router.push({path:"/"}))
        }
    },
    computed:{
        state(){
            return this.$store.state.vps.current
        },
        action(){
            return this.$route.params.id ? "Update" : "Create"
        },
        users(){
            return this.$store.getters["users/usersOnly"]
        },

        operatingSystem:{
            get(){
                return this.state.operatingSystem
            },
            set(val){
                return this.$store.commit("vps/UPDATE_CURRENT", {...this.state,operatingSystem:val})
            }
        },
        cpuCount:{
            get(){
                return this.state.cpucount
            },
            set(val){
                return this.$store.commit("vps/UPDATE_CURRENT", {...this.state,cpucount:val})
            }
        },
        cpuRate:{
            get(){
                return this.state.cpurate
            },
            set(val){
                return this.$store.commit("vps/UPDATE_CURRENT", {...this.state,cpurate:val})
            }
        },
        ram:{
            get(){
                return this.state.ram
            },
            set(val){
                return this.$store.commit("vps/UPDATE_CURRENT", {...this.state,ram:val})
            }
        },
        userId:{
            get(){
                return this.state.userId
            },
            set(val){
                return this.$store.commit("vps/UPDATE_CURRENT", {...this.state,userId:val})
            }
        },
        osErrors(){
            const errors = []
            if (!this.$v.operatingSystem.$dirty) return errors
            !this.$v.operatingSystem.required && errors.push('Operating system required')

            return errors
        },
        cpuCountErrors(){
            const errors = []
            if (!this.$v.cpuCount.$dirty) return errors
            !this.$v.cpuCount.required && errors.push('Cpu count required')
            !this.$v.cpuCount.integer && errors.push('Cpu count must be integer!')

            return errors
        },
        cpuRateErrors(){
            const errors = []
            if (!this.$v.cpuRate.$dirty) return errors
            !this.$v.cpuRate.required && errors.push('Cpu rate required')
            !this.$v.cpuRate.numeric && errors.push('Cpu count must be numeric!')

            return errors
        },
        ramErrors(){
            const errors = []
            if (!this.$v.ram.$dirty) return errors
            !this.$v.ram.required && errors.push('ram required')
            !this.$v.ram.numeric && errors.push('ram must be numeric!')

            return errors
        }
    }
  }
</script>