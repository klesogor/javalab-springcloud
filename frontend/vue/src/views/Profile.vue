<template>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4>
            <v-card class="elevation-12">
              <v-toolbar dark color="primary">
                <v-toolbar-title>Registration</v-toolbar-title>
                <v-spacer></v-spacer>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field 
                        prepend-icon="call" 
                        name="phone" label="Phone" 
                        type="text" 
                        v-model="phone"
                  />
                  <v-text-field 
                        prepend-icon="assignment" 
                        name="company" 
                        label="Company" id="company" 
                        type="text" 
                        v-model="company"
                  />
                  <v-text-field 
                        prepend-icon="location_on" 
                        name="city" 
                        label="City"  
                        id="city" 
                        type="text"
                        v-model="city"
                  />
                  <v-text-field 
                        prepend-icon="email" 
                        name="email" 
                        label="Email" 
                        id="email" 
                        type="email"
                        @blur="$v.email.$touch()"
                        @input="$v.email.$touch()" 
                        v-model="email"
                        :error-messages="emailErrors"
                    />
                    <v-text-field
                        prepend-icon="person" 
                        name="age" 
                        label="Age" 
                        id="age" 
                        type="text"
                        @blur="$v.age.$touch()"
                        @input="$v.age.$touch()" 
                        v-model="age"
                        :error-messages="ageErrors"
                     />
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-btn color="error" @click="remove">Delete</v-btn>
                <v-spacer></v-spacer>
                <v-btn color="primary" @click="update">Update</v-btn>
              </v-card-actions>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
</template>

<script>
    import {email,minValue, integer} from "vuelidate/lib/validators"

  export default {
    validations:{
        email: {email},
        age: {integer, minValue: minValue(18)},
    },
    created(){
        this.$store.commit("SET_LOADING", true)
        this.$store.dispatch("profile/fetch").then(_ => this.$store.commit("SET_LOADING", false))
    },
    methods:{
      update(){
        if(this.$v.$invalid) return;
        this.$store.dispatch('profile/update',this.state);  
      },
      remove(){
        this.$store.dispatch('profile/delete').then(_ => this.$router.push({to:'login'}))
      }
    },
    computed:{
        state(){
            return this.$store.state.profile
        },
        age:{
            get(){
                return this.$store.state.profile.age
            },
            set(val){
                return this.$store.commit("profile/SET_PROFILE_DATA", {...this.state,age:val})
            }
        },
        city:{
            get(){
                return this.$store.state.profile.city
            },
            set(val){
                return this.$store.commit("profile/SET_PROFILE_DATA", {...this.state,city:val})
            }
        },
        company:{
            get(){
                return this.$store.state.profile.company
            },
            set(val){
                return this.$store.commit("profile/SET_PROFILE_DATA", {...this.state,company:val})
            }
        },
        email:{
            get(){
                return this.$store.state.profile.email
            },
            set(val){
                return this.$store.commit("profile/SET_PROFILE_DATA", {...this.state,email:val})
            }
        },
        phone:{
            get(){
                return this.$store.state.profile.phone
            },
            set(val){
                return this.$store.commit("profile/SET_PROFILE_DATA", {...this.state,phone:val})
            }
        },
        emailErrors(){
            const errors = []
            if (!this.$v.email.$dirty) return errors
            !this.$v.email.email && errors.push('Provide valid email')

            return errors
        },
        ageErrors(){
            const errors = []
            if (!this.$v.age.$dirty) return errors
            !this.$v.age.integer && errors.push('Age must be an integer')
            !this.$v.age.minValue && errors.push('You must be at least 18')

            return errors
        }
    }
  }
</script>