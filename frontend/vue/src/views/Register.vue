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
                    prepend-icon="person" 
                    name="login" label="Login" 
                    type="text" 
                    v-model="username"
                    @blur="$v.username.$touch()"
                    @input="$v.username.$touch()"
                    :error-messages="usernameErrors"
                    required
                  />
                  <v-text-field 
                    prepend-icon="lock" 
                    name="password" 
                    label="Password" id="password" 
                    type="password" 
                    v-model="password"
                    @blur="$v.password.$touch()"
                    @input="$v.password.$touch()"
                    :error-messages="passwordErrors"
                    required
                  />
                  <v-text-field 
                    prepend-icon="lock" 
                    name="password_repeat" 
                    label="Password again" 
                    id="password_repeat" 
                    type="password"
                    @blur="$v.password_repeat.$touch()"
                    @input="$v.password_repeat.$touch()"
                    v-model="password_repeat"
                    :error-messages="passwordRepeatErrors"
                    required
                  />
                  <v-text-field 
                    prepend-icon="lock" 
                    name="secret" 
                    label="Secret" 
                    id="secret" 
                    type="text"
                    @blur="$v.secret.$touch()"
                    @input="$v.secret.$touch()" 
                    v-model="secret"
                    :error-messages="secretErrors"
                    required
                    />
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" @click="register">Register</v-btn>
              </v-card-actions>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
</template>

<script>
    import {required, minLength, sameAs} from "vuelidate/lib/validators"

  export default {
    data: () => ({
      username: "",
      password: "",
      password_repeat: "",
      secret: ""
    }),

    validations:{
        username: {required, minLength: minLength(6)},
        password: {required, minLength: minLength(6)},
        password_repeat: {sameAs:sameAs('password')},
        secret: {required}
    },

    methods:{
      register: function(){
        if(this.$v.$invalid) return;
        this.$store.dispatch('auth/register');  
      },
    },
    computed:{
        usernameErrors(){
            const errors = []
            if (!this.$v.username.$dirty) return errors
            !this.$v.username.required && errors.push('Username required!')
            !this.$v.username.minLength && errors.push('Username must be at least 6 characters')

            return errors
        },
        passwordErrors(){
            const errors = []
            if (!this.$v.password.$dirty) return errors
            !this.$v.password.required && errors.push('Password required!')
            !this.$v.password.minLength && errors.push('Password must be at least 6 characters')
            
            return errors
        },
        passwordRepeatErrors(){
            const errors = []
            if (!this.$v.password_repeat.$dirty) return errors
            !this.$v.password_repeat.sameAs && errors.push('Passwords must match!')
            
            return errors
        },
        secretErrors(){
            const errors = []
            if (!this.$v.password.$dirty) return errors
            !this.$v.password.required && errors.push('Password required!')

            return errors
        }
    }
  }
</script>