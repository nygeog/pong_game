import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class pong extends PApplet {

int paddleSpeed = 4;
Ball ball = new Ball(400, 250, 5, 20);
Paddle p1 = new Paddle(20);
Paddle p2 = new Paddle(800-20);
boolean gameOn = false;
int player1Score = 0;
int player2Score = 0;
int winningNumber = 5;
boolean gameWon = false;
String winningPhrase = "Holy cow! Player ";
String playerInstructions = "Controls:1,2 for Player 1; 9,0 for Player 2 - Press 'a' to start";

public void setup(){
  size(800,500);
  strokeCap(SQUARE);
  background(0,0,0);
}

public void draw(){
  if(gameWon == false){
    drawEnvironment();
  
    p1.drawPaddle();
    p2.drawPaddle();
    //p2.movePaddle(1);
  
    if(gameOn == true){
      ball.moveBall();
    }
  
    ball.drawBall();  
  }else{
    background(random(255),random(255),random(255));
    textSize(50);
    fill(255);
    text(winningPhrase, 50, 200);
  }

  
  if(keyPressed){

    if(key == '1'){
      p1.movePaddle(paddleSpeed);
    }else if(key == '2'){
      p1.movePaddle(-paddleSpeed);
    }else if(key == '9'){
      p2.movePaddle(paddleSpeed);
    }else if(key == '0'){
      p2.movePaddle(-paddleSpeed);
    }
    
    if(gameOn == false){
      if(key == 'a'){
        gameOn = true;
      }
    }
  }
}

public void drawEnvironment(){
  fill(0,0,0,55);
  rect(0, 0, width, height);
  
  stroke(100);
  strokeWeight(5);
  line(width/2, 0, width/2, height);
  
  textSize(100);
  fill(100);
  text(player1Score, 200, 280);
  text(player2Score, 600, 280);
  textSize(10);
  text(playerInstructions, 25, 25);
}



public void celebrate(String player){
  gameWon = true;
  winningPhrase = winningPhrase + player + " WINS!!!";
}
class Ball{
  int ballSize = 20;
  float ballX = 0;
  float ballY = 0;
  float ballSpeed = 5;
  boolean xToggle = false;
  boolean yToggle = false;

  Ball(float x, float y, float s, int bS){
    ballX = x;
    ballY = y;
    ballSpeed = s;
    ballSize = bS;
  }
  
  public void drawBall(){
    noStroke();
    fill(255,255,255);
    ellipse(ballX, ballY, ballSize, ballSize);
  }
  
  public void moveBall(){
    if(ballX > width){
      //point for player one
      gameOn = false;
      player1Score++;
      
      if(player1Score == winningNumber){
        celebrate("One");
      }else{
        ballX = 400;
        ballY = 250;      
      }

    }
    if(ballX < 0){
      //point for player two
      gameOn = false;
      player2Score++;
      if(player2Score == winningNumber){
        celebrate("Two");
      }else{
        ballX = 400;
        ballY = 250;      
      }

    }
    
    
    if(ballX > width - 30){
      if(p2.checkPaddle(ballY) == true){
        xToggle = true;
      }
    }
    if(ballX < 30){
      if(p1.checkPaddle(ballY) == true){
        xToggle = false;
      }
    }
    
    
  
    if(ballY > height - ballSize/2){
      yToggle = true;
    }
    if(ballY < ballSize/2){
      yToggle = false;
    }
  
    if(xToggle == false){
      ballX = ballX + ballSpeed;
    }else{
      ballX = ballX - ballSpeed;
    }
    if(yToggle == false){
      ballY = ballY + ballSpeed;
    }else{
      ballY = ballY - ballSpeed;
    }
  }
}

class Paddle{
  float posX = 0;
  float posY = 250;
  int paddleLength = 150;
  
  Paddle(float pX){
    posX = pX;
  }
  
  public void drawPaddle(){
    stroke(255);
    strokeWeight(10);
    line(posX, posY, posX, posY + paddleLength);
  }
  
  public void movePaddle(float pY){
    if( (posY + pY) > 0 && (posY + pY) < height - paddleLength ){
      posY = posY + pY;
    }
  }
  
  public boolean checkPaddle(float pY){
    if(pY > posY && pY < posY + paddleLength){
      return true;
    }else{
      return false;
    }
  }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "pong" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
