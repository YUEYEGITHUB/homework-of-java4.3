public class SynchronizedTest {
	public static void main(String[] args) throws InterruptedException{
		Object obj = new Object();
		new ThreadA(obj).start();
		new ThreadB(obj).start();
	}
}
