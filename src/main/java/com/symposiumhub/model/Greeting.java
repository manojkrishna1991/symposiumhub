package com.symposiumhub.model;



public class Greeting {
    
    private String content;
    
    private String name;
    

    public Greeting(String name,String content) {
        this.content = content;
        this.name=name;
    }

    public String getName() {
		return name;
	}

	public String getContent() {
        return content;
    }

}
