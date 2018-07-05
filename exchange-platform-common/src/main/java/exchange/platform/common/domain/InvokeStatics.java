package exchange.platform.common.domain;

import java.io.Serializable;

public class InvokeStatics implements Serializable{

	/**
	 * 2018年7月3日下午4:30:18
	 * Tony
	 */
	private static final long serialVersionUID = 8677699298777104228L;

	private String  serviceName;
	private long  invokeTime;
	private boolean status;
	public InvokeStatics(String serviceName, long invokeTime, boolean status) {
		super();
		this.serviceName = serviceName;
		this.invokeTime = invokeTime;
		this.status = status;
	}
	/**
	 * get serviceName value
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}
	/**
	 * set serviceName value
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	/**
	 * get invokeTime value
	 * @return the invokeTime
	 */
	public long getInvokeTime() {
		return invokeTime;
	}
	/**
	 * set invokeTime value
	 * @param invokeTime the invokeTime to set
	 */
	public void setInvokeTime(long invokeTime) {
		this.invokeTime = invokeTime;
	}
	/**
	 * get status value
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}
	/**
	 * set status value
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
}
