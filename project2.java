// Justin Acosta
// Project 2
// make a pool table with billard balls that collide
// make a button that resets table when clicked

String title= "Project 2 Pool Table";                        //Strings
String author= "Justin Acosta";
String help= "Press 'r' or left click to reset";
String control= "Press 'c' to move Cue Ball";

//Globals
float cueX, cueY, cueDX, cueDY;               
float redX, redY, redDX, redDY;
float yelX, yelY, yelDX, yelDY;
float bluX, bluY, bluDX, bluDY;
float top, bottom, left, right, middle;
float buttonX, buttonY, buttonW, buttonH;
int count;
boolean click = false;                    //boolean for mousePressed



void setup(){                             // Set up and Size.
  rectMode(CENTER);
  ellipseMode(CENTER);
  size( 700, 500);
  left= width/2 - 255;                    //left of center
  right= width/2 + 255;                   //right of center
  top= height/2 - 140;                    //top of center
  bottom= height - 106;                   //bottom of center
  middle= left + (right-left)/2;          //middle
  reset();
  
}


void reset(){                       
  cueX= width/2 -150;                                            // starting postions of balls
  cueY= height/2;
  redX = random(right , middle);    redY = random(top, bottom);      
  bluX = random(left, middle);      bluY= random(top, bottom);
  yelX = random(left, right);       yelY= random(top, bottom);
  
                                                                   
  redDX = random(1,6);              redDY = random(1,6);        //speed of balls
  bluDX = random(1,6);              bluDY = random(1,6);
  yelDX = random(1,6);              yelDY = random(1,6);
  cueDX = 0;                        cueDY = 0;


  buttonX= left-45;                                             //variables for reset button
  buttonY= bottom + 35;
  buttonW= 150;
  buttonH= 65;
}






void draw(){
  count = count + 1;               //count for animation
  background(100, 150, 100 );
  table();
  billiards();
  button();
  info();
  action();
  collisions();


}



void table(){                          // Pool table and felt.
  rectMode(CENTER);                    //Rect mode is Center
  fill(0);
  rect(width/2, height/2, 600, 350);   // boarder
  fill(100, 0 ,0);
  rect(width/2, height/2, 550, 300);   //Felt
  
  
}


void billiards(){    // shows red, blue, yellow and cue ball
  ellipseMode(CENTER);                                                                     //Ellipse mode is Center
  fill(255);          ellipse(cueX, cueY, 30, 30);                                         //Cue ball
  fill(255, 0, 0);    ellipse(redX, redY, 30, 30);   fill(0);  text("1", redX-5, redY+3);  // Red ball
  fill(0, 0, 255);    ellipse(bluX, bluY, 30, 30);   fill(0);  text("2", bluX-5, bluY+3);  // Blue ball                            
  fill(255, 255, 0);  ellipse(yelX, yelY, 30, 30);   fill(0);  text("3", yelX-5, yelY+3);  // Yellow ball                             
  
  
}



void action(){                                   //Balls move and bounce of boarders.

redX += redDX;      
if ( redX< left || redX> right ) redDX *= -1;

redY += redDY;  
if ( redY<top || redY>bottom ) redDY *=  -1;

bluX += bluDX;
if ( bluX < left || bluX > right) bluDX *= -1;

bluY += bluDY;
if (bluY < top || bluY > bottom) bluDY *= -1;

yelX += yelDX;
if (yelX < left || yelX > right) yelDX *= -1;

yelY += yelDY;
if (yelY < top || yelY > bottom) yelDY *= -1; 

cueX += cueDX;
if ( cueX < left || cueX > right) cueDX *= -1;

cueY += cueDY;
if ( cueY < top || cueY > bottom) cueDY *= -1;

}



void info(){                             //Displays Title, Name , and Info. 

  textSize(20);
  fill(0);  text(author, 75, 45);        //Name
  fill(0);  text(title, 450, 475);       //Title
  textSize(15);
  if (count / 30 % 2 == 0){              // control text flashes yellow
     fill(0);  
  }else{
     fill(255, 255, 0);
  }
  text(control, 450, 45);                //control text
  
  
}

void button(){                     //shows button to reset background
  rectMode(CORNER);                //reset rectmode to corner
  
  if (count/ 30 % 2 == 0){                                 //Makes button flash black and blue
    fill(0);
  }else{
    fill(0,0,255);
  }
  rect(buttonX, buttonY, buttonW, buttonH);                //show button
  fill(255, 255, 0);
  text("click here", buttonX+40, buttonY+30);              //button text
  text("to reset table!", buttonX+25, buttonY+50);
  
  
  
  
     
  
}

void collisions(){    //Balls bounce off each other and swap speeds
 float tmp;
 
 
 if (dist(redX, redY, yelX, yelY) < 30 ){      // Red and Yellow
   tmp = yelDX;  yelDX = redDX; redDX = tmp;
   tmp= yelDY;   yelDY = redDY; redDY = tmp;
 }
 
 if (dist(redX, redY, bluX, bluY) < 30){           // Red and Blue
   tmp = bluDX;   bluDX = redDX;  redDX = tmp;
   tmp = bluDY;   bluDY = redDY;  redDY = tmp;
 }
 
 if (dist(bluX, bluY, yelX, yelY) < 30){           // Blue and Yellow
   tmp = yelDX;   yelDX = bluDX;  bluDX = tmp;
   tmp = yelDY;   yelDY = bluDY;  bluDY = tmp;
 }
 
 if(cueDX != 0 || cueDY !=0){                      // Igonore Cue collisions if not moving
   if (dist(cueX, cueY, redX, redY) < 30){         // Cue and Red
     tmp = redDX;   redDX = cueDX;  cueDX = tmp;
     tmp = redDY;   redDY = cueDY;  cueDY = tmp;
 }
   if (dist(cueX, cueY, bluX, bluY) < 30){         // Cue and Blue
     tmp = bluDX;   bluDX = cueDX;  cueDX = tmp;
     tmp = bluDY;   bluDY = cueDY;  cueDY = tmp;
 }
   if (dist(cueX, cueY, yelX, yelY) < 30){         // Cue and Yellow
     tmp = yelDX;   yelDX = cueDX;  cueDX = tmp;
     tmp = yelDY;   yelDY = cueDY;  cueDY = tmp;
 }
 }
 
}

void keyPressed(){
  
  if (key == 'c'){                   //Press 'c' to make cue ball move
    cueX += cueDX;
    cueY += cueDY;
    cueDX = random(1,6);             // Cue ball speed is random
    cueDY = random(1,6);    
  }
  
  
}
  
  void mousePressed(){                //Click button to reset table
   
   if(mouseButton == LEFT &&
     mouseX > buttonX &&
     mouseX < buttonX + buttonW &&
     mouseY > buttonY &&
     mouseY < buttonY + buttonH){
       click = !click;
       reset();
     }
  }
  
