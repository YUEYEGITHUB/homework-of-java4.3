class ThreadA extends Thread{
	Object obj;
	public ThreadA(Object obj) {
		this.obj = obj;
	}

	public void run(){
		synchronized(obj){
			try {
				obj.wait();
				System.out.println("check1");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
