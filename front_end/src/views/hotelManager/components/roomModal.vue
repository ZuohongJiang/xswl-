<template>
    <a-modal
            :visible="roomModalVisible"
            title="房间详情"
            :footer="null"
            @cancel="cancel">
        <a-card style="margin-bottom: 3%;height: 130px" >
            <a-row>
                <a-col :span="12" style="margin-top: 27px">
                    <b style="font-size: 19px">{{roomDetail.roomType}}</b>
                    <font style="color: gray;margin-left: 13px">id:{{roomDetail.id}}</font>
                </a-col>
                <a-col :span="12">
                    建筑面积： <b v-if="roomDetail.area">{{roomDetail.area}}平方米</b><br/>
                    楼层： <b v-if="roomDetail.level">{{roomDetail.level}}层</b><br/>
                    床型： <b v-if="roomDetail.bedType">{{roomDetail.bedType}}平方米</b><br/>
                    加床： <b v-if="roomDetail.addBed">可加床</b><b v-else>不可加床</b><br/>
                </a-col>
            </a-row>
        </a-card>
        <a-card style="margin-bottom: 3%" >
            <b>所有房型设施</b>
            <p v-html="roomDetail.facility" style="margin-top: 7px"></p>
        </a-card>
    </a-modal>
</template>

<script>
    import {mapGetters, mapMutations, mapActions} from 'vuex'
    export default {
        name: "roomModal",
        computed: {
            ...mapGetters([
                'roomModalVisible',
                'roomDetail'
            ])
        },
        methods: {
            ...mapMutations([
                'set_roomModalVisible'
            ]),
            cancel(){
                this.set_roomModalVisible(false)
            }
        }
    }
</script>

<style scoped>

</style>