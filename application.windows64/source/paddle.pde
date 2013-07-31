class Paddle{
  float posX = 0;
  float posY = 250;
  int paddleLength = 150;
  
  Paddle(float pX){
    posX = pX;
  }
  
  void drawPaddle(){
    stroke(255);
    strokeWeight(10);
    line(posX, posY, posX, posY + paddleLength);
  }
  
  void movePaddle(float pY){
    if( (posY + pY) > 0 && (posY + pY) < height - paddleLength ){
      posY = posY + pY;
    }
  }
  
  boolean checkPaddle(float pY){
    if(pY > posY && pY < posY + paddleLength){
      return true;
    }else{
      return false;
    }
  }
}
