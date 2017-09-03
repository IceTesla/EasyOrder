package cn.IceTesla.easyorder.Data.Model;


import java.io.Serializable;

public class CheckTableModel implements Serializable {
		private int num;
		private int flag;
		public int getFlag() {
			return flag;
		}
		public void setFlag(int flag) {
			this.flag = flag;
		}
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
}
