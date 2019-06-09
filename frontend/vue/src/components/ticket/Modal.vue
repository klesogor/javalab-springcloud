<template>
    <v-flex xs12>
        <v-dialog v-model="dialog" max-width="500">
            <template v-slot:activator="{ on }">
                <v-btn color="primary" dark v-on="on">Create ticket</v-btn>
            </template>
            <v-card>
                <v-card-title>
                <span class="headline">Create ticket</span>
                </v-card-title>
                <v-card-text>
                    <v-container fluid>
                        <v-flex xs12>
                            <v-textarea
                                v-model="text"
                                label="Description"
                                outlined
                            />
                        </v-flex>
                        <v-flex xs12>
                            <v-spacer />
                            <v-btn @click="create">Create</v-btn>
                        </v-flex>
                    </v-container>
                </v-card-text>
            </v-card>
        </v-dialog>
    </v-flex>
</template>

<script>
export default {
    props:{
        userId: String,
        vpsId: String,
    },
    data: () => ({
        text: "",
        dialog: false
    }),
    methods:{
        create(){
            this.$emit("create",{userId: this.userId,vpsId: this.vpsId, description: this.text})
            this.dialog = false
        }
    }
}
</script>
