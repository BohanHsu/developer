package test;

public class Test{
	private int i = 0;
	public Test(){
		this.i = 66;
	}
	public Test(int i){
		this.i = i;
	}

	public void toConsole(){
		System.out.println(this.i);
	}

	public static void main(String[] args){
		Test t1 = new Test();
		Test t2 = new Test(6);
		t1.toConsole();
		t2.toConsole();
		System.out.println("hello");
	}
}
