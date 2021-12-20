<template>
        <a-card hoverable class="hotelCard ant-col-xs-7 ant-col-lg-5 ant-col-xxl-3">
            <img
                    alt="example"
                    src="@/assets/cover.jpeg"
                    slot="cover"
                    referrerPolicy="no-referrer"
            />
            <a-tooltip :title="hotel.title" placement="top">
                <a-card-meta :title="hotel.name">
                    <template slot="description">
                        <a-rate style="font-size: 15px" :value="this.changeStarToNum()" disabled allowHalf/>
                        {{hotel.rate}}分
                    </template>
                </a-card-meta>
            </a-tooltip>
        </a-card>
</template>
<script>
    export default {
        name: '',
        props: {
            hotel: {}
        },
        data() {
            return {}
        },
        methods: {
            changeStarToNum() {
                if (this.hotel.hotelStar === 'Three')
                    return 3;
                else if (this.hotel.hotelStar === 'Four')
                    return 4;
                else if (this.hotel.hotelStar === 'Five')
                    return 5;
            }
        }
    }
</script>
<style scoped lang="less">
    .hotelCard {
        margin: 10px 10px;
        min-width: 180px;
        max-height: 350px;

        img {
            height: 250px;
        }
    }
</style>

<style lang="less">
    .hotelCard {
        .ant-card-body {
            padding: 12px
        }
    }

    .v-card {
        box-shadow: 0 2px 10px #fff;
        border-radius: 199px;
    }

</style>