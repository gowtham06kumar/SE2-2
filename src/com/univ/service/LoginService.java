package com.univ.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.univ.beans.User;

public class LoginService {

	public boolean validate(String value1, String value2) {


		User user = new User(value1,value2);
		List<User> users = getUsers();
		
		boolean isCredentialsValid = validateUser(user,users);
			
		if(isCredentialsValid)
			return true;
		
		return false;
	}

	public boolean validateUser(User origUser, List<User> users) {

		for(User user :users)
		{
			if(origUser.getUserName().equals(user.getUserName()) && origUser.getPasswd().equals(user.getPasswd()))
				return true;
		}
		
		return false;
	}

	public List<User> getUsers()
	{
		String line = null;
		String[] userdetails;
		
		List<User> users = new ArrayList<User>();
		User user = null;
		
		try {
			FileReader fileReader = new FileReader("users.csv");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null) 
	        {
				userdetails = line.split(",");
				
				user = new User();
				user.setUserName(userdetails[0]);
				user.setPasswd(userdetails[1]);
				
				users.add(user);
	        }
			
			 bufferedReader.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return users;
		
	}

	public boolean updatePassword(String userName,String passwd)
	{
		List<User> users = getUsers();
		
		for(User user : users)
		{
			if(user.getUserName().equals(userName))
				user.setPasswd(passwd);
		}
		
		updateUsersList(users);
		return true;
	}

	private void updateUsersList(List<User> users) {
		
		try {
				FileWriter fileWriter = new FileWriter("users.csv");
				BufferedWriter bw = new BufferedWriter(fileWriter);
				
				for(User user : users)
				{
					bw.write(user.getUserName()+","+user.getPasswd()+"\n");
				}
				
				bw.close();
			}
			catch(FileNotFoundException e){
				e.printStackTrace();
			} 
			catch(IOException e){
				e.printStackTrace();
			}
		}

}
