package kz.iskst.model;

import java.util.Date;

public class UserRequest {
	
	private int id; 
    private User user;
    private Problems problem;     
    private int priority;
    private Date time;
    public enum Problems {NETWORK_NOT_WORK,PC_NOT_LOAD,AUTOCAD,INDORCAD,OFFICE,PRINTER,OTHER};
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}
    
	public void setTime(Date time) {
		this.time = time;
	}
    public long getTimeLong() {
		return time.getTime();
	}
    
	public void setTime(long time) {
		this.time.setTime(time);
	}
	public User getUser() {
        return user;
    }    
    public void setUser(User user) {
        this.user = user;
    }

    public Problems getProblem() {
		return problem;
	}
    public String getProblemString() {
		return problem.toString();
	}
	public void setProblem(Problems problem) {
		if (problem != null) this.problem = problem;
	}
	
	private void setProblem(String problem) {
		if (problem == null) problem = "OTHER";
		switch (problem) {
			case "NETWORK_NOT_WORK" : this.problem = Problems.NETWORK_NOT_WORK; break;
			case "PC_NOT_LOAD" : this.problem = Problems.PC_NOT_LOAD; break;
			case "AUTOCAD" : this.problem = Problems.AUTOCAD; break;
			case "INDORCAD" : this.problem = Problems.INDORCAD; break;
			case "OFFICE" : this.problem = Problems.OFFICE; break;
			case "PRINTER" : this.problem = Problems.PRINTER; break;
			case "OTHER" : this.problem = Problems.OTHER; break;
			default : this.problem = Problems.OTHER;
		}
			
		
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		if (priority < 0) this.priority = 0;
		else if (priority > 10) this.priority = 10;
		else this.priority = priority;
	}
	
	public UserRequest(){
		time = new Date();
		
    }
	
	public UserRequest(User user, Problems problem, int priority) {
		super();
		this.user = user;
		this.problem = problem;
		setPriority(priority);
		this.time = new Date();
		
		
	}
	
	public UserRequest(User user, String problem, int priority, Date time) {
		super();
		this.user = user;
		setProblem(problem);
		setPriority(priority);
		this.time = time;
	}

	public UserRequest(User user, Problems problem, int priority, Date time) {
		super();
		this.user = user;
		setProblem(problem);
		setPriority(priority);
		this.time = time;
	}
		
	public UserRequest(User user, String problem, int priority, long time) {
		super();
		this.user = user;
		setProblem(problem);
		setPriority(priority);
		this.time = new Date(time);
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + priority;
		result = prime * result + ((problem == null) ? 0 : problem.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRequest other = (UserRequest) obj;
		if (id != other.id)
			return false;
		if (priority != other.priority)
			return false;
		if (problem != other.problem)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	
	
	@Override
	public String toString() {
		return "UserRequest [id=" + id + ", user=" + user + ", problem="
				+ problem + ", priority=" + priority + ", time=" + time + "]";
	}

	
	public static void main(String ... args){
		UserRequest ur = new UserRequest();
		ur.setProblem(Problems.NETWORK_NOT_WORK);
		System.out.println(ur);
		System.out.println(ur.getTime().getTime());
		Date d = new Date(115, 10, 23, 18, 37, 30);
		System.out.println(d.getTime());
				
	}
        
}
