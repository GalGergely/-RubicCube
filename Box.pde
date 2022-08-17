class Box {
  Settings setting = new Settings();
  PVector position;
  float length; 
  
  Box(float x, float y, float z, float length) {
    this.length = length;
    position = new PVector(x,y,z);
  }
  
  void show(){
   pushMatrix();
   
   fill(setting.boxColor); 
   stroke(setting.strokeColor);
   strokeWeight(setting.strokeWeight);
   
   translate(position.x, position.y, position.z);
   box(length);
   popMatrix();
  }
}
