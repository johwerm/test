package com.example.test;

public class Light {

	public int[] pos;
	public int[] vel;
	
	public Light(int[] position, int[] velocity) {
		this.pos = position;
		this.vel = velocity;
	}
	
	public void move(int steps) {
		pos[0] += vel[0] * steps;
		pos[1] += vel[1] * steps;
	}
	
	
	public static Light of(String s) {
		int[] pos = new int[] {Integer.parseInt(s.substring(10, 16).trim()), Integer.parseInt(s.substring(18, 24).trim()) };
		int[] vel = new int[] {Integer.parseInt(s.substring(36, 38).trim()), Integer.parseInt(s.substring(40, 42).trim()) };
		
		return new Light(pos, vel);
	}
	
}
