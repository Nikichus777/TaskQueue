package kz.iskst.model;


public class User {
    private int id;
    private String login;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    public enum Group {ROAD,ENGINEER,GEODESISTS,BUILDERS,GEOLOGISTS,ADMINISTRATION,ACCOUNTING,BUDGET_CALCULATION,MATCHERS,OTHERS};
    private Group group;
    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
    
    public void setGroup(String group) {
        switch(group){
        case "road" : this.group = Group.ROAD; break;
        case "accounting" : this.group = Group.ACCOUNTING; break;
        case "administration" : this.group = Group.ADMINISTRATION; break;
        case "budget_calculation" : this.group = Group.BUDGET_CALCULATION; break;
        case "builders" : this.group = Group.BUILDERS; break;
        case "engineer" : this.group = Group.ENGINEER; break;
        case "geologist" : this.group = Group.GEOLOGISTS; break;
        case "matchers" : this.group = Group.MATCHERS; break;
        case "surveyors" : this.group = Group.GEODESISTS; break;
        case "others" : this.group = Group.OTHERS; break;
        default : this.group = Group.OTHERS; break;
        
        }
    	
    }

    public int getId() {
	return id;
    }
    
    public void setId(int id) {
	this.id = id;
    }
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
       this.login = login;
    }
    
    public String getEmail() {
        return email;
    }
    

    public void setEmail(String email) {
	this.email = email;
    }
    
    public User(String login, String email) {
		setLogin(login);
		setEmail(email);
    }
    
    public User() {
	
    }

    
    
    
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result + ((group == null) ? 0 : group.hashCode());
	result = prime * result + id;
	result = prime * result + ((login == null) ? 0 : login.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result
		+ ((password == null) ? 0 : password.hashCode());
	result = prime * result
		+ ((patronymic == null) ? 0 : patronymic.hashCode());
	result = prime * result + ((surname == null) ? 0 : surname.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (!(obj instanceof User)) {
	    return false;
	}
	User other = (User) obj;
	if (email == null) {
	    if (other.email != null) {
		return false;
	    }
	} else if (!email.equals(other.email)) {
	    return false;
	}
	if (group != other.group) {
	    return false;
	}
	if (id != other.id) {
	    return false;
	}
	if (login == null) {
	    if (other.login != null) {
		return false;
	    }
	} else if (!login.equals(other.login)) {
	    return false;
	}
	if (name == null) {
	    if (other.name != null) {
		return false;
	    }
	} else if (!name.equals(other.name)) {
	    return false;
	}
	if (password == null) {
	    if (other.password != null) {
		return false;
	    }
	} else if (!password.equals(other.password)) {
	    return false;
	}
	if (patronymic == null) {
	    if (other.patronymic != null) {
		return false;
	    }
	} else if (!patronymic.equals(other.patronymic)) {
	    return false;
	}
	if (surname == null) {
	    if (other.surname != null) {
		return false;
	    }
	} else if (!surname.equals(other.surname)) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "User [id=" + id + ", login=" + login + ", email=" + email
		+ ", password=" + password + ", name=" + name + ", surname="
		+ surname + ", patronymic=" + patronymic + ", group=" + group
		+ "]";
    }

    public static void main(String ... args) throws Exception{
	    new User("ss._dsd2343","_aa@ff.rr");
	    System.out.println("create successfully");
	}
    
}