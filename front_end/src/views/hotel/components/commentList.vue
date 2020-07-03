<template>
    <v-container>
        <v-card outlined elevation="2" class="comment_container" >
            <v-list three-line v-for="comment in comments" :key="comment.id">
                <template>
                    <v-list-item>
                        <v-list-item-avatar>
                            <v-img v-bind:src="require('../../../../src/assets/halcyon.png')" @click="test(comment)"></v-img>
                        </v-list-item-avatar>
                        <v-list-item-content>
                            <v-list-item-title >
                                        <v-list-item-content class="button">{{comment.userName}}</v-list-item-content>
                                <v-list-item-content>
                                    <v-row class="my-n5">
                                        <v-col cols="1" >
                                            评分：{{comment.rate}} 分
                                        </v-col>
                                        <v-col cols="1" class="my-n2 ml-2">
                                            <div class="overline">{{comment.createDate}}</div>
                                        </v-col>
                                    </v-row>
                                </v-list-item-content>
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
                alert("本网站不是交友网站");
            }
        }
    }
</script>

<style scoped>
.comment_container{
    margin:100px;
}
</style>