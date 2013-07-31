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
  
  void drawBall(){
    noStroke();
    fill(255,255,255);
    ellipse(ballX, ballY, ballSize, ballSize);
  }
  
  void moveBall(){
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

