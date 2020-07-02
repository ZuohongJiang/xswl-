<template>
    <v-container>
        <v-card outlined class="comment_container" >
            <v-list three-line v-for="comment in comments" :key="comment.id">
                <template>
<!--                    <v-divider></v-divider>-->

                    <v-list-item>
                        <v-list-item-avatar>
                            <v-img src="https://cdn.vuetifyjs.com/images/lists/2.jpg" @click="test(comment)"></v-img>
<!--                            <v-icon dark>mdi-account-circle</v-icon>-->
                        </v-list-item-avatar>

                        <v-list-item-content>
                            <v-list-item-title >

                                        <div>{{comment.userName}}</div>
                                <v-row>
                                    <v-col>
                                        <div>{{comment.rate}}</div>
                                    </v-col>
                                    <v-col>
                                        <div>{{comment.createDate}}</div>
                                    </v-col>

                            </v-row>
                            </v-list-item-title>
                            <v-list-item-subtitle v-html="comment.commentContent"></v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                </template>
            </v-list>
        </v-card>
    </v-container>
</template>

<script>
    export default {
        name: "commentList",
        props: {
            comments: {
                type: Array
            }
        },
        mounted(){

        },

        methods:{
            test(comment){
                console.log(comment);
                alert(comment);
            }
        }
    }
</script>

<style scoped>
.comment_container{
    margin:100px;
}
</style>