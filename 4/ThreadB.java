class ThreadB extends Thread{
	Object obj;
	public ThreadB(Object obj) {
		this.obj = obj;
	}
	public void run(){
		synchronized(obj){
			obj.notify();
			System.out.println("check2");
		}
	}
}
