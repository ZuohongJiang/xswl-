const getters = {
  //user
  token: state => state.user.token,
  userId: state => state.user.userId,
  userInfo: state => state.user.userInfo,
  orderDetailVisible: state => state.user.orderDetailVisible,
  orderDetail: state => state.user.orderDetail,
  userOrderList: state => state.user.userOrderList,
  hotelListLoading: state => state.hotel.hotelListLoading,
  hotelList: state => state.hotel.hotelList,
  currentHotelInfo: state => state.hotel.currentHotelInfo,
  currentHotelId: state => state.hotel.currentHotelId,
  orderModalVisible: state => state.hotel.orderModalVisible,
  currentOrderRoom: state => state.hotel.currentOrderRoom,
  orderMatchCouponList: state => state.hotel.orderMatchCouponList,
  //admin
  activeUserId: state => state.admin.activeUserId,
  activeUserInfo: state => state.admin.activeUserInfo,
  managerList: state => state.admin.managerList,
  addManagerModalVisible: state => state.admin.addManagerModalVisible,
  addManagerParams: state => state.admin.addManagerParams,
  hotelManagerInfoModalVisible: state => state.admin.hotelManagerInfoModalVisible,
  //hotelManager
  orderList: state => state.hotelManager.orderList,
  addHotelModalVisible: state => state.hotelManager.addHotelModalVisible,
  addHotelParams: state => state.hotelManager.addHotelParams,
  updateHotelInfoModalVisible: state =>state.hotelManager.updateHotelInfoModalVisible,
  updateHotelInfoParams: state => state.hotelManager.updateHotelInfoParams,
  addRoomModalVisible: state => state.hotelManager.addRoomModalVisible,
  editRoomModalVisible:state => state.hotelManager.editRoomModalVisible,
  couponVisible: state => state.hotelManager.couponVisible,
  roomVisible: state => state.hotelManager.roomVisible,
  roomModalVisible: state => state.hotelManager.roomModalVisible,
  roomDetail: state=>state.hotelManager.roomDetail,
  roomList: state => state.hotelManager.roomList,
  addCouponVisible: state => state.hotelManager.addCouponVisible,
  activeHotelId: state => state.hotelManager.activeHotelId,
  activeHotelInfo: state => state.hotelManager.activeHotelInfo,
  couponList: state => state.hotelManager.couponList,
  currentRoom:state=> state.hotelManager.currentRoom,
  manageHotelListLoading: state => state.hotel.manageHotelListLoading,
  manageHotelList: state => state.hotel.manageHotelList,
  managerId: state => state.hotel.managerId,
  hotelIdList: state => state.hotelManager.hotelIdList,
  }


export default getters
