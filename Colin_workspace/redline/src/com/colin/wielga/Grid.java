package com.colin.wielga;


public class Grid {

	Note[][] noteGrid;
	int width;
	int height;
	
	public Grid(int x, int y){
	noteGrid = new Note[x][y];
	width = x;
	height = y;
//	for (int i=0;i<x;i++){
//		for (int j=0;j<y;y++){
//			noteGrid[i][j] = null;
//		}
//	}
	
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void addNote(Note note){
		noteGrid[note.getX()][note.getY()] = note;
	}
	
	public Note getNote(int x, int y){
		return noteGrid[x][y];
	}
	
	
}
