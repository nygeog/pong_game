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

void setup(){
  size(800,500);
  strokeCap(SQUARE);
  background(0,0,0);
}

void draw(){
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

void drawEnvironment(){
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



void celebrate(String player){
  gameWon = true;
  winningPhrase = winningPhrase + player + " WINS!!!";
}
