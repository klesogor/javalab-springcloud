<template>
    <v-card class="mb5">
        <v-card-title>
            <span :class="{'font-weight-medium': isMyMessage}">
                {{username}}
            </span>
        </v-card-title>
        <v-card-text>
            <span>{{text}}</span>
        </v-card-text>
    </v-card>
</template>

<script>
export default {
    props:{
        username: String,
        text: String,
        isMyMessage: Boolean
    }
}
</script>
