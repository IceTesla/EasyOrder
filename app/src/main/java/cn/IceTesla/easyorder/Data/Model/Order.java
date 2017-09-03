package cn.IceTesla.easyorder.Data.Model;

public class Order {
		private int id;
		private String orderTime;
		private String userId;
		private int tableId;
		private int personNum;
		private int isPay;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getIsPay() {
			return isPay;
		}
		public void setIsPay(int isPay) {
			this.isPay = isPay;
		}
		public String getOrderTime() {
			return orderTime;
		}
		public void setOrderTime(String orderTime) {
			this.orderTime = orderTime;
		}
		public int getPersonNum() {
			return personNum;
		}
		public void setPersonNum(int personNum) {
			this.personNum = personNum;
		}
		public int getTableId() {
			return tableId;
		}
		public void setTableId(int tableId) {
			this.tableId = tableId;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		
}
