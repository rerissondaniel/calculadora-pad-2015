package main;

public class Resource {
	private String resource = "";
	private boolean available = true;

	public synchronized String getResource() {
		if (available == true) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		available = true;
		notifyAll();
		return resource;
	}

	public synchronized void setResource(String resource) {
		if (available == false) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		this.resource = resource;
		available = false;
		notifyAll();
	}

}
